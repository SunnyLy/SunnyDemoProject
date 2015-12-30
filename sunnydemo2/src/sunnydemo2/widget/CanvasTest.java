package sunnydemo2.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import sunnydemo2.utils.LogUtils;


/**
 * Created by sunny on 2015/12/24.
 * Annotion:
 */
public class CanvasTest extends View {

    private Paint marketPaint;
    private Paint whitePaint;

    public CanvasTest(Context context) {
        this(context,null);
    }

    public CanvasTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }


    public CanvasTest(Context context, AttributeSet attrs) {

        this(context, attrs,0);
    }

    private void initParams() {


        marketPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        marketPaint.setAntiAlias(true);
        marketPaint.setColor(Color.RED);
        marketPaint.setTextSize(30);
        marketPaint.setStyle(Paint.Style.STROKE);

        whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        whitePaint.setStyle(Paint.Style.STROKE);
        whitePaint.setColor(Color.WHITE);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.e("onLayout=========");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = measreSpec(widthMeasureSpec);
        int measureHeight = measreSpec(heightMeasureSpec);
        setMeasuredDimension(measureWidth,measureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布画背景色并不是真正的在虚拟画布上画背景色，而是在手机上渲染，
        //所以不管画什么颜色，画多少次背景，都只以最后一次为准。
        measure(0,0);
        canvas.drawColor(Color.DKGRAY);
        canvas.save();


        int measureWidth = getMeasuredWidth();
        int measureHeight = getMeasuredHeight();
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        LogUtils.e("measureWidth:"+measureWidth+
        "\nmeasureHeight:"+measureHeight+
        "\nviewWidth:"+viewWidth+
        "\nviewHeight:"+viewHeight);

        int px = measureWidth/2;
        int py = measureHeight/2;

        //画布旋转
       /* canvas.drawText("测试rotate:",px,py,marketPaint);
        canvas.save();
        canvas.rotate(15,px,py);//根据指定坐标点来旋转
        canvas.drawText("测试rotate45:",px,py,marketPaint);
        canvas.restore();*/

        //画布平移，坐标系跟着平移
        //每次画的时候，都会产生一个新的canvas,透明图层
        RectF testRectF = new RectF(0,0,400,220);
        canvas.drawRect(testRectF,marketPaint);
        //save:保存当前状态至么有栈，便于后面restore时，恢复到这个点
        canvas.save();

        canvas.translate(100,100);
        canvas.drawRect(testRectF,marketPaint);
        canvas.save();


        //skew:倾斜，这个值一般是角度的tan值
        canvas.skew((float) Math.tan(60),0);
        canvas.drawRect(testRectF,marketPaint);

        //restore：就是恢复到上次save保存的所有状态时的那个状态，
        //即从栈里面取栈顶的因此，如果restore调用的次数不能超过save,否则报错，因为栈里面没有存这么多
        canvas.restore();

        canvas.skew((float)Math.tan(30),(float)Math.tan(45));
        canvas.drawRect(testRectF,whitePaint);
        canvas.restore();
        canvas.drawText("再次restore，从栈里面取出save之前的状态",testRectF.centerX(),testRectF.centerY()+10,marketPaint);
    }

    private int measreSpec(int widthMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if(specMode == MeasureSpec.UNSPECIFIED){
            result = 400;
        }else {
            result = specSize;
        }

        return result;
    }
}
