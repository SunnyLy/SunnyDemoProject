package sunnydemo2.ad.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2015/11/12.
 * Annotion:
 */
public class ADViewPagerAdapter extends PagerAdapter {

    private List<View> views = new ArrayList<>();
    private Context mContext;

    public ADViewPagerAdapter(Context context,List<View> views){
        this.mContext = context;
        if(views!=null&&views.size()>0){
            this.views.clear();
            this.views.addAll(views);
        }

    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
