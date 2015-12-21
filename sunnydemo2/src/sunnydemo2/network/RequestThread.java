package sunnydemo2.network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by sunny on 2015/11/30.
 * Annotion:
 */
public class RequestThread extends Thread {

    private Context mContext;
    private String mPhone;
    private String mPwd;
    private String mUrl;
    private String mSign;

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public void setmSign(String mSign) {
        this.mSign = mSign;
    }

    public RequestThread(Context context, String account, String pwd) {
        this.mContext = context;
        this.mPhone = account;
        this.mPwd = pwd;
    }

    @Override
    public void run() {
        //请求客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        //请求参数
        RequestBody requestBody = new FormEncodingBuilder()
                .add("appId", "10100")
                .add("timestamp", String.valueOf(System.currentTimeMillis()))
                .add("account", mPhone)
                .add("password", mPwd)
                .add("sign", mSign)
                .addEncoded("charset", "utf-8")
                .build();
        Request getRequest = new Request.Builder().url(mUrl)
                .method("POST", requestBody)
                .build();


        Call requestCall = okHttpClient.newCall(getRequest);
        try {
            Response response = requestCall.execute();
            if (response.isSuccessful()) {
                Log.e("okhttp", "返回结果：\n" + response.body().string());
            } else {

            }
        } catch (final IOException e) {
            e.printStackTrace();

            Looper.prepare();
            Looper requestLooper = Looper.myLooper();
            SunnyHandler sunnyHandler = new SunnyHandler(mContext,requestLooper);
            Message msg = sunnyHandler.obtainMessage();
            msg.what = 0;
            msg.obj = e.getMessage();
            sunnyHandler.sendMessage(msg);
            Looper.loop();

        }
    }
}
