package sunnydemo2.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2016/3/9.
 * Annotion:视图层实现类，
 */
public abstract class AbsView implements IView {
    private SparseArray<View> mBindViews = new SparseArray<>();
    private Context context;
    private View rootView;

    @Override
    public void createView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstance) {
        rootView = inflater.inflate(getLayoutId(), viewGroup, false);
        context = rootView.getContext();
    }

    @Override
    public void initWidget() {
        //TODO 在这里是一个空方法，由子类去实现
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    /**
     * 把子控件用一个集合存起来
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T bindView(int viewId) {

        T t = (T) mBindViews.get(viewId);
        if (t == null) {
            t = (T) rootView.findViewById(viewId);
            mBindViews.put(viewId, t);
        }

        return t;
    }


    public <T extends View> T getViewById(int viewId) {
        T view = (T) rootView.findViewById(viewId);
        return view;
    }

    /**
     * 获取布局文件id
     *
     * @return
     */
    public abstract int getLayoutId();
}
