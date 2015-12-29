package sunnydemo2.rxjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.het.common.base.BaseActivity;
import com.smartbracelet.sunny.sunnydemo2.R;

import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import sunnydemo2.rxjava.api.GitHubApi;

/**
 * Created by sunny on 2015/12/29.
 * 这个类主要是用来测试响应式编程:
 * RxJava,Retrofit,EventBus三者的巧妙结合，
 *
 */
public class RxJavaActivity extends BaseActivity implements View.OnClickListener{


    private Button mBtnSend;
    private Button mRetrofit;
    private TextView mTextViewShow;
    private TextView mTextViewShow1;

    public static void startRxJavaActivity(Context context){
        Intent targetIntent = new Intent(context,RxJavaActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        mBtnSend = (Button) findViewById(R.id.rxjava_send);
        mRetrofit = (Button) findViewById(R.id.rxjava_retrofit);

        mTextViewShow = (TextView) findViewById(R.id.rxjava_info);
        mTextViewShow1 = (TextView) findViewById(R.id.rxjava_info1);

        mBtnSend.setOnClickListener(this);
        mRetrofit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rxjava_send:
                doSend();
                break;
            case R.id.rxjava_retrofit:
                doNetRequest();
                break;
        }
    }

    /**
     * 用retrofit代替volley实现网络请求
     */
    private void doNetRequest() {

        String serverUrl = "http://200.200.200.54:8080";
        Observable<String > result = new GitHubApi().dingCan(serverUrl,"80");
        result.subscribe(dingCanOnNextAction);

    }

    private Action1<String > dingCanOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {

            freshUI(s);
        }
    };

    private void doSend(){
        final long downTime = System.currentTimeMillis();
        // myObserve.subscribe(subscriber);
        //simpleObserve.subscribe(simpleOnNextAction);
        Observable.just("直接一句话来表示，效果跟上面那种Action是一样的").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                s = "OhYeah,真的一样的呢\n"+s+"\n响应时间："+(System.currentTimeMillis() - downTime);
                freshUI(s);
            }
        });

        mTextViewShow1.setText("OhYeah,真的一样的呢\n"+"直接一句话来表示，效果跟上面那种Action是一样的\n" +
                "响应时间："+(System.currentTimeMillis() - downTime));

               /* //使用java8的lambda代码可以更简洁
                Observable.just("使用java8的lambda代码可以更简洁")
                        .subscribe(s -> freshUI(s));*/
    }

    private void freshUI(String s) {
        if(mTextViewShow != null){
            mTextViewShow.setText(s);
        }
    }

    /**
     * 复杂点的写法
     * 被订阅者：
     * 用来像所有订阅者或指定订阅者发送消息
     */
    Observable<String > myObserve = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {

            subscriber.onNext("This is just a test!");
            subscriber.onCompleted();

        }
    });

    /**
     * Observable简单的写法：
     *
     */
    Observable<String > simpleObserve = Observable.just("This is a simple Observable!");


    /**
     * 订阅者(复杂一点的写法)：
     * 这种写法里面必须要实现onCompleted(),onError(),onNext()三个方法
     * 用来接收被订阅者所发出的信息。
     */
    Subscriber<String > subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

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
    Action1<String > simpleOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            freshUI(s);
        }

    } ;


}
