package sunnydemo2.bargraph.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * Created by sunny on 2015/11/20.
 * Annotion:自定义垂直ViewPager,
 */
public class SunnyVerticalViewPager extends ViewGroup {
    private Context mContext;
    private Scroller mScroller;
    private VelocityTracker mVelocityTacker;
    private int lastMotionY;
    private int startMotionY;
    private int count = 1;
    private int totalHeight;

    public SunnyVerticalViewPager(Context context) {
        this(context, null);
    }

    public SunnyVerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        initParams(context, attrs);
    }

    private void initParams(Context context, AttributeSet attrs) {

        mContext = context;
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int totalHeight = 0;
        int childCount = getChildCount();
        //循环遍历子View,对子View重新进行onLayout
        if (childCount <= 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.layout(l, totalHeight, r, totalHeight + b);
            totalHeight += b;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTacker == null) {
            mVelocityTacker = VelocityTracker.obtain();
        }
        mVelocityTacker.addMovement(event);

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }

                startMotionY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //移动，计算纵轴上2点的差的值
                //如果deltaY > 0 从上往下滑动
                //如果deltaY < 0 从下往上滑动
                int deltaY = getScrollY();
                if (Math.abs(deltaY) < 100) {
                    break;
                }
                scrollTo(0, deltaY);
                invalidate();

                break;
            case MotionEvent.ACTION_UP:
                if (mVelocityTacker != null) {
                    mVelocityTacker.clear();
                    mVelocityTacker.recycle();
                    mVelocityTacker = null;
                }
                lastMotionY = (int) event.getY();
                int delta = lastMotionY - startMotionY;
                if (delta == 0) {
                    break;
                }
                int childCount = getChildCount();
                View childView = getChildAt(0);
                int height = childView.getHeight();
                int scrollY = getScrollY();
                if(scrollY == 0){
                    count = 2;
                }
                if (scrollY > 0 && (scrollY%height==0)) {
                    count += 1;
                }
                if (delta < -100) {
                    //向上滑动
                    //获取当前Y轴上已经滚动的距离,getScrollY()是指视图顶部滚动的距离
                   // mScroller.startScroll(0, 0, 0, childView.getHeight());
                    totalHeight = height+height*(count - 1);
                    scrollTo(0,height*(count - 1));
                    if(count == childCount +1){
                        Toast.makeText(mContext,"已是最后一页",Toast.LENGTH_SHORT).show();
                        break;
                    }
                } else if (delta > 100) {
                    count = totalHeight/height;
                    //向下滑动
                    if(count == 1){
                        Toast.makeText(mContext,"已是第一页",Toast.LENGTH_SHORT).show();
                        break;
                    }
                   // mScroller.startScroll(0, (childView.getHeight() * count), 0, (childView.getHeight()) * (count-1));
                    scrollTo(0,totalHeight-height*(count-1));

                }
             //   invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            //mScroller.computeScrollOffset()获取视图滚动的最新位置 ，
            //如果返回True,则说明动画还未结束。
            scrollTo(0, mScroller.getCurrY());
        }
    }
}
