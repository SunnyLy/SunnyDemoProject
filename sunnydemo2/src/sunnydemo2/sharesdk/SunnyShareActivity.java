package sunnydemo2.sharesdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import com.het.share.dialog.CommonShareDialog;
import com.het.share.listener.ICommonShareLinstener;
import com.het.share.manager.CommonShareManager;
import com.het.share.manager.CommonSharePlatform;
import com.het.share.model.CommonShareBaseBean;
import com.het.share.model.CommonShareImage;
import com.het.share.model.CommonShareMusic;
import com.het.share.model.CommonShareTextOnly;
import com.het.share.model.CommonShareWebpage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.smartbracelet.sunny.sunnydemo2.R;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import sunnydemo2.base.BaseActivity;

/**
 * Created by sunny on 2016/1/9.
 * 分享测试 类
 */
public class SunnyShareActivity extends BaseActivity implements ICommonShareLinstener,IUiListener{

    private CommonShareManager mShareManger;
    private CommonShareDialog mShareDialog;

    //private String musicUrl = "http://music.baidu.com/song/256006577";
    private String musicUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
    private String musicDataUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
    private String mTargetUrl = "http://www.baidu.com";
    private String mTitle = "阳总";
    private String mDescription = "Hi,阳总,吃晚饭了没";
    private String mImgUrl = "http://img.369xxw.com/uploads/20151203/rse4m0b3sfv.jpg";

    /*private String mWeixinAppId = "wxd930ea5d5a258f4f";//微信Demo用
    private String mWeixinSecrect = "";//分享的时候不要用到secretId,只有微信登录的时候要*/

    public static String mWeixinAppId = "wxe46768b16f799372";
    private String mWeixinSecrect = "f0ce1674d0d12867467b0b7342c7b670";

    private String mQQAppId = "1104541762";//CLife用的

    private String mSinaAppkey = "380422110";//SunnyDemo2专用



    public static void startSunnyShareActivity(Context context) {
        Intent targetIntent = new Intent(context, SunnyShareActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        initParams();

    }

    private void initParams() {
        mShareManger = new CommonShareManager.Builder(this).
                registerWeixin(mWeixinAppId).//注册微信
                registerQQ(mQQAppId).//注册QQ
                registerSinaWeibo(mSinaAppkey,"www.baidu.com").//注册微博
                create();
        mShareDialog = new CommonShareDialog(this,this);
        mShareDialog.showSina();

    }

    public void startShare(View view){

        if(mShareDialog != null && !mShareDialog.isShowing()){
            mShareDialog.show();
        }
    }

    @Override
    public void onStartShare(CommonSharePlatform sharePlatform) {

        showDialog();

        CommonShareBaseBean shareBean = new CommonShareBaseBean();
        shareBean.setUiListener(this);
        shareBean.setTitle(mTitle);
        shareBean.setDescription(mDescription);
        shareBean.setAppName("SunnyDemo");
        shareBean.setTargetUrl(mTargetUrl);
        shareBean.setBm(null);
        shareBean.setSharePlatform(sharePlatform);


        /**
         * 音乐
         */
        CommonShareMusic music = new CommonShareMusic();
        music.setUiListener(this);
        music.setTitle(mTitle);
        music.setDescription(mDescription);
        music.setAppName("SunnyDemo");
        //music.setTargetUrl("http://music.sina.com.cn/yueku/i/2850305.html");
        music.setTargetUrl(musicUrl);
        music.setMusicUrl(musicUrl);
        music.setMusicDataUrl(musicDataUrl);
        music.setBm(null);
        music.setSharePlatform(sharePlatform);


        /**
         * 网页
         */
        CommonShareWebpage webpage = new CommonShareWebpage();
        webpage.setUiListener(this);
        webpage.setTitle(mTitle);
        webpage.setDescription(mDescription);
        webpage.setAppName("SunnyDemo");
        webpage.setTargetUrl("http://news.sina.com.cn/c/2013-10-22/021928494669.shtml");
        webpage.setWebpageUrl(mTargetUrl);
        webpage.setBm(null);
        webpage.setSharePlatform(sharePlatform);

        /**
         * 图片
         */
        CommonShareImage image = new CommonShareImage();
        image.setUiListener(this);
        image.setTitle(mTitle);
        image.setDescription(mDescription);
        image.setAppName("SunnyDemo2");
        image.setTargetUrl(mTargetUrl);
        image.setImgUrl(mImgUrl);
        image.setSharePlatform(sharePlatform);

        /**
         * 文字
         */
        CommonShareTextOnly textOnly = new CommonShareTextOnly();
        textOnly.setUiListener(this);
        textOnly.setTitle(mTitle);
        textOnly.setDescription(mDescription);
        textOnly.setAppName("SunnyDemo2");
        textOnly.setTargetUrl(mTargetUrl);
        textOnly.setImgUrl(mImgUrl);
        textOnly.setSharePlatform(sharePlatform);

        //分享文字
       // mShareManger.shareTextOnly(textOnly);

        //分享音乐
        //mShareManger.shareMusic(music);
        //分享网页
        webpage.setImgUrl(mImgUrl);
        mShareManger.shareWebpage(webpage);
        //分享图片
       // mShareManger.sharePicOnly(image);
    }

    @Override
    public void onShareSuccess(CommonSharePlatform sharePlatform, String msg,CommonShareDialog dialog) {

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        hideDialog();
    }

    @Override
    public void onShareFialure(CommonSharePlatform sharePlatform, String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        hideDialog();
    }

    @Override
    public void onShareCancel(CommonSharePlatform sharePlatform, String msg) {
        if(sharePlatform != CommonSharePlatform.SinaWeibo)
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        hideDialog();
    }

    /**
     * //////////////下面是腾讯分享回调
     * @param o
     */
    @Override
    public void onComplete(Object o) {
        hideDialog();
        Toast.makeText(this,"分享完成",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(final UiError uiError) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SunnyShareActivity.this,uiError.errorMessage,Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });

    }

    @Override
    public void onCancel() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SunnyShareActivity.this,"分享onCancel",Toast.LENGTH_SHORT).show();
            }
        });

    }



}
