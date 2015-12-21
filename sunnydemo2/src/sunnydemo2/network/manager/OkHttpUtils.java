package sunnydemo2.network.manager;

import android.content.Context;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import sunnydemo2.network.biz.ICallback;

/**
 * Created by sunny on 2015/11/30.
 * Annotion:
 */
public class OkHttpUtils {

    private OkHttpClient mOkHttpClient;

    private static OkHttpUtils mInstance;

    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient();
        //设置超时
        mOkHttpClient.setConnectTimeout(5000, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(10000,TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10000,TimeUnit.SECONDS);
        //设置证书任证
        mOkHttpClient.setAuthenticator(new Authenticator() {
            @Override
            public Request authenticate(Proxy proxy, Response response) throws IOException {

                return null;
            }

            @Override
            public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                return null;
            }
        });
    }

    public static OkHttpUtils getInstance() {

        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                mInstance = new OkHttpUtils();
            }
        }

        return mInstance;
    }

    /**
     * OkHttp Get请求
     *
     * @param callback
     * @param url 网络请求地址
     * @param params 请求参数
     */
    public void okHttpGet(final ICallback callback, String url, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        stringBuilder.append("?");
        if (params != null && params.size() > 0) {
            Iterator keys = params.entrySet().iterator();
            while (keys.hasNext()) {
                Map.Entry entry = (Map.Entry) keys.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                stringBuilder.append(key + "=" + value + "&");
            }

            stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);
        }

        String requestUrl = stringBuilder.toString();
        Request okHttpGet = new Request.Builder()
                .url(requestUrl)
                .build();

        Call requestCall = mOkHttpClient.newCall(okHttpGet);
        requestCall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onFailure(0,e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                callback.onSuccess(response.body().string());
            }
        });


    }

}
