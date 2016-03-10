package sunnydemo2.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import sunnydemo2.mvp.base.BaseActivity;
import sunnydemo2.mvp.view.LoginView;

/**
 * Created by sunny on 2016/3/9.
 * Annotion:登录Presenter
 */
public class LoginAtyPresenter extends BaseActivity<LoginView> {

    public static void startLoginAtyPresenter(Context context){
        Intent targetIntent = new Intent(context,LoginAtyPresenter.class);
        context.startActivity(targetIntent);
    }
    @Override
    public Class getResenterClass() {
        return LoginView.class;
    }
}
