package sunnydemo2.rxjava.biz;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by sunny on 2015/12/29.
 * Annotion:
 */
public interface GitHubService {
    @GET("/users/{user}/repos")
    Observable<String > listRepos(@Path("user") String user);

    /**
     * 测试订单网址
     * @param foodId
     * @return
     */
    @GET("/dingcan/config/orderAdd?{foodId}")
    Observable<String > dingCan(@Path("foodId") String foodId);
}
