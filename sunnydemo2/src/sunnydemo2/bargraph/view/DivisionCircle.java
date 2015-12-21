package sunnydemo2.bargraph.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sunny on 2015/11/13.
 * Annotion:刻度盘
 */
public class DivisionCircle extends View {
    private Context mContext;

    private Paint mTextPaint;
    private Paint mTextPaint2;
    private Paint mSmallPaint;
    private Paint mCirclePaint;//画圆
    private Paint mSmallCirclePaint;//圆中的小圆
    private Paint mTagPaint;//当前值记录画笔
    private Paint mBigTextPaint;//大数字画笔
    private Paint mSmallTextPaint;//小数字画笔

    public DivisionCircle(Context context) {
        this(context,null);
    }
    public DivisionCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;


        initPaint();
    }

    private void initPaint() {
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setStrokeWidth(10);
        mTextPaint.setAntiAlias(true);

        mTextPaint2 = new Paint();
        mTextPaint2.setColor(Color.YELLOW);
        mTextPaint2.setAntiAlias(true);
        mTextPaint2.setStyle(Paint.Style.FILL);
        mTextPaint2.setTextSize(30);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(0xFF57E4AB);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mSmallCirclePaint = new Paint();
        mSmallCirclePaint.setColor(0xFF88FFCF);
        mSmallCirclePaint.setAntiAlias(true);
        mSmallCirclePaint.setStyle(Paint.Style.FILL);

        mSmallPaint = new Paint();
        mSmallPaint.setColor(Color.WHITE);
        mSmallPaint.setAntiAlias(true);
        mSmallPaint.setStyle(Paint.Style.FILL);
        mSmallPaint.setTextSize(10);

        mTagPaint = new Paint();
        mTagPaint.setColor(Color.WHITE);
        mTagPaint.setAntiAlias(true);
        mTagPaint.setStyle(Paint.Style.FILL);
        mTagPaint.setTextSize(30);

        mBigTextPaint = new Paint();
        mBigTextPaint.setTextSize(30);
        mBigTextPaint.setColor(Color.WHITE);
        mBigTextPaint.setStyle(Paint.Style.FILL);

        mSmallTextPaint = new Paint();
        mSmallTextPaint.setTextSize(15);
        mSmallTextPaint.setColor(Color.WHITE);
        mSmallTextPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec){
        int width = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST){
            width = widthMeasureSpec;
        }else if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec){
        int height = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else if(heightMode == MeasureSpec.AT_MOST){
            height = heightMeasureSpec;
        }
        return height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xFFA9475E);
        canvas.drawText("刻度盘", 100, 50, mTextPaint2);

        RectF rectF = new RectF();
        rectF.left = 100;
        rectF.top = 100;
        rectF.right = rectF.left+200*2;
        rectF.bottom = rectF.top+200*2;
        /**
         * 画扇形，startAngle:扇形起始点角度
         *  sweepAngle:扇形扫过的角度
         *  注：是以水平为0度，开始，顺时针画
         */
        canvas.drawArc(rectF, 150, 210, false, mTextPaint);
        Path path = new Path();
        path.addArc(rectF,150,210);
        //弧长
        int s = (int) (210*Math.PI*250/180);
        int space = (s/11);
        int smallSpace = space/3;//每一份又分成三小份
        int start = -10;
        int smallStart = 0;//每一小份的开始
        for(int i=1;i<11;i++){
            //画刻度值
            //hOffset:画文字的起始位置
            //vOffset:<0,在路径上方，>0在路径下方
            if(((i+1)%2)!= 0){
                canvas.drawTextOnPath((i+10)+"",path,start,30,mBigTextPaint);
            }else{
                canvas.drawTextOnPath((i+10)+"",path,start,30,mSmallTextPaint);
            }

            start += space;
            //画刻度
           // canvas.drawTextOnPath("|", path, start, 0, mTextPaint2);
            for(int j=0;j<3;j++){
                //画小刻度
                if(j==2){
                    canvas.drawTextOnPath("|", path, smallStart, 0, mTextPaint2);
                }else{
                    canvas.drawTextOnPath("|",path,smallStart,0,mSmallPaint);
                }
                smallStart += smallSpace;
            }
/*
            if((i%(i+(i-1)*3)) == 0){
                canvas.drawTextOnPath("|", path, smallStart, 0, mTextPaint2);
            }else{
                canvas.drawTextOnPath("|",path,smallStart,0,mSmallPaint);
            }

            smallStart += smallSpace;*/

        }


        //canvas.restore();//restore()就是保存已经画好的，重新开始一块新的画布
        //再画刻度盘中间的圆
        canvas.drawCircle(300,300,100,mCirclePaint);

        //再画圆中的小圆
        canvas.drawCircle(300, 300, 20, mSmallCirclePaint);
        //画指针
        canvas.drawLine(80, 450, 300, 300, mSmallCirclePaint);

        //画文字
        canvas.drawText("12次/分",270,450,mTagPaint);
        canvas.drawText("今天10:00",270,480,mTagPaint);




    }
}
