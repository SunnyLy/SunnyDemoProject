package sunnydemo2.androidl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/12/16.
 * RecyclerView item分割线
 */
public class ItemDiliver extends RecyclerView.ItemDecoration {
    private Context mContext;
    private Drawable mDrawable;

    public ItemDiliver(Context context,int resId) {
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mDrawable = context.getResources().getDrawable(resId, null);
        }else{
            mDrawable = context.getResources().getDrawable(resId);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int paddingLeft = parent.getPaddingLeft();
        int paddingRight = parent.getPaddingRight();
        int paddingTop = parent.getPaddingTop();
        int paddingBottom = parent.getPaddingBottom();

        int right = parent.getWidth() - paddingRight;
        int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            //计算出分割线所要绘制的区域
            int top = childView.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(paddingLeft,top,right,bottom);
            mDrawable.draw(c);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,0,0,mDrawable.getIntrinsicWidth());
    }
}
