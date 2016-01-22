package sunnydemo2.imageloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.het.frescosupport.FrescoManager;

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
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
        addContentView(simpleDraweeView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        FrescoManager.getInstance(this).init();
        simpleDraweeView.setImageURI(Uri.parse("http://200.200.200.58:8981/group2/M00/04/33/yMjIOlaXUrKAPkljAAGUMlWlJeY2811924"));
    }
}
