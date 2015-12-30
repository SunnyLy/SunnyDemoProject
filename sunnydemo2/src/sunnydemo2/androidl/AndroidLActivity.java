package sunnydemo2.androidl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.smartbracelet.sunny.sunnydemo2.R;

import sunnydemo2.androidl.adapter.SunnyRecyclerViewAdapter;

/**
 * Created by sunny on 2015/12/15.
 * Android5.0 新控件
 */
public class AndroidLActivity extends Activity implements SunnyRecyclerViewAdapter.OnItemClickListener
        , SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = AndroidLActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private SunnyRecyclerViewAdapter mAdapter;
    private String[] datas;
    private LinearLayoutManager mLinearLayoutManager;

    private SwipeRefreshLayout mSwipRefreshLayout;

    public static void startAndroidLActivity(Context context) {
        Intent targetIntent = new Intent(context, AndroidLActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidl);
        setTitle("Android5.0新控件");
        getData();
    }

    private void getData() {
        createDatas();
        mAdapter = new SunnyRecyclerViewAdapter(datas, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void createDatas() {
        datas = getResources().getStringArray(R.array.android_5_new_views);
    }

    @Override
    public void onContentChanged() {
        mSwipRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        setSwipRefreshLayout();
        setRecyclerView();
    }

    private void setSwipRefreshLayout() {
        if (mSwipRefreshLayout != null) {
            mSwipRefreshLayout.setProgressBackgroundColor(R.color.swipfreshlistview);
            mSwipRefreshLayout.setOnRefreshListener(this);
        }
    }

    private void setRecyclerView() {
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
            mLinearLayoutManager = new LinearLayoutManager(this);
            //可以设置RecyclerView的方向，默认是垂直
            mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            //添加布局管理
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            //添加分割线
            mRecyclerView.addItemDecoration(new ItemDiliver(this, R.drawable.item_diliver));
        }
    }

    @Override
    public void onItemClick(int viewId, int position) {
        if (viewId == R.id.item_layout) {
            switch (position) {
                case 0:
                    Toast.makeText(this, "当前界面列表就是用RecyclerView写的", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    TintActivity.startTintActivity(this);
                    break;
                case 2:
                    CardViewActivity.startCardViewActivity(this);
                    break;
                case 3:
                    PaletteActivity.startPaletteActivity(this);
                    break;
                case 4:
                    RippleDrawableActivity.startRippleDrawableActivity(this);
                    break;
                case 5:
                    ToolbarActivity.startToolbarActivity(this);
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {

        //方便测试，当下拉刷新时，过3s中停止
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipRefreshLayout.setRefreshing(false);
            }
        },3000);
    }
}
