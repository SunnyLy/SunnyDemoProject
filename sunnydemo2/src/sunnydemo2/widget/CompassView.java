package sunnydemo2.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import sunnydemo2.utils.LogUtils;


/**
 * Created by sunny on 2015/12/23.
 * 练手，写一个罗盘的自定义控件
 */
public class CompassView extends View {

    private Paint marketPaint;//用于做标记
    private Paint textPaint;//用于画文字
    private Paint circlePaint;//画圆
    private Paint scalePaint;//刻度
    private Paint indicatorPaint;//指针

    private String northString = "北";//北
    private String southString = "南";//南
    private String westString = "西";//西
    private String eastString = "东";//东

    //用来显示当前方向
    private int bearing;

    private int textHeight;
    private int textWidth;

    public int getBearing() {
        return bearing;
    }

    public void setBearing(int bearing) {
        this.bearing = bearing;
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
    }

    public CompassView(Context context) {
        this(context, null);
    }

    public CompassView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCompassView();
    }

    /**
     * 初始化罗盘控件的一些参数
     */
    private void initCompassView() {
        setFocusable(true);


        marketPaint = new Paint();
        marketPaint.setAntiAlias(true);
        marketPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        marketPaint.setTextSize(30);
        marketPaint.setStyle(Paint.Style.STROKE);
        marketPaint.setColor(0xFFFF1122);

        //刻度
        scalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scalePaint.setAntiAlias(true);
        scalePaint.setStyle(Paint.Style.STROKE);
        scalePaint.setColor(Color.WHITE);
        scalePaint.setTextSize(30);

        //指针
        indicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        indicatorPaint.setAntiAlias(true);
        indicatorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        indicatorPaint.setColor(Color.GREEN);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(0xFFFFFFFF);
        textPaint.setTextSize(30);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.parseColor("#FFEE2288"));
        //测量指定字的宽度
        textHeight = (int) textPaint.measureText("W");
        textWidth = (int) textPaint.measureText("yY");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtils.e("onMeasure==================");
        int measureWidth = measure(widthMeasureSpec);
        int measureHeight = measure(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtils.e("onDraw==================");
        /**
         * getMeasuredWidth与getWidth的区别：
         * getMeasuredWidth:一般用于布局，计算view的原始宽度，前提是一定重写onMeasure方法
         *                 只有这样，才能进行onLayout()
         * getWidth:是view已经在xml中布局好了，直接获取view所占的宽度
         */

        int measureWidth = getMeasuredWidth();
        int measureHeight = getMeasuredHeight();

        int viewWidth = getWidth();
        int viewHeight = getHeight();
        LogUtils.e("measureWidth:"+measureWidth+",measureHeight:"+measureHeight
        +"\nviewWidth:"+viewWidth+",viewHeight:"+viewHeight);

        //这是控件的中心点，
        int px = measureWidth / 2;
        int py = measureHeight / 2;

        int radius = Math.min(px, py);

        int smallCircleRadius = radius/3;

        //画圆
        canvas.drawCircle(px, py, radius, circlePaint);

        //测试
        canvas.drawText("TEST",px-smallCircleRadius-10,py-smallCircleRadius-10,indicatorPaint);

        //画小圆
        canvas.drawCircle(px,py,smallCircleRadius,indicatorPaint);
        //画指针
        canvas.drawLine(px,py,px,py-radius+textHeight,indicatorPaint);

        //保存画布当前状态，为下一步画布的旋转，平移，缩放等做好准备。
        //当画布scale,translate,rotate等结束后，一定要先保存状态，不然restore时，就不会显示。
        canvas.save();

        //通过旋转画布来显示当前方向，负号表示当前永远在屏幕正方向最顶端
        canvas.rotate(-bearing, px, py);
        canvas.save();
        canvas.restore();

        //下面准备画标记了，
        int cardinalX = px - textWidth / 2;
        int cardinalY = py - radius + textHeight;
        //360度分成24份，每15度画一刻度，每45度画一文本
        for (int i = 0; i < 24; i++) {
            //画刻度
            canvas.drawLine(px, py - radius, px, py - radius + 10, scalePaint);
            canvas.save();
            //把画布往下移动textHeight距离，为画文字做好准备
            canvas.translate(0, textHeight);
            //先画东南西北
            if (i % 6 == 0) {
                String direcString = "";
                switch (i) {
                    case 0:
                        direcString = northString;
                        int arrowY = 2 * textHeight;
                        //画正北方向上的^符号
                        canvas.drawLine(px, arrowY, px - 5, 3 * textHeight, marketPaint);
                        canvas.drawLine(px, arrowY, px + 5, 3 * textHeight, marketPaint);
                        break;
                    case 6:
                        direcString = eastString;
                        break;
                    case 12:
                        direcString = southString;
                        break;
                    case 18:
                        direcString = westString;
                        break;
                }
                canvas.drawText(direcString, cardinalX, cardinalY, textPaint);
            } else if (i % 3 == 0) {
                //每45度画一个角度
                String angle = String.valueOf(i * 15);
                float angleTextWidth = textPaint.measureText(angle);
                int angleTextX = (int) (px - angleTextWidth / 2);
                int angleTextY = py - radius + textHeight;
                canvas.drawText(angle+"°", angleTextX, angleTextY, textPaint);
            }
            canvas.restore();

            //一个刻度+文字画完，再让画布旋转15度，为下一个刻度做准备。
            canvas.rotate(15, px, py);
        }

        //恢复之前所有保存的状态
        canvas.restore();

    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.dispatchPopulateAccessibilityEvent(event);
        LogUtils.e("dispatchPopulateAccessibilityEvent==================");
        if (!isShown()) {
            return false;
        } else {
            String bearingStr = String.valueOf(bearing);
            if (bearingStr.length() > AccessibilityEvent.MAX_TEXT_LENGTH) {
                bearingStr = bearingStr.substring(0, AccessibilityEvent.MAX_TEXT_LENGTH);
            }
            event.getText().add(bearingStr);
            return true;
        }
    }

    private int measure(int measureSpec) {
        int result = 0;
        //对测量说明进行解码
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;//指明大小
        } else if (specMode == MeasureSpec.AT_MOST) {
            //充分利用ViewGroup可用的空间
            result = specSize;
        } else if (specSize == MeasureSpec.UNSPECIFIED) {
            //未指定大小时，给一个默认值
            result = 300;
        }
        return result;
    }
}
