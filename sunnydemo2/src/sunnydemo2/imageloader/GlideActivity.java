package sunnydemo2.imageloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/11/25.
 * 用google推荐的glide来加载网络图片，本地图片，本地资源图片
 */
public class GlideActivity extends Activity {


    private ImageView mImageView;

    public static void startGlideActivity(Context context) {
        Intent targetIntent = new Intent(context, GlideActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(this).
                load("http://d.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd5a6e81a8052da81cb39db3d0e.jpg").
                into(mImageView);
    }

    @Override
    public void onContentChanged() {

    }
}
