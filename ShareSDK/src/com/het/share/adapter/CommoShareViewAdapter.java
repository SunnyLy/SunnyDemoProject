package com.het.share.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.het.share.R;
import com.het.share.model.CommonShareItemModel;
import com.het.share.widget.CommonShareView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2016/1/14.
 * Annotion:
 */
public class CommoShareViewAdapter extends BaseAdapter {
    private final int PAGE_ITEM_NUM = 8;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<View> shareViewList;
    private List<CommonShareItemModel> models = new ArrayList<>();

    private WindowManager mWindowManager;
    private float density;

    public CommoShareViewAdapter(Context ctxt, List<CommonShareItemModel> itemModels) {
        this.mContext = ctxt;
        mLayoutInflater = LayoutInflater.from(ctxt);
        this.shareViewList = new ArrayList<>();
        this.models = itemModels;
        initWindowManger();
    }

    private void initWindowManger() {
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        density = displayMetrics.density;
    }

    @Override
    public int getCount() {
        int pageCount = 0;
        if (models != null) {
            //每页8项，判断共可分多少页
            int size = models.size();
            pageCount = size % PAGE_ITEM_NUM == 0 ? size / PAGE_ITEM_NUM : size / PAGE_ITEM_NUM + 1;
        }
        return pageCount;
    }

    @Override
    public Object getItem(int position) {
        return shareViewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int curPosition = position;
        View view = mLayoutInflater.inflate(R.layout.common_share_widget_gridview, null);
        GridView gridView = (GridView) view.findViewById(R.id.share_item_gridView);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) gridView
                .getLayoutParams();
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        gridView.setLayoutParams(params);

        gridView.setNumColumns(4);
        gridView.setVerticalSpacing((int) (12 * density));
        gridView.setHorizontalSpacing(0);

        shareViewList.add(view);

        //再里面就是GridView对分享平台的适配
        CommonShareGridViewAdapter gridViewAdapter;
        if(position == getCount() - 1){
            gridViewAdapter = new CommonShareGridViewAdapter(mContext,models.subList(position*PAGE_ITEM_NUM,models.size()));
        }else{
            gridViewAdapter = new CommonShareGridViewAdapter(mContext,models.subList(position*PAGE_ITEM_NUM,(position + 1)*PAGE_ITEM_NUM));
        }
        gridView.setAdapter(gridViewAdapter);

        return view;
    }


}
