package net.oschina.app.ui;

import java.io.ByteArrayInputStream;

import net.oschina.app.AppConfig;
import net.oschina.app.AppContext;
import net.oschina.app.R;
import net.oschina.app.api.ApiHttpClient;
import net.oschina.app.api.remote.OSChinaApi;
import net.oschina.app.base.BaseActivity;
import net.oschina.app.bean.Constants;
import net.oschina.app.bean.LoginUserBean;
import net.oschina.app.bean.Result;
import net.oschina.app.util.CyptoUtils;
import net.oschina.app.util.SimpleTextWatcher;
import net.oschina.app.util.StringUtils;
import net.oschina.app.util.TDevice;
import net.oschina.app.util.TLog;
import net.oschina.app.util.XmlUtils;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.protocol.HttpContext;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.utils.KJLoger;

import android.content.Intent;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.InjectView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * 用户登录界面
 * 
 * @author kymjs (http://www.kymjs.com/)
 */
public class LoginActivity extends BaseActivity {

    public static final int REQUEST_CODE_INIT = 0;
    private static final String BUNDLE_KEY_REQUEST_CODE = "BUNDLE_KEY_REQUEST_CODE";
    protected static final String TAG = LoginActivity.class.getSimpleName();

    @InjectView(R.id.et_username)
    EditText mEtUserName;

    @InjectView(R.id.et_password)
    EditText mEtPassword;

    @InjectView(R.id.iv_clear_username)
    View mIvClearUserName;

    @InjectView(R.id.iv_clear_password)
    View mIvClearPassword;

    @InjectView(R.id.btn_login)
    Button mBtnLogin;
    @InjectView(R.id.qq_login)
    Button mBtnQQLogin;

    private final int requestCode = REQUEST_CODE_INIT;
    private String mUserName;
    private String mPassword;

    private Tencent mTencent;

    private final TextWatcher mUserNameWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                int count) {
            mIvClearUserName
                    .setVisibility(TextUtils.isEmpty(s) ? View.INVISIBLE
                            : View.VISIBLE);
        }
    };
    private final TextWatcher mPassswordWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                int count) {
            mIvClearPassword
                    .setVisibility(TextUtils.isEmpty(s) ? View.INVISIBLE
                            : View.VISIBLE);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected int getActionBarTitle() {
        return R.string.login;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
        case R.id.iv_clear_username:
            mEtUserName.getText().clear();
            mEtUserName.requestFocus();
            break;
        case R.id.iv_clear_password:
            mEtPassword.getText().clear();
            mEtPassword.requestFocus();
            break;
        case R.id.btn_login:
            handleLogin();
            break;
        case R.id.qq_login:
            qqLogin();
            break;
        default:
            break;
        }
    }

    private void handleLogin() {

        if (!prepareForLogin()) {
            return;
        }

        // if the data has ready
        mUserName = mEtUserName.getText().toString();
        mPassword = mEtPassword.getText().toString();

        showWaitDialog(R.string.progress_login);
        OSChinaApi.login(mUserName, mPassword, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {

        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            try {
                AsyncHttpClient client = ApiHttpClient.getHttpClient();
                HttpContext httpContext = client.getHttpContext();
                CookieStore cookies = (CookieStore) httpContext
                        .getAttribute(ClientContext.COOKIE_STORE);
                if (cookies != null) {
                    String tmpcookies = "";
                    for (Cookie c : cookies.getCookies()) {
                        TLog.log(TAG,
                                "cookie:" + c.getName() + " " + c.getValue());
                        tmpcookies += (c.getName() + "=" + c.getValue()) + ";";
                    }
                    TLog.log(TAG, "cookies:" + tmpcookies);
                    AppContext.getInstance().setProperty(AppConfig.CONF_COOKIE,
                            tmpcookies);
                    ApiHttpClient.setCookie(ApiHttpClient.getCookie(AppContext
                            .getInstance()));
                    HttpConfig.sCookie = tmpcookies;
                }
                LoginUserBean user = XmlUtils.toBean(LoginUserBean.class,
                        new ByteArrayInputStream(arg2));
                Result res = user.getResult();
                if (res.OK()) {
                    // 保存登录信息
                    user.getUser().setAccount(mUserName);
                    user.getUser().setPwd(mPassword);
                    user.getUser().setRememberMe(true);
                    AppContext.getInstance().saveUserInfo(user.getUser());
                    hideWaitDialog();
                    handleLoginSuccess();
                } else {
                    AppContext.getInstance().cleanLoginInfo();
                    hideWaitDialog();
                    AppContext.showToast(res.getErrorMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
                onFailure(arg0, arg1, arg2, e);
            }
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                Throwable arg3) {
            hideWaitDialog();
            AppContext.showToast(R.string.tip_login_error_for_network);
        }
    };

    private void handleLoginSuccess() {
        Intent data = new Intent();
        data.putExtra(BUNDLE_KEY_REQUEST_CODE, requestCode);
        setResult(RESULT_OK, data);
        this.sendBroadcast(new Intent(Constants.INTENT_ACTION_USER_CHANGE));
        finish();
    }

    private boolean prepareForLogin() {
        if (!TDevice.hasInternet()) {
            AppContext.showToastShort(R.string.tip_no_internet);
            return false;
        }
        String uName = mEtUserName.getText().toString();
        if (StringUtils.isEmpty(uName)) {
            AppContext.showToastShort(R.string.tip_please_input_username);
            mEtUserName.requestFocus();
            return false;
        }
        // 去除邮箱正确性检测
        // if (!StringUtils.isEmail(uName)) {
        // AppContext.showToastShort(R.string.tip_illegal_email);
        // mEtUserName.requestFocus();
        // return false;
        // }
        String pwd = mEtPassword.getText().toString();
        if (StringUtils.isEmpty(pwd)) {
            AppContext.showToastShort(R.string.tip_please_input_password);
            mEtPassword.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void initView() {
        mIvClearUserName.setOnClickListener(this);
        mIvClearPassword.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnQQLogin.setOnClickListener(this);

        mEtUserName.addTextChangedListener(mUserNameWatcher);
        mEtPassword.addTextChangedListener(mPassswordWatcher);
    }

    @Override
    public void initData() {
        mTencent = Tencent.createInstance(AppConfig.APP_QQ_KEY,
                this.getApplicationContext());

        mEtUserName.setText(AppContext.getInstance()
                .getProperty("user.account"));
        mEtPassword.setText(CyptoUtils.decode("oschinaApp", AppContext
                .getInstance().getProperty("user.pwd")));
    }

    /**
     * QQ登陆
     */
    private void qqLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", new AuthorListener());
        }
    }

    // /**
    // * 封装并返回QQ登陆数据
    // */
    // private User packQQLoginBean(User user, JSONObject obj) {
    // user.setLoginType(QQ_LOGIN);
    // if (obj.has("openid")) {
    // user.setUserId(obj.optString("openid"));
    // } else {
    // user.setUserName(obj.optString("nickname"));
    // user.setSex("男".equals(obj.optString("gender")) ? 1 : 2);
    // user.setHeadUrl(obj.optString("figureurl_qq_1"));
    // user.setPwd(user.getUserId());
    // }
    // return user;
    // }

    class AuthorListener implements IUiListener {

        @Override
        public void onCancel() {
            KJLoger.debug(getClass().getName() + "用户取消登陆");
        }

        @Override
        public void onComplete(Object arg0) {
            // packQQLoginBean(user, obj);
            AppContext.showToast("成功"); // 服务器端暂无
        }

        @Override
        public void onError(UiError error) {
            KJLoger.debug(getClass().getName() + "登陆失败" + error.errorDetail);
        }
    }
}
