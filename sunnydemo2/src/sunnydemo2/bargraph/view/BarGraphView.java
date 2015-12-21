package sunnydemo2.bargraph.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2015/11/13.
 * Annotion:柱形图控件
 */
public class BarGraphView extends View {

    private static final int CANVAS_BG_COLOR_DEF = Color.parseColor("#FFAAAAAA");
    private static final int BAR_CONTENT_COLOR_DEF = Color.RED;
    private static final int VIEW_HEIGHT_DEF = 200;
    private static final int RECT_SPACE_DEF = 5;//柱形条之间的间距
    private static float mScale = 100.0f;//划分比例，eg:（传入的值）/100.0 =在该图表中的比例值
    /**
     * 默认标题字体颜色
     */
    private static final int CHART_TITLE_COLOR_DEF = BAR_CONTENT_COLOR_DEF;
    /**
     * 默认标题字体大小
     */
    private static final int CHART_TITLE_SIZE_DEF = 10;

    private int mChartBgColor;
    private int mChartWidth;
    private int mChartHeight;
    private int mDensity;
    private int mChartBarContentColor;//图表中柱状图的填充色
    private int mChartTitleColor;
    private int mChartTitleTextSize;

    private int mScreenHeight;
    private int mScreenWidth;
    private int mChartViewHeight;//

    private int mPaintTimes = 0;//当前第几次绘制
    private int mTotalPaintTimes = 1000;//总共分100次画完
    private int mUnitValue=0;

    private boolean isShowValue = true;
    private String mTitle;

    private Resources mResources;
    private Context mContext;


    private Paint mTitlePaint;//标题画笔
    private Paint mAlixPaint;//坐标轴画笔
    private Paint mTagPaint;//横坐标数据标记画笔
    private Paint mContentPaint;//条形块画笔

    private List<Integer> xPoint = new ArrayList<>();//x轴上的坐标数
    private List<Integer> values = new ArrayList<>();//y轴上的值

    public void setxPoint(List<Integer> xPoint) {
        this.xPoint = xPoint;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public void setIsShowValue(boolean isShowValue) {
        this.isShowValue = isShowValue;
    }

    public void setmChartTitleColor(int mChartTitleColor) {
        this.mChartTitleColor = mChartTitleColor;
    }

    public void setmChartTitleTextSize(int mChartTitleTextSize) {
        this.mChartTitleTextSize = mChartTitleTextSize;
    }

    public BarGraphView(Context context) {
        super(context);
        initView(context);
    }

    public BarGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDensity = (int) context.getResources().getDisplayMetrics().density;
        initTypeArray(context, attrs);
        initView(context);
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BarGraphView);
        mChartBgColor = typedArray.getColor(R.styleable.BarGraphView_chart_bg, CANVAS_BG_COLOR_DEF);
        mChartBarContentColor = typedArray.getColor(R.styleable.BarGraphView_chart_bar_color, BAR_CONTENT_COLOR_DEF);
        mChartHeight = (int) typedArray.getDimension(R.styleable.BarGraphView_chart_height, VIEW_HEIGHT_DEF * mDensity);
        isShowValue = typedArray.getBoolean(R.styleable.BarGraphView_chart_bar_showValue, true);
        mTitle = typedArray.getString(R.styleable.BarGraphView_chart_title);
        mChartTitleColor = typedArray.getColor(R.styleable.BarGraphView_chart_title_color, CHART_TITLE_COLOR_DEF);
        mChartTitleTextSize = (int) typedArray.getDimension(R.styleable.BarGraphView_chart_title_textsize, CHART_TITLE_SIZE_DEF * mDensity);


        typedArray.recycle();
    }

    private void initView(Context context) {

        this.mContext = context;
        mResources = mContext.getResources();

        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mScreenHeight = windowManager.getDefaultDisplay().getHeight();
        mScreenWidth = windowManager.getDefaultDisplay().getWidth();
        mChartViewHeight = getHeight();

        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mTitlePaint = new Paint();
        mTitlePaint.setColor(mChartTitleColor);
        mTitlePaint.setAntiAlias(true);
        mTitlePaint.setStyle(Paint.Style.FILL);
        mTitlePaint.setTextSize(mChartTitleTextSize);
        mTitlePaint.setTypeface(Typeface.DEFAULT_BOLD);

        mAlixPaint = new Paint();
        mAlixPaint.setColor(Color.WHITE);
        mAlixPaint.setAntiAlias(true);
        mAlixPaint.setTextSize(20);
        mAlixPaint.setStyle(Paint.Style.FILL);
        mAlixPaint.setTypeface(Typeface.DEFAULT);

        mTagPaint = new Paint();
        mTagPaint.setColor(Color.GRAY);
        mTagPaint.setAntiAlias(true);
        mTagPaint.setTextSize(20);
        mTagPaint.setStyle(Paint.Style.FILL);
        mTagPaint.setTypeface(Typeface.DEFAULT);

        mContentPaint = new Paint();
        mContentPaint.setColor(mChartBarContentColor);
        mContentPaint.setAntiAlias(true);
        mContentPaint.setTextSize(10);
        mContentPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            width = widthMeasureSpec;
        } else if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec) {
        int height = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = heightMeasureSpec;
        }
        return height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaintTimes++;//绘制次数递增
        canvas.drawColor(mChartBgColor);
        if (!TextUtils.isEmpty(mTitle))
            canvas.drawText(mTitle, 100, 50, mTitlePaint);

        int space = 10;//间距

        int startX = 100;
        int endX = startX + space;

        mChartViewHeight = getHeight();
        int startY = mChartViewHeight - 20 * mDensity;//Y轴起始点
        int endY = mChartViewHeight - 20 * mDensity;//Y轴结束点
        mUnitValue = (int) (getHeight()/mScale);


        //画X轴
        int xAlixStart = 50;
        int xAlixEnd = mScreenWidth - xAlixStart;
        canvas.drawLine(xAlixStart, endY, xAlixEnd, endY, mAlixPaint);

        //画X轴上的坐标
        canvas.drawText("00:00", startX - space * mDensity, endY + space * mDensity, mTagPaint);

        //画柱条
        for (int i = 0; i < xPoint.size(); i++) {
            int value = 0;
            if (i >= values.size()) {
                value = values.get(values.size() - 1);
            } else {
                value = values.get(i);
            }
            //当要加上动画时，算出每次柱形所画高度
            //  int yStart = (int) ((mChartViewHeight - mChartViewHeight *(value/mScale))/mTotalPaintTimes*mPaintTimes);

            canvas.drawRect(startX, mChartViewHeight - mChartViewHeight * (value / mScale), endX, endY, mContentPaint);

            //在柱形上写上数值
            if (isShowValue) {
                canvas.drawText(value + "", startX, mChartViewHeight - mChartViewHeight * (value / mScale) - 5, mTagPaint);
            }
            startX = endX + space;
            endX = startX + space;
            startY = startY - space;
        }

        //画X轴上的最后一个坐标
        canvas.drawText("23:00", startX - space * mDensity, endY + space * mDensity, mTagPaint);

        //只要当前次数小于绘制的总次数，则循环onDraw()
        /*if (mPaintTimes < mTotalPaintTimes) {
            invalidate();
        }*/


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       /* float x = 0;
        float y = 0;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                Toast.makeText(mContext,"x:"+x+",y="+y,Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_UP:
                break;

            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                break;
        }*/
        return true;
    }
}
