package android.support.v4.view1;

import android.support.v4.view.*;
import android.view.View;

/**
 * Created by sunny on 2015/11/20.
 * Annotion:
 */
public class ViewPagerAdapter extends android.support.v4.view.PagerAdapter {
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return false;
    }

    public interface  ViewPagerDataSetObserver {
        void onDataSetChanged();
    }


    public void setDataObserver(ViewPagerDataSetObserver observer){
        observer.onDataSetChanged();
    }
}
