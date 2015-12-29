package sunnydemo2.rxjava.api;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import rx.Observable;
import sunnydemo2.rxjava.biz.GitHubService;

/**
 * Created by sunny on 2015/12/29.
 * Retrofit用RestAdapter把接口变成实现类
 */
public class GitHubApi {

    /**
     * 把GitHubService变成实现类
     *
     */

    public Observable<String > getUser(String url, String user){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .build();
        GitHubService gitHubService = retrofit.create(GitHubService.class);
       return  gitHubService.listRepos(user);
    }


    /**
     * 订餐。
     * @param serverUrl
     * @param foodId
     * @return
     */
    public Observable<String > dingCan(String serverUrl,String foodId){
        Retrofit foodRetrofit = new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create())//返回数据解析方式
                .build();

        GitHubService gitHubService = foodRetrofit.create(GitHubService.class);
        return gitHubService.dingCan(foodId);
    }

}
