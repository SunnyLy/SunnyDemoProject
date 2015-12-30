package sunnydemo2.imageloader.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.List;

import sunnydemo2.utils.LogUtils;

/**
 * Created by sunny on 2015/12/21.
 * Annotion:
 */
public class GridviewAdapter extends BaseAdapter {
    private List<String> mImageLists = new ArrayList<>();
    private LayoutInflater mInflater;
    private View.OnClickListener mOnClickListener;

    public GridviewAdapter(Context context, List<String> imageList, View.OnClickListener clickListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnClickListener = clickListener;
        if (imageList != null && imageList.size() > 0) {
            mImageLists.clear();
            mImageLists.addAll(imageList);
        }

    }

    @Override
    public int getCount() {
        return mImageLists.size();
    }

    @Override
    public String getItem(int position) {
        return mImageLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold holder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_choose_picture_toshow, null);
            holder = new ViewHold();
            holder.mPicture = (SimpleDraweeView) convertView.findViewById(R.id.picture_show);
            holder.mPictureInfo = (TextView) convertView.findViewById(R.id.picture_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHold) convertView.getTag();
        }

        if (position == 0) {
            holder.mPictureInfo.setVisibility(View.VISIBLE);
            holder.mPicture.setImageURI(Uri.parse("res://drawable/" + R.drawable.takephoto));
        } else {
            holder.mPicture.setImageURI(Uri.parse("file://" + mImageLists.get(position)));
            LogUtils.e("position:"+position+",url:content://"+mImageLists.get(position));
            holder.mPictureInfo.setVisibility(View.GONE);
        }
        holder.mPicture.setOnClickListener(mOnClickListener);
        return convertView;

    }

    private class ViewHold {

        SimpleDraweeView mPicture;
        TextView mPictureInfo;
    }
}
