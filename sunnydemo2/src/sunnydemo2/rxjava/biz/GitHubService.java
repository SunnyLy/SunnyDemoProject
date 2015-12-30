package sunnydemo2.rxjava.biz;

import com.squareup.okhttp.Response;

import java.util.List;
import java.util.TreeMap;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by sunny on 2015/12/29.
 * Annotion:
 *
 */
public interface GitHubService {
    @GET("/users/{user}")
    Call<Object  > listRepos(@Path("user") String user);

    /**
     * 测试订单网址
     * 请求参数中@Path,是直接替换掉请求路径上的字段名；
     * 。@Query,是请求参数,单指一个，
     * ；@QueryMap,请求有多个参数时用
     * @param foodId
     * @return
     */
    @GET("/dingcan/config/orderAdd")
    Observable<Object > dingCan(@Query("foodId") String foodId);

    @GET("/dingcan/config/orderAdd")
    Call<Response> dingCan1(@Query("foodId") String foodId);

    @GET("/dingcan/config/orderAdd")
    Observable<Object> dingCan(@QueryMap TreeMap<String ,String > params);
}
