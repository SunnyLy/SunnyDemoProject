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
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sunnydemo2.ad.MainActivity;
import sunnydemo2.androidl.adapter.MasonryAdapter;
import sunnydemo2.androidl.adapter.SunnyRecyclerViewAdapter;
import sunnydemo2.androidl.callback.RecycleItemClickListener;
import sunnydemo2.androidl.model.Product;

/**
 * Created by sunny on 2015/12/15.
 * Android5.0 新控件
 */
public class AndroidLActivity extends Activity implements SunnyRecyclerViewAdapter.OnItemClickListener
        , SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = AndroidLActivity.class.getSimpleName();

    private Button mBtnChange;
    private RecyclerView mRecyclerView;
    private SunnyRecyclerViewAdapter mAdapter;
    private String[] datas;
    private LinearLayoutManager mLinearLayoutManager;
    private StaggeredGridLayoutManager mStaggerGridLayoutManager;
    private ManagerType mMangerType;

    private List<Product> productList;
    private RecyclerView.ItemDecoration mItemDecoration;
    private SpacesItemDecoration decoration;


    private SwipeRefreshLayout mSwipRefreshLayout;

    private enum ManagerType {
        LINAER_LAYOUT, GRID, STRAGGER_GRID
    }

    public static void startAndroidLActivity(Context context) {
        Intent targetIntent = new Intent(context, AndroidLActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidl);
        setTitle("Android5.0新控件");

    }

    private void getData() {
        createDatas();
        mAdapter = new SunnyRecyclerViewAdapter(datas, this);
        mRecyclerView.setAdapter(mAdapter);
        mMangerType = ManagerType.LINAER_LAYOUT;
    }

    private void createDatas() {
        datas = getResources().getStringArray(R.array.android_5_new_views);
    }

    @Override
    public void onContentChanged() {
        mSwipRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mBtnChange = (Button) findViewById(R.id.btn_recyclerView);
        initParams();
        bindView();
        setSwipRefreshLayout();
        setRecyclerView();
        //setStraggerGridRecyclerView();
    }

    private void initParams() {
        mItemDecoration = new ItemDiliver(this, R.drawable.item_diliver);
        decoration = new SpacesItemDecoration(16);
    }

    private void bindView() {
        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (mMangerType) {
                    case LINAER_LAYOUT:
                        mMangerType = ManagerType.STRAGGER_GRID;
                        mBtnChange.setText("RecyclerView+StaggeredGridLayoutManager");
                        setStraggerGridRecyclerView();
                        break;
                    case STRAGGER_GRID:
                        setRecyclerView();
                        mBtnChange.setText("RecyclerView+LinearLayoutManager");
                        mMangerType = ManagerType.LINAER_LAYOUT;
                        break;
                    case GRID:
                        break;
                }
            }
        });
    }

    /**
     * RecyclerView实现瀑布流布局
     */
    private void setStraggerGridRecyclerView() {
        if (mRecyclerView != null) {
            //  mRecyclerView.setHasFixedSize(true);
            mStaggerGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mStaggerGridLayoutManager);
            initData();
            MasonryAdapter adapter = new MasonryAdapter(productList, itemClickListener);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.removeItemDecoration(mItemDecoration);
            mRecyclerView.removeItemDecoration(decoration);
            mRecyclerView.addItemDecoration(decoration);
        }


    }

    RecycleItemClickListener itemClickListener = new RecycleItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
//                Log.e("position","="+position);
//                Toast.makeText(MainActivity.this, productList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
               /* Intent intent=new Intent();
                intent.setClass(MainActivity.this,ProductDetailActivity.class);
                startActivity(intent);*/
        }
    };

    private void initData() {
        productList = new ArrayList<Product>();
        Product p1 = new Product(R.drawable.pager2, "我是照片1");
        productList.add(p1);
        Product p2 = new Product(R.drawable.pager3, "我是照片2");
        productList.add(p2);
        Product p3 = new Product(R.drawable.pager4, "我是照片3");
        productList.add(p3);
        Product p4 = new Product(R.drawable.pager5, "我是照片4");
        productList.add(p4);
        Product p5 = new Product(R.drawable.p5, "我是照片5");
        productList.add(p5);
        Product p6 = new Product(R.drawable.p6, "我是照片6");
        productList.add(p6);
        Product p7 = new Product(R.drawable.p2, "我是照片7");
        productList.add(p7);
        Product p8 = new Product(R.drawable.p1, "我是照片8");
        productList.add(p8);
        Product p9 = new Product(R.drawable.p4, "我是照片9");
        productList.add(p9);
        Product p10 = new Product(R.drawable.p6, "我是照片10");
        productList.add(p10);
        Product p11 = new Product(R.drawable.p3, "我是照片11");
        productList.add(p11);

        Collections.shuffle(productList);//打乱顺序

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
            getData();
            mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            //添加布局管理
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            //添加分割线
            mRecyclerView.removeItemDecoration(mItemDecoration);
            mRecyclerView.removeItemDecoration(decoration);
            mRecyclerView.addItemDecoration(mItemDecoration);
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
                case 6:
                    TestPrefeerenceActivity.startTestPreferenceActivity(this);
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
        }, 3000);
    }
}
