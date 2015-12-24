package com.smartbracelet.sunny.sunnydemo3;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by sunny on 2015/11/24.
 * Annotion:
 */
public class SunnyTextView extends TextView {
    public SunnyTextView(Context context) {
        super(context);
    }

    public SunnyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SunnyAttr);
        int color = typedArray.getColor(R.styleable.SunnyAttr_sunnyTextColorRed,0xffff0000);
        setTextColor(color);
    }
}
