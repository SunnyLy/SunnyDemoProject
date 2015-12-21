package sunnydemo2.ad.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/11/12.
 * Annotion:广告轮播中的圆点指示器
 * 在xml中用
 */
public class ViewPagerIndicator extends RelativeLayout {

    private Context mContext;
    private Resources mResoures;
    private int mDensity;

    private PagerAdapter mPageAdapter;
    private ImageView mIndicator;
    private LinearLayout mLinearLayout;

    private ShapeDrawable mChooseShapeDrawable;
    private ShapeDrawable mUnChooseShapeDrawable;

    //圆点属性
    private int mItemWidth;//圆点宽度
    private int mItemMargin;//圆点间间距
    private int mChooseColor;//圆点选中颜色
    private int mUnChooseColor;//圆点未选中颜色


    private static final int ITEM_MARGIN_DP = 10;//默认圆点间间距为10dp
    private static final int ITEM_WIDTH_DP = 6;//默认圆点的宽度为6dp
    private static int CHOOSE_COLOR_DEF = Color.parseColor("#FF4B91F6");
    private static int UNCHOOSE_COLOR_DEF = Color.parseColor("#FFFF0000");

    public ViewPagerIndicator(Context context) {
        this(context,null);
    }
    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mResoures = context.getResources();
        mDensity = (int) mResoures.getDisplayMetrics().density;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mItemWidth = (int) typedArray.getDimension(R.styleable.ViewPagerIndicator_item_width,ITEM_WIDTH_DP*mDensity);
        mItemMargin = (int) typedArray.getDimension(R.styleable.ViewPagerIndicator_item_margin,ITEM_MARGIN_DP*mDensity);
        mChooseColor = typedArray.getColor(R.styleable.ViewPagerIndicator_item_choose_color,CHOOSE_COLOR_DEF);
        mUnChooseColor = typedArray.getColor(R.styleable.ViewPagerIndicator_item_unchoose_color,UNCHOOSE_COLOR_DEF);

        //加入圆点的水平线性布局
        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,mItemWidth));
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(mLinearLayout);

        mChooseShapeDrawable = genarateShapeDrawable(mChooseColor);
        mUnChooseShapeDrawable = genarateShapeDrawable(mUnChooseColor);


        mIndicator = genareteIndicator();
        addView(mIndicator);//把选中的圆点加到布局上

        typedArray.recycle();
    }

    /**
     * 生成选中的圆 点
     *
     * @return
     */
    private ImageView genareteIndicator() {
        ImageViewShape imageViewShape = new ImageViewShape(mContext,mChooseShapeDrawable);
        imageViewShape.setLayoutParams(genareteLayoutParams());
        return imageViewShape;
    }

    /**
     * 生成未选中的圆点
     * @return
     */
    private ImageViewShape genareteUnChooseChildView() {
        ImageViewShape unChooseView = new ImageViewShape(mContext,mUnChooseShapeDrawable);
        unChooseView.setLayoutParams(genareteLayoutParams());
        return unChooseView;
    }

    private LinearLayout.LayoutParams genareteLayoutParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mItemWidth,mItemWidth);
        layoutParams.rightMargin = mItemMargin;
        return layoutParams;
    }


    /**
     * 根据颜色生成相对就的圆点Drawable
     * @param color
     * @return
     */
    private ShapeDrawable genarateShapeDrawable(int color) {

        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(color);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);//画实心
        shapeDrawable.getPaint().setAntiAlias(true);//除去边缘锯齿
        shapeDrawable.setBounds(new Rect(0, 0, mItemWidth, mItemWidth));//画大小
        return shapeDrawable;
    }


    public void setViewPager(ViewPager viewPager){
        mPageAdapter = viewPager.getAdapter();
        if(mPageAdapter == null)
            throw new NullPointerException("please set Adapter before setViewPager()");
        redrawChilds();
        mPageAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                redrawChilds();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                View child = mLinearLayout.getChildAt(position);
                if (child != null) {

                    Log.e("viewpager", "child.getLeft:" + child.getLeft() + ",positionOffset:" + positionOffset);
                   /* TranslateAnimation translateAnimation = new TranslateAnimation(child.getLeft(),
                            child.getLeft()+positionOffset*(mItemMargin+mItemWidth),0,0);
                    translateAnimation.setInterpolator(new OvershootInterpolator());
                    translateAnimation.setFillAfter(true);
                    mIndicator.startAnimation(translateAnimation);*/
                    mIndicator.setTranslationX(child.getLeft() + positionOffset * (mItemMargin + mItemWidth));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 重绘圆点
     */
    private void redrawChilds() {

        int curChildCount = mLinearLayout.getChildCount();
        int delta = curChildCount - mPageAdapter.getCount();

        if(delta < 0){
            //第一次进来的时候，因为mLinearLayout里面为0，所以delta<0
            for(int i=0;i<Math.abs(delta);i++){
              mLinearLayout.addView(genareteUnChooseChildView());
            }
        }else if(delta > 0){
            for(int i=0;i<delta;i++){
                mLinearLayout.removeViewAt(curChildCount-i-1);
            }
        }

        if(mPageAdapter.getCount() <= 1){
            setVisibility(View.INVISIBLE);
        }else{
            setVisibility(View.VISIBLE);
        }

    }


    private class ImageViewShape extends ImageView{

        ShapeDrawable shapeDrawable;
        public ImageViewShape(Context context,ShapeDrawable shapeDrawable) {
            super(context);
            this.shapeDrawable = shapeDrawable;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            shapeDrawable.draw(canvas);
        }
    }
}
