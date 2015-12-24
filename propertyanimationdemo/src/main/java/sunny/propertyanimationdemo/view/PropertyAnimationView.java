package sunny.propertyanimationdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by Administrator on 2015-08-27.
 */
public class PropertyAnimationView extends View {

    public static final float RADIUS = 50f;
    private Point currentPoint;
    private Paint mPaint;

    private String mColor;

    public PropertyAnimationView(Context context) {
        super(context);
        init();
    }

    public PropertyAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PropertyAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String mColor) {
        this.mColor = mColor;
        mPaint.setColor(Color.parseColor(mColor));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(currentPoint == null){
            currentPoint = new Point(RADIUS,RADIUS);
            drawCircle(canvas);
            startAnimation();
        }else{
            drawCircle(canvas);
        }
    }

    private void startAnimation() {
        Point startPoint = new Point(RADIUS,RADIUS);
        Point endPoint = new Point(getWidth() - RADIUS,getHeight() - RADIUS);
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });

        animator.setDuration(5000);
        animator.start();
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x,y,RADIUS,mPaint);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

}
