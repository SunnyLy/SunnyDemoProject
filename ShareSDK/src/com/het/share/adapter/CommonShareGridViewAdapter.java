package com.het.share.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.het.share.R;
import com.het.share.model.CommonShareItemModel;
import com.het.share.widget.CommonShareView;
import com.sina.weibo.sdk.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2016/1/14.
 * ViewPager里头的GridView适配器
 */
public class CommonShareGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<CommonShareItemModel> shareViewList;

    public CommonShareGridViewAdapter(Context mContext, List<CommonShareItemModel> shareViewList) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        if (shareViewList != null && shareViewList.size() > 0) {
            this.shareViewList = shareViewList;
            notifyDataSetChanged();
        }else{
            this.shareViewList = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return shareViewList.size();
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
        ViewHolder holder = null;
        CommonShareItemModel itemModel = shareViewList.get(position);
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.common_share_widget_share_item,null);
            holder.mShareView = (ImageView) convertView.findViewById(R.id.share_item_drawable);
            holder.mShareText = (TextView) convertView.findViewById(R.id.share_item_title);
            holder.share_item_root = (LinearLayout) convertView.findViewById(R.id.share_item_root);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mShareView.setImageDrawable(itemModel.getmShareDrawable());
        holder.mShareText.setText(itemModel.getmShareTitle());
        holder.share_item_root.setOnClickListener(itemModel.getOnClick());
        return convertView;
    }

    class ViewHolder{

        LinearLayout share_item_root;
        ImageView mShareView;
        TextView mShareText;

    }
}
