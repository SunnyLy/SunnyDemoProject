package sunnydemo2.mvp.presenter;

import sunnydemo2.mvp.base.BaseActivity;
import sunnydemo2.mvp.view.LoginView;

/**
 * Created by sunny on 2016/3/9.
 * Annotion:登录Presenter
 */
public class LoginAtyPresenter extends BaseActivity<LoginView> {
    @Override
    public Class getResenterClass() {
        return LoginView.class;
    }
}
