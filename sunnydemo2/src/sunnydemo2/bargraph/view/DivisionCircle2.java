package sunnydemo2.bargraph.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/11/16.
 * Annotion:刻度盘2
 */
public class DivisionCircle2 extends View {

    private Context mContext;
    private static final int BG_COLOR_DEF = 0xFFFF6F3B;
    private static final int RADIUS_DEF = 150;


    private int radius ;
    private int divided = 90;
    private int agle = 2;
    private int circleX = 200;
    private int circleY = 200;

    private int mCurrentTimes = 0;//当前绘制次数
    private int mTotalTimes = 10;//总共绘制次数
    private int mUnitScale = 10;//刻度之间的间距

    private int mCurrentValue;//值
    private int mMinValue;//最小值
    private int mMaxValue;//最大值
    private int mBgColor;

    private Paint mValuePaint;
    private Paint mTagPaint;
    private Paint mBigLineBottomPaint;
    private Paint mBigLineTopPaint;
    private Paint mScaleBigPaint;
    private Paint mScaleSmallPaint;
    private Paint mNumPaint;
    private Paint mBigTextPaint;

    public void setCurrentValue(int mValue) {
        this.mCurrentValue = mValue;
        invalidate();
    }

    public void setMinValue(int mMinValue) {
        this.mMinValue = mMinValue;
    }

    public void setMaxValue(int mMaxValue) {
        this.mMaxValue = mMaxValue;
    }

    public DivisionCircle2(Context context) {
        this(context, null);
    }

    public DivisionCircle2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DivisionCircle2);
        mMinValue = typedArray.getInt(R.styleable.DivisionCircle2_min_value,0);
        mMaxValue = typedArray.getInt(R.styleable.DivisionCircle2_max_value,150);
        mBgColor = typedArray.getColor(R.styleable.DivisionCircle2_bg_color,BG_COLOR_DEF);
        radius = typedArray.getInt(R.styleable.DivisionCircle2_radius,RADIUS_DEF);
        typedArray.recycle();

        initView();

    }

    private void initView() {

        mValuePaint = new Paint();
        mValuePaint.setColor(Color.WHITE);
        mValuePaint.setStyle(Paint.Style.STROKE);
        mValuePaint.setStrokeWidth(2);
        mValuePaint.setAntiAlias(true);

        mTagPaint = new Paint();
        mTagPaint.setStyle(Paint.Style.FILL);
        mTagPaint.setColor(Color.RED);
        mTagPaint.setTextSize(30);

        mBigLineBottomPaint = new Paint();
        mBigLineBottomPaint.setStyle(Paint.Style.STROKE);
        mBigLineBottomPaint.setStrokeWidth(20);
        mBigLineBottomPaint.setColor(0xFFE95726);
        mBigLineBottomPaint.setAntiAlias(true);

        mBigLineTopPaint = new Paint();
        mBigLineTopPaint.setStyle(Paint.Style.STROKE);
        mBigLineTopPaint.setStrokeWidth(20);
        mBigLineTopPaint.setColor(Color.WHITE);
        mBigLineTopPaint.setAntiAlias(true);

        mScaleBigPaint = new Paint();
        mScaleBigPaint.setStyle(Paint.Style.FILL);
        mScaleBigPaint.setColor(Color.WHITE);
        mScaleBigPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mScaleBigPaint.setAntiAlias(true);
        mScaleBigPaint.setTextSize(30);

        mScaleSmallPaint = new Paint();
        mScaleSmallPaint.setStyle(Paint.Style.FILL);
        mScaleSmallPaint.setColor(Color.WHITE);
        mScaleSmallPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mScaleSmallPaint.setAntiAlias(true);
        mScaleSmallPaint.setTextSize(15);

        mNumPaint = new Paint();
        mNumPaint.setStyle(Paint.Style.FILL);
        mNumPaint.setColor(Color.WHITE);
        mNumPaint.setAntiAlias(true);
        mNumPaint.setTextSize(30);

        mBigTextPaint = new Paint();
        mBigTextPaint.setStyle(Paint.Style.FILL);
        mBigTextPaint.setColor(Color.WHITE);
        mBigTextPaint.setAntiAlias(true);
        mBigTextPaint.setTextSize(50);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCurrentTimes++;
        canvas.drawColor(0xFFFF6F3B);
        canvas.drawText("刻度盘2", 10, 100, mTagPaint);

        //画最外的半圆
        RectF rectF = new RectF(100, 100, 300 + radius * 2, 300 + radius * 2);
        canvas.drawArc(rectF, 180, 180, false, mValuePaint);

        //画底部粗线条的半圆
        RectF bigRecFBottom = new RectF(100 + 20, 100 + 20, 280 + radius * 2, 280 + radius * 2);
        canvas.drawArc(bigRecFBottom, 180, 180, false, mBigLineBottomPaint);
        //画上面的白色粗线条半圆
        RectF bigRecFTop = new RectF(100 + 20, 100 + 20, 280 + radius * 2, 280 + radius * 2);
        //把扫射的角度按100次来平分，即得出每次绘制所要扫射的角度
        canvas.drawArc(bigRecFTop, 180, (((mCurrentValue*180)/mMaxValue) / mTotalTimes * mCurrentTimes), false, mBigLineTopPaint);

        if (mCurrentTimes < mTotalTimes) {
            postInvalidate();//只要当前绘制次数少于总共次数，则重绘
        }

        canvas.save();
        //再画刻度
        RectF scaleRectF = new RectF(100 + 80, 100 + 80, 220 + radius * 2, 220 + radius * 2);
       // canvas.drawArc(scaleRectF, 180, 180, false, mValuePaint);
        //弧长
        int scaleLength = (int) ((180 * Math.PI * (((220 + radius * 2) - 180) / 2)) / 180);
        int devided = scaleLength / mUnitScale;
        Path path = new Path();
        path.addArc(scaleRectF, 180, 180);
        int startX = 0;
        int vOffset = 0;
        for (int i = 1; i < devided+1; i++) {
            if(i%2==0){
                canvas.drawTextOnPath("|", path, startX, 0, mScaleSmallPaint);
            }else{
                canvas.drawTextOnPath("|", path, startX, 0, mScaleBigPaint);
            }
            startX += mUnitScale;
        }
        //画最后一个
        canvas.drawTextOnPath("|", path, startX, 0, mScaleSmallPaint);
        canvas.save();

        //画左右2个坐标值
        canvas.drawText(mMinValue+"",100,140+250,mNumPaint);
        canvas.drawText(mMaxValue+"",280 + radius * 2,140+250,mNumPaint);
        canvas.save();

        //再画文字
        canvas.drawText("今天10：00",100+250*2/3,140+250,mNumPaint);
        canvas.drawText("次/分",100+250*5/6,100+250,mBigTextPaint);
        canvas.drawText(mCurrentValue / mTotalTimes * mCurrentTimes+"",80+250,40+250,mBigTextPaint);



    }
}
