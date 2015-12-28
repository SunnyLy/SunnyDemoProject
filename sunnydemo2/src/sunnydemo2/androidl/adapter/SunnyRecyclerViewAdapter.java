package sunnydemo2.androidl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/12/15.
 * RecyclerView适配器
 */
public class SunnyRecyclerViewAdapter extends RecyclerView.Adapter<SunnyRecyclerViewAdapter.SunnyViewHolder> {

    private String[] mDataList;
    private OnItemClickListener mOnClickListener;

    public SunnyRecyclerViewAdapter(String[] datas, OnItemClickListener clickListener){
        this.mOnClickListener = clickListener;
        this.mDataList = datas;
    }


    @Override
    public SunnyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,parent,false);
        return new SunnyViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(SunnyViewHolder holder, final int position) {
        TextView textView = (TextView) holder.mTextView.findViewById(R.id.item_btn);
        textView.setText(mDataList[position]);

        if(position == 4){
            textView.setBackgroundDrawable(holder.mTextView.getResources().getDrawable(R.drawable.rippledrawable));
        }

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnClickListener != null){
                    mOnClickListener.onItemClick(v.getId(),position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.length;
    }

    public class SunnyViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout mTextView;
        public SunnyViewHolder(View itemView) {
            super(itemView);
            mTextView = (LinearLayout) itemView;
        }
    }


    public interface OnItemClickListener{
        void onItemClick(int viewId,int position);
    }
}
