package sunnydemo2.bargraph.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2015/11/16.
 * Annotion:折线图
 */
public class CurveChartView extends View {
    private Context mContext;

    private Paint mTitlePaint;
    private Paint mBgBarPaint;

    private Paint mRedPaint;
    private Paint mBluePaint;

    private List<Integer> redDatas = new ArrayList<>();
    private List<Integer> blueDatas = new ArrayList<>();

    public CurveChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        initView();
        initDatas();
    }

    private void initDatas() {
        redDatas.clear();
        blueDatas.clear();

        redDatas.add(30);
        redDatas.add(70);
        redDatas.add(60);
        redDatas.add(40);
        redDatas.add(50);
        redDatas.add(20);

        blueDatas.add(30);
        blueDatas.add(70);
        blueDatas.add(60);
        blueDatas.add(40);
        blueDatas.add(50);
        blueDatas.add(20);
    }

    private void initView() {
        mTitlePaint = new Paint();
        mTitlePaint.setStyle(Paint.Style.FILL);
        mTitlePaint.setColor(0xFFA1192D);
        mTitlePaint.setAntiAlias(true);
        mTitlePaint.setTextSize(30);

        mBgBarPaint = new Paint();
        mBgBarPaint.setStyle(Paint.Style.STROKE);
        mBgBarPaint.setStrokeWidth(2);
        mBgBarPaint.setAntiAlias(true);
        mBgBarPaint.setColor(0xFFE7E7E7);

        mRedPaint = new Paint();
        mRedPaint.setStyle(Paint.Style.STROKE);
        mRedPaint.setStrokeWidth(2);
        mRedPaint.setAntiAlias(true);
        mRedPaint.setColor(0xFFB85362);

        mBluePaint = new Paint();
        mBluePaint.setStyle(Paint.Style.STROKE);
        mBluePaint.setStrokeWidth(2);
        mBluePaint.setAntiAlias(true);
        mBluePaint.setColor(0xFF6AE6B4);
    }

    public CurveChartView(Context context) {
        this(context, null);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawText("曲线图",10,10,mTitlePaint);

        int topY = 30;
        int bottomY = getHeight() - topY;
        int startX = 20;
        int space = 18;
        int endX = 20;

        //画背景竖线
        RectF bgRectF = new RectF();
        for(int i=1;i<25;i++){
            bgRectF.top =topY;
            bgRectF.left = startX;
            bgRectF.right = endX;
            bgRectF.bottom = bottomY;
            canvas.drawLine(startX,topY,endX,bottomY,mBgBarPaint);
            //每根竖线上有2个点
           /* if(i<redDatas.size()){
                canvas.drawCircle(startX,redDatas.get(i-1),1,mRedPaint);
                //连线
                canvas.drawLine(startX,redDatas.get(0),endX,redDatas.get(i-1),mRedPaint);
                canvas.drawCircle(startX,blueDatas.get(i-1),1,mBluePaint);
            }*/
            startX += (getWidth()-20*2)/25;
            endX = startX;
        }


        //画拆线
        canvas.drawLine(200,100,300,300,mRedPaint);
        canvas.drawCircle(300,300,4,mBluePaint);
        canvas.drawLine(300,300,400,400, mRedPaint);
        canvas.drawCircle(400,400, 4, mBluePaint);
        canvas.drawLine(400,400,500,200, mRedPaint);
        canvas.drawCircle(500,200,4,mBluePaint);



    }
}
