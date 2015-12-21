package sunnydemo2.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.smartbracelet.sunny.sunnydemo2.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import sunnydemo2.network.biz.ICallback;
import sunnydemo2.network.manager.OkHttpUtils;
import sunnydemo2.utils.MD5;
import sunnydemo2.utils.SecurityUtils;

/**
 * Created by sunny on 2015/11/18.
 * Annotion:XUtils网络请求框架
 */
public class OkHttpNetWorkActivity extends Activity implements View.OnClickListener {

    private static final String appSecret = "aac1b1ea435a40ceb2a6bbcf42bbdcfe";
    private static final String mUrl = "https://api.clife.cn/v1/account/login";

    private OkHttpUtils mOkHttpUtils;

    public static void startNetWorkActivity(Context context) {
        Intent targetIntent = new Intent(context, OkHttpNetWorkActivity.class);
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

        initParams();
    }

    private void initParams() {
        mOkHttpUtils = OkHttpUtils.getInstance();
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

        startLogin(phone, pwd);
    }

    /**
     * Get请求
     *
     * @param view
     */
    public void getRequest(View view) {

        Map<String, String> params = new HashMap<>();
        params.put("city", "ip");
        mOkHttpUtils.okHttpGet(new ICallback<String>() {
            @Override
            public void onSuccess(String obj) {
                Looper.prepare();
                Log.e("onSuccess:Looper:", "当前线程looper:" + Looper.myLooper().hashCode() + ""
                        + "\n主线程looper:" + Looper.getMainLooper().hashCode() + "");
                InnerHandler innerHandler = new InnerHandler(Looper.getMainLooper());
                innerHandler.sendMessage(innerHandler.obtainMessage(2, obj));
                Looper.loop();

            }

            @Override
            public void onFailure(int code, String msg) {
                Log.e("onFailure:Looper:", "当前线程looper:" + Looper.myLooper().hashCode() + ""
                        + "\n主线程looper:" + Looper.getMainLooper().hashCode() + "");
                SunnyHandler failureHandler = new SunnyHandler(OkHttpNetWorkActivity.this
                        , Looper.getMainLooper());
                failureHandler.sendMessage(failureHandler.obtainMessage(1, msg));
            }
        }, "http://api.clife.cn/v1/web/env/weather/clife/now", params);

    }

    private class InnerHandler extends Handler {
        private Looper mLooper;
        private InnerHandler(Looper looper) {
            super(looper);
            mLooper = looper;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Log.e("Looper::","handler接收到的looper:"+mLooper.hashCode());
                    if (mLooper == mLooper.getMainLooper()) {
                        mResult.setText((String) msg.obj);
                    } else {
                        Toast.makeText(OkHttpNetWorkActivity.this,
                                "请使用Looper.getMainLooper()来刷新UI", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    /**
     * Post请求
     *
     * @param view
     */
    public void postRequest(View view) {
        String phone = mEditTextPhone.getText().toString().trim();
        String pwd = mEditTextPassword.getText().toString().trim();

        RequestThread requestThread = new RequestThread(this, phone, pwd);

        requestThread.setmSign(params2sign(phone, pwd));
        requestThread.setmUrl(mUrl);
        requestThread.start();

    }

    /**
     * 上传文件请求
     *
     * @param view
     */
    public void uploadRequest(View view) {

    }

    /**
     * 下载文件请求
     *
     * @param view
     */
    public void downloadRequest(View view) {

    }

    private void startLogin(String phone, String pwd) {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams("UTF-8");
        params.addBodyParameter("appId", "10100");
        params.addBodyParameter("timestamp", String.valueOf(System.currentTimeMillis()));
        params.addBodyParameter("account", phone);
        params.addBodyParameter("password", SecurityUtils.encrypt4login(pwd, appSecret));
        params.addBodyParameter("sign", params2sign(phone, pwd));
        httpUtils.send(HttpRequest.HttpMethod.POST, mUrl, params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {

                Toast.makeText(OkHttpNetWorkActivity.this, responseInfo.result.toString(), Toast.LENGTH_LONG).show();
                mResult.setText("返回结果：\n" + responseInfo.result.toString());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(OkHttpNetWorkActivity.this, "登录失败", Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * 参数签名
     *
     * @return
     */
    public String params2sign(String account, String pwd) {
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
