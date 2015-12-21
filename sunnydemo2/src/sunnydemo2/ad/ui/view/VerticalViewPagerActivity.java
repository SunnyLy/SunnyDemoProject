package sunnydemo2.ad.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2015/11/20.
 * Annotion:上下滑动翻页Activity
 */
public class VerticalViewPagerActivity extends FragmentActivity {

 /*   private VerticalViewPager vertical_viewpager;
    private PagerAdapter mPagerAdapter;*/
    private List<View> mViewLists = new ArrayList<>();

    private DirectionalViewPager mViewPager;

    public static void startVerticalViewPagerActivity(Context context){
        Intent targetItent = new Intent(context,VerticalViewPagerActivity.class);
        context.startActivity(targetItent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_viewpager);

        initParams();
    }

    private void initParams() {
        mViewLists.clear();
        for(int i=0;i<5;i++){
            TextView textView = new TextView(this);
            textView.setText("textView:"+i);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mViewLists.add(textView);
        }

        TestFragmentAdapter adpater = new TestFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adpater);

    }

    @Override
    public void onContentChanged() {
        mViewPager = (DirectionalViewPager) findViewById(R.id.viewPager);
        mViewPager.setOrientation(DirectionalViewPager.VERTICAL);
    }
}
