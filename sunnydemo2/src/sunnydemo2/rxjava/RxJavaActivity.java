package sunnydemo2.rxjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.TreeMap;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import sunnydemo2.rxjava.api.GitHubApi;
import sunnydemo2.rxjava.event.BaseEvent;
import sunnydemo2.rxjava.event.DingCanEvent;

/**
 * Created by sunny on 2015/12/29.
 * 这个类主要是用来测试响应式编程:
 * RxJava,Retrofit,EventBus三者的巧妙结合，
 */
public class RxJavaActivity extends Activity implements View.OnClickListener {

    private static final String SERVEL_URL = "http://200.200.200.54:8080";
    private static final String USER_URL = "https://api.github.com";


    private Button mBtnSend;
    private Button mRetrofit;
    private Button mRetrofitObservable;
    private Button mRetrofitEventBus;
    private TextView mTextViewShow;
    private TextView mTextViewShow1;

    public static void startRxJavaActivity(Context context) {
        Intent targetIntent = new Intent(context, RxJavaActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        EventBus.getDefault().register(this);

        mBtnSend = (Button) findViewById(R.id.rxjava_send);
        mRetrofit = (Button) findViewById(R.id.rxjava_retrofit);
        mRetrofitObservable = (Button) findViewById(R.id.retrofit_observable);
        mRetrofitEventBus = (Button) findViewById(R.id.retrofit_eventbus);

        mTextViewShow = (TextView) findViewById(R.id.rxjava_info);
        mTextViewShow1 = (TextView) findViewById(R.id.rxjava_info1);

        mBtnSend.setOnClickListener(this);
        mRetrofit.setOnClickListener(this);
        mRetrofitObservable.setOnClickListener(this);
        mRetrofitEventBus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rxjava_send:
                doSend();
                break;
            case R.id.rxjava_retrofit:
                doNetRequest();
                break;
            case R.id.retrofit_observable:
                //一个请求参数
                responseByObservale(SERVEL_URL, "80");
                break;
            case R.id.retrofit_eventbus:
                //多个请求参数
                responseByObservalbeMultiParams(SERVEL_URL);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 用retrofit代替volley实现网络请求
     */
    private void doNetRequest() {

        responseByCall(USER_URL, "SunnyLy");


        //实现并发请求


    }

    /**
     * 提交多个请求参数
     *
     * @param serverUrl
     */
    private void responseByObservalbeMultiParams(final String serverUrl) {

        final TreeMap<String, String> params = new TreeMap<>();
        params.put("foodId", "80");
        params.put("tc", "9");
        new GitHubApi().dingCan2(serverUrl, params);

        /*new Thread(){
            @Override
            public void run() {
                Observable<Object> result = new GitHubApi().dingCan2(serverUrl,params);
                result.subscribe(dingCanOnNextAction, dingCanOnErrorAction, dingCanOnCompleteAction);
            }
        }.start();*/


    }

    /**
     * Retrofit与RxJava结合
     *
     * @param serverUrl
     * @param s
     */
    private void responseByObservale(final String serverUrl, final String s) {
        new Thread() {
            @Override
            public void run() {
                Observable<Object> result = new GitHubApi().dingCan(serverUrl, s);
                result.subscribe(dingCanOnNextAction, dingCanOnErrorAction, dingCanOnCompleteAction);
            }
        }.start();


    }

    /**
     * 返回以Call<T></T>
     *
     * @param userUrl
     * @param sunnyLy
     */
    private void responseByCall(String userUrl, String sunnyLy) {
        Call<Object> userInfo = new GitHubApi().getUser(userUrl, sunnyLy);
        userInfo.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(retrofit.Response<Object> response, Retrofit retrofit) {
                freshUI("responseByCall:\nonResponse:" + response.message() +
                        "\nreslut:" + response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                freshUI("onFaiure:\n" + t.getMessage());
            }
        });
    }

    /**
     * onNextAction
     */
    private Action1<Object> dingCanOnNextAction = new Action1<Object>() {
        @Override
        public void call(Object s) {

            String result = "";
            if (s == null) {
                result = "返回结果为null";
            } else {
                result = "返回Observable:"+s.toString();
            }
            freshUI(result);
        }
    };

    /**
     * onErrorAction
     */
    private Action1<Throwable> dingCanOnErrorAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable s) {
            freshUI("onError" + s.getCause());
        }
    };

    /**
     * onCompleteAction
     */
    private Action0 dingCanOnCompleteAction = new Action0() {
        @Override
        public void call() {

        }
    };

    private void doSend() {
        final long downTime = System.currentTimeMillis();
        // myObserve.subscribe(subscriber);
        //simpleObserve.subscribe(simpleOnNextAction);
        Observable.just("直接一句话来表示，效果跟上面那种Action是一样的").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                s = "OhYeah,真的一样的呢\n" + s + "\n响应时间：" + (System.currentTimeMillis() - downTime);
                freshUI(s);
            }
        });

        mTextViewShow1.setText("OhYeah,真的一样的呢\n" + "直接一句话来表示，效果跟上面那种Action是一样的\n" +
                "响应时间：" + (System.currentTimeMillis() - downTime));

               /* //使用java8的lambda代码可以更简洁
                Observable.just("使用java8的lambda代码可以更简洁")
                        .subscribe(s -> freshUI(s));*/
    }

    private void freshUI(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTextViewShow != null) {
                    mTextViewShow.setText(s);
                }
            }
        });

    }

    /**
     * 复杂点的写法
     * 被订阅者：
     * 用来像所有订阅者或指定订阅者发送消息
     */
    Observable<String> myObserve = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {

            subscriber.onNext("This is just a test!");
            subscriber.onCompleted();

        }
    });

    /**
     * Observable简单的写法：
     */
    Observable<String> simpleObserve = Observable.just("This is a simple Observable!");


    /**
     * 订阅者(复杂一点的写法)：
     * 这种写法里面必须要实现onCompleted(),onError(),onNext()三个方法
     * 用来接收被订阅者所发出的信息。
     */
    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

            freshUI(e.getMessage());
        }

        @Override
        public void onNext(String s) {

            freshUI(s);

        }
    };

    /**
     * Subscriber精简写法：
     * 同上，每一次写都要复写onCompleted(),onError(),onNext()三个方法，
     * 比较麻烦，
     * 那么Observable提供了一种Action的便捷，如下：
     */
    Action1<String> simpleOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            freshUI(s);
        }

    };


    public void onEventMainThread(DingCanEvent event) {
        if (event != null) {
            BaseEvent.ResponseStatus responseStatus = event.getResponseStatus();
            switch (responseStatus) {
                case ERROR:
                    String error = (String) event.getmObject();
                    freshUI(error);
                    break;
                case ON_NEXT:
                    Object msg = event.getmObject();
                    freshUI(msg == null ? "返回结果为null" : "EventBus:\n"+msg.toString());
                    break;
                case ON_COMPLETE:
                    break;
            }
        }

    }


}
