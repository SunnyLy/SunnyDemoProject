package sunnydemo2.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.het.account.callback.IQueryFirstLoginCallback;
import com.het.account.manager.HetLogin;
import com.het.account.manager.LoginManager;
import com.het.account.ui.LoginActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.smartbracelet.sunny.sunnydemo2.R;

import sunnydemo2.utils.MD5;
import sunnydemo2.utils.SecurityUtils;

/**
 * Created by sunny on 2015/11/18.
 * Annotion:XUtils网络请求框架
 */
public class XutilsNetWorkActivity extends Activity implements View.OnClickListener{

    private static final String appSecret = "aac1b1ea435a40ceb2a6bbcf42bbdcfe";
    private static final String mUrl = "https://api.clife.cn/v1/account/login";

    public static void startNetWorkActivity(Context context){
        Intent targetIntent = new Intent(context,XutilsNetWorkActivity.class);
        context.startActivity(targetIntent);
    }

    private EditText mEditTextPhone;
    private EditText mEditTextPassword;
    private Button mBtnLogin;

    private TextView mResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
    }

    @Override
    public void onContentChanged() {
        mEditTextPhone = (EditText) findViewById(R.id.et_phone);
        mEditTextPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mResult = (TextView) findViewById(R.id.result);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phone = mEditTextPhone.getText().toString().trim();
        String pwd = mEditTextPassword.getText().toString().trim();


      //  startLogin(phone, pwd);
        //调BaseProject中的登录


       // LoginActivity.startLoginActivity(XutilsNetWorkActivity.this);
       HetLogin.login(new IQueryFirstLoginCallback() {
           @Override
           public void isFirstLogin() {

           }

           @Override
           public void notFirstLogin() {

           }

           @Override
           public void queryError(int i, String s) {

           }

           @Override
           public void getThirdIdSuccess(String s, String s1) {

           }

           @Override
           public void loginSuccess(String s, String s1) {

           }
       },phone,pwd);
    }

    private void startLogin(String phone, String pwd) {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams("UTF-8");
        params.addBodyParameter("appId","10100");
        params.addBodyParameter("timestamp",String.valueOf(System.currentTimeMillis()));
        params.addBodyParameter("account", phone);
        params.addBodyParameter("password", SecurityUtils.encrypt4login(pwd, appSecret));
        params.addBodyParameter("sign",params2sign(phone,pwd));
        httpUtils.send(HttpRequest.HttpMethod.POST, mUrl, params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {

                Toast.makeText(XutilsNetWorkActivity.this,responseInfo.result.toString(),Toast.LENGTH_LONG).show();
                mResult.setText("返回结果：\n"+responseInfo.result.toString());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(XutilsNetWorkActivity.this,"登录失败",Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * 参数签名
     * @return
     */
    public String params2sign(String account,String pwd) {
        StringBuilder sb = new StringBuilder("POST");
        sb.append(Url2Uri(mUrl));
        sb.append("appId=").append("10100").append("&").
                append("timestamp=").append(String.valueOf(System.currentTimeMillis())).append("&").
                append("account=").append(account).append("&").
                append("password=").append(SecurityUtils.encrypt4login(pwd, appSecret)).append("&")
                .append(appSecret);
         Log.d("sign param", sb.toString());
        return MD5.getMD5(sb.toString());
    }

    public String Url2Uri(String url) {
        Uri.Builder uriBuilder = Uri.parse(url).buildUpon();
        return uriBuilder.build().toString();
    }
}
