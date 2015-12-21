package sunnydemo2.ad.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.List;

import sunnydemo2.ad.adapter.ADViewPagerAdapter;
import sunnydemo2.ad.widget.ViewPagerIndicator;

/**
 * Created by sunny on 2015/11/12.
 * Annotion:广告轮播
 */
public class CarouselADActivity extends Activity {

    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;
    private ADViewPagerAdapter mViewPagerAdapter;

    private List<View> mViewLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        initParams();
    }

    private void initParams() {


        View view1 = LayoutInflater.from(this).inflate(R.layout.viewpager_1,null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.viewpager_2,null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.viewpager_3,null);

        mViewLists.clear();
        mViewLists.add(view1);
        mViewLists.add(view2);
        mViewLists.add(view3);
        mViewPagerAdapter = new ADViewPagerAdapter(this,mViewLists);
        mViewPager.setAdapter(mViewPagerAdapter);
        mIndicator.setViewPager(mViewPager);
    }

    @Override
    public void onContentChanged() {
        mViewPager = (ViewPager) findViewById(R.id.common_viewpager_ad);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.common_indicator);
    }
}

