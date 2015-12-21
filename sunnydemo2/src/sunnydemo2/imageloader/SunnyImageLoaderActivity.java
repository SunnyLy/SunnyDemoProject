package sunnydemo2.imageloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sunny on 2015/11/25.
 * 自定义图片加载框架
 */
public class SunnyImageLoaderActivity extends Activity {

    public static void startSunnyImageLoaderActivity(Context context){
        Intent targetIntent = new Intent(context,SunnyImageLoaderActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
