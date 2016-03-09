package sunnydemo2.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import sunnydemo2.mvp.view.IView;

/**
 * Created by sunny on 2016/3/9.
 * Annotion:Presenter的实现基类
 */
public abstract class ActivityPresenter<T extends IView> extends Activity {

    private T mView;
    private Context context;

    /**
     * 由子类去实现它，通过newInstance来获取实例化对象
     * @return
     */
    public abstract Class<T> getResenterClass();

    public ActivityPresenter(){
        try {
            mView = getResenterClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if(mView != null){
            mView.createView(getLayoutInflater(),null,savedInstanceState);
        }
        setContentView(mView.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
