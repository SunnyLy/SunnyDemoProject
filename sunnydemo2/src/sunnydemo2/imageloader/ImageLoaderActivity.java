package sunnydemo2.imageloader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/11/25.
 * ImageLoader来加载网络图片，本地图片，本地资源图片
 */
public class ImageLoaderActivity extends Activity {

    private ImageView mImageView;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;

    private TextView tvInfo;

   /* private static final String URL = "http://d.hiphotos.baidu.com/image/pic/item/7c1ed21b0ef41bd" +
            "5a6e81a8052da81cb39db3d0e.jpg";*/
    private static final String URL = "http://200.200.200.58:8981/group2/M00/04/33/yMjIOlaXUrKAPkljAAGUMlWlJeY2811924";

    public static void startImageLoaderActivity(Context context){
        Intent targetIntent = new Intent(context,ImageLoaderActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader);

        initImageLoader();
       // getTelphoneNum();
        ImageLoader.getInstance().
                displayImage(URL,mImageView);

        ImageLoader.getInstance()
                .displayImage(URL, mImageView2, null, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                        tvInfo.setText(s);
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {
                        tvInfo.setText(s);
                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        tvInfo.setText(s);
                        mImageView2.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {
                        tvInfo.setText(s);
                    }
                });

    }

    public void onContentChanged() {
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView3 = (ImageView) findViewById(R.id.imageView3);
        mImageView4 = (ImageView) findViewById(R.id.imageView4);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    private void initImageLoader() {
        ImageLoader imageLoader = ImageLoader.getInstance();
       /* DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();*/
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .denyCacheImageMultipleSizesInMemory()
              //  .defaultDisplayImageOptions(options)
                .build();
        imageLoader.init(configuration);
    }

    private void getTelphoneNum() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if(Build.VERSION.SDK_INT >= 23){
            int phoneCount = telephonyManager.getPhoneCount();
        }
        int phoneType = telephonyManager.getPhoneType();
        String  phoneNum = telephonyManager.getLine1Number();
        String providerName = getProviderName(telephonyManager);
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(
                phoneType+"\n手机号码："+phoneNum
        +"\n运营商:"+providerName).create();
        alertDialog.show();
    }

    private String  getProviderName(TelephonyManager telephonyManager) {
        String providerName = "N/A";
        String imsi = null;
        imsi = telephonyManager.getSubscriberId();
        if(TextUtils.isEmpty(imsi)){
            return null;
        }
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        if(imsi.startsWith("4600")||imsi.startsWith("46002")){
            providerName = "中国移动";
        }else if(imsi.startsWith("46001")){
            providerName = "中国联通";
        }else if(imsi.startsWith("46003")){
            providerName = "中国电信";
        }else{
            providerName = "火星";
        }


        return providerName;
    }
}
