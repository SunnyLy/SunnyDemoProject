package sunnydemo2.mvp.base;

import sunnydemo2.mvp.view.IView;

/**
 * Created by sunny on 2016/3/9.
 * Annotion:
 */
public abstract class BaseActivity<T extends IView> extends ActivityPresenter<T> {
}
