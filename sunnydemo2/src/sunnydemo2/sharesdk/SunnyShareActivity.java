package sunnydemo2.sharesdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.het.share.dialog.CommonShareDialog;
import com.het.share.listener.ICommonShareLinstener;
import com.het.share.manager.CommonShareManager;
import com.het.share.manager.CommonSharePlatform;
import com.het.share.model.CommonShareBaseBean;
import com.het.share.model.CommonShareMusic;
import com.het.share.model.CommonShareWebpage;
import com.smartbracelet.sunny.sunnydemo2.R;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * Created by sunny on 2016/1/9.
 * Annotion:
 */
public class SunnyShareActivity extends Activity implements ICommonShareLinstener,IUiListener{

    private CommonShareManager mShareManger;
    private CommonShareDialog mShareDialog;

    //private String musicUrl = "http://music.baidu.com/song/256006577";
    private String musicUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
    private String musicDataUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
    private String mTargetUrl = "http://user.qzone.qq.com/450512747/infocenter?ptsig=vpvnl-fJW5GE6ROJxYC3-U4Ah0KsYVZD0m3jkC3Q-Yo_";
    private String mTitle = "阳总";
    private String mDescription = "Hi,阳总,吃晚饭了没";
    private String mImgUrl = "http://pic15.nipic.com/20110725/7067632_020203324179_2.jpg";

    /*private String mWeixinAppId = "wxd930ea5d5a258f4f";//微信Demo用
    private String mWeixinSecrect = "";//分享的时候不要用到secretId,只有微信登录的时候要*/

    private String mWeixinAppId = "wxe46768b16f799372";
    private String mWeixinSecrect = "f0ce1674d0d12867467b0b7342c7b670";

    private String mQQAppId = "1104541762";//CLife用的
    private String mSinaWeiboAppId = "3475229326";//Clife用


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
                registerSinaWeibo(mSinaWeiboAppId).//注册微博
                create();
        mShareDialog = new CommonShareDialog(this,this);

    }

    public void startShare(View view){

        if(mShareDialog != null && !mShareDialog.isShowing()){
            mShareDialog.show();
        }
    }

    @Override
    public void onStartShare(CommonSharePlatform sharePlatform) {

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
        music.setTargetUrl(mTargetUrl);
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
        webpage.setTargetUrl(mTargetUrl);
        webpage.setBm(null);
        webpage.setSharePlatform(sharePlatform);

        //分享音乐
        mShareManger.shareMusic(music);
        //分享网页
       /* webpage.setImgUrl(mImgUrl);
        mShareManger.shareWebpage(webpage);*/
    }

    @Override
    public void onShareSuccess(CommonSharePlatform sharePlatform, String msg) {

        Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShareFialure(CommonSharePlatform sharePlatform, String msg) {
        Toast.makeText(this,"分享失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShareCancel(CommonSharePlatform sharePlatform) {
        Toast.makeText(this,"取消分享",Toast.LENGTH_SHORT).show();
    }

    /**
     * //////////////下面是腾讯分享回调
     * @param o
     */
    @Override
    public void onComplete(Object o) {
        Toast.makeText(this,"QQ分享完成",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(UiError uiError) {
        Toast.makeText(this,"QQ分享onError",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        Toast.makeText(this,"QQ分享onCancel",Toast.LENGTH_SHORT).show();
    }
}
