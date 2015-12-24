package sunny.propertyanimationdemo.view;

import android.animation.TypeEvaluator;

/**
 * Created by sunny on 2015-08-27.
 * TypeEvalutor:通过计算告知动画系统如何从初始值过度到结束值
 */
public class FloatEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        //fraction:表示动画的完成度,根据这来计算当前动画的值应该是多少
        //startValue:开始值
        //endValue:结束值
        float startFloat =((Number)startValue).floatValue();
        float endFloat = ((Number)endValue).floatValue();
        return startFloat + fraction*(endFloat - startFloat);
        //公式：用（结束值-初始值）* fraction
    }
}
