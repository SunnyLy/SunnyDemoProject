package sunnydemo2.imageloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sunny on 2015/11/25.
 * FaceBook 的 Fresco来加载网络图片，本地图片，本地资源图片
 */
public class FrescoActivity extends Activity {

    public static void startFrescoActivity(Context context){
        Intent targetIntent = new Intent(context,FrescoActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
