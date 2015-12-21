package sunnydemo2.network.biz;

/**
 * Created by sunny on 2015/11/30.
 * Annotion:
 */
public interface ICallback<T> {

    void onSuccess(T obj);
    void onFailure(int code,String msg);
}
