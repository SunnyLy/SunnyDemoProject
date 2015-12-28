package sunnydemo2.androidl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.widget.Button;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/12/17.
 * Android L 新控件RippleDrawable的使用范例
 * RippleDrawable:波纹效果
 */
public class RippleDrawableActivity extends Activity {


    public static void startRippleDrawableActivity(Context context){
        Intent targetIntent = new Intent(context,RippleDrawableActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rippledrawable);

    }
}
