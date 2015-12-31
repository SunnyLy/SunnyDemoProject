package sunnydemo2.rxjava.api;

import com.squareup.okhttp.Response;

import java.util.TreeMap;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sunnydemo2.rxjava.biz.GitHubService;
import sunnydemo2.rxjava.event.BaseEvent;
import sunnydemo2.rxjava.event.DingCanEvent;

/**
 * Created by sunny on 2015/12/29.
 * Retrofit用RestAdapter把接口变成实现类
 */
public class GitHubApi {

    /**
     * 把GitHubService变成实现类
     */

    public Call<Object> getUser(String url, String user) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        return gitHubService.listRepos(user);
    }


    /**
     * 订餐。
     * 返回Observable
     *
     * @param serverUrl
     * @param foodId
     * @return
     */
    public Observable<Object> dingCan(String serverUrl, String foodId) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {

            }
        });
        Retrofit foodRetrofit = new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create())//返回数据解析方式
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        GitHubService gitHubService = foodRetrofit.create(GitHubService.class);
        return gitHubService.dingCan(foodId);
    }

    public Call<Response> dingCan1(String serverUrl, String foodId) {

        Retrofit foodRetrofit = new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GitHubService gitHubService = foodRetrofit.create(GitHubService.class);
        return gitHubService.dingCan1(foodId);
    }

    /**
     * 多参数请求
     *
     * @param serverUrl
     * @param params
     * @return
     */
    public void dingCan2(String serverUrl, TreeMap<String, String> params) {

        Retrofit foodRetrofit = new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GitHubService gitHubService = foodRetrofit.create(GitHubService.class);

        Observable<Object> objectObservable = gitHubService.dingCan(params);
        objectObservable
                .subscribeOn(Schedulers.io())//数据请求线程
                // .observeOn(Schedulers.newThread())//新开一个线程
                .observeOn(AndroidSchedulers.mainThread())//这是RxAndroid中的UI线程
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        DingCanEvent dingCanEvent = new DingCanEvent();
                        dingCanEvent.setResponseStatus(BaseEvent.ResponseStatus.ON_COMPLETE);
                        EventBus.getDefault().post(dingCanEvent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        DingCanEvent dingCanEvent = new DingCanEvent();
                        dingCanEvent.setmObject(e.getCause());
                        dingCanEvent.setResponseStatus(BaseEvent.ResponseStatus.ERROR);
                        EventBus.getDefault().post(dingCanEvent);
                    }

                    @Override
                    public void onNext(Object o) {
                        DingCanEvent dingCanEvent = new DingCanEvent(o);
                        dingCanEvent.setResponseStatus(BaseEvent.ResponseStatus.ON_NEXT);
                        EventBus.getDefault().post(dingCanEvent);
                    }
                });
    }


}
