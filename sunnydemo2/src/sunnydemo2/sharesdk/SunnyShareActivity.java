package sunnydemo2.sharesdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.het.share.dialog.CommonShareDialog;
import com.het.share.listener.ICommonShareListener;
import com.het.share.manager.CommonShareManager;
import com.het.share.manager.CommonSharePlatform;
import com.het.share.model.CommonShareImage;
import com.het.share.model.CommonShareMusic;
import com.het.share.model.CommonShareTextOnly;
import com.het.share.model.CommonShareWebpage;
import com.het.share.utils.CommonShareProxy;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.smartbracelet.sunny.sunnydemo2.R;

import sunnydemo2.base.BaseActivity;

/**
 * Created by sunny on 2016/1/9.
 * 分享测试 类
 */
public class SunnyShareActivity extends BaseActivity implements ICommonShareListener, IWeiboHandler.Response {

    private CommonShareManager mShareManger;
    private CommonShareDialog mShareDialog;
    private CommonShareProxy mShareProxy;

    //private String musicUrl = "http://music.baidu.com/song/256006577";
    private String musicUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
    private String musicDataUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
    private String mTargetUrl = "http://user.qzone.qq.com/1156581276/mood/9c07f044e63558577a160600.1?ptlang=2052";
    private String mTitle = "老婆";
    private String mDescription = "Hi,老婆,吃晚饭了没？";
    //private String mImgUrl = "http://img.369xxw.com/uploads/20151203/rse4m0b3sfv.jpg";
    private String mImgUrl = "http://www.baidu.com/img/bdlogo.png";

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
        mShareProxy = new CommonShareProxy(SunnyShareActivity.this, this);
        mShareProxy.onCreate(savedInstanceState);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mShareProxy.onNewIntent(intent);
    }

    /**
     * 在onDestroy的时候，释放所有的资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShareManger.releaseResource();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        mShareProxy.onActivityResult(requestCode, resultCode, data);
    }

    private void initParams() {
        mShareManger = new CommonShareManager.Builder(this).
                //不使用默认的微信，QQ，Sina分享，添加自己的分享
               // setShareOperate(new TestShareOpreate(this)).
                registerWeixin().registerQQ().registerSinaWeibo("www.baidu.com").
        create();
        mShareDialog = new CommonShareDialog(SunnyShareActivity.this, this);
        mShareDialog.showSina();


    }

    public void startShare(View view) {

        if (mShareDialog != null && !mShareDialog.isShowing()) {
            mShareDialog.show();
        }
    }

    public void showShareDialogFragment(View view){
        ShareDialog shareDialog = new ShareDialog();
        shareDialog.setListener(this);
        shareDialog.show(getSupportFragmentManager(),"");

    }

    @Override
    public void onStartShare(CommonSharePlatform sharePlatform) {
        showDialog();

        /**
         * 音乐
         */
        CommonShareMusic music = new CommonShareMusic();
        music.setUiListener(this);
        music.setTitle("分享音乐");
        music.setDescription("高山流水，寻觅知音");
        music.setAppName("SunnyDemo");
        //music.setTargetUrl("http://music.sina.com.cn/yueku/i/2850305.html");
        music.setTargetUrl(musicUrl);
        music.setMusicUrl(musicUrl);
        music.setMusicDataUrl(musicDataUrl);
        music.setBm(null);
        music.setImgUrl("http://fdfs.xmcdn.com/group15/M06/94/D2/wKgDaFY7Ei6R79bNAAE0O1cKLAY443_mobile_large.jpg");
        music.setSharePlatform(sharePlatform);

       /* CommonShareMusic music = new CommonShareMusic();
        music.setUiListener(this);
        music.setTitle("睡眠音乐分享");
        music.setDescription("陆莹翻唱《演员》");
        music.setAppName("Clife睡眠");
        music.setTargetUrl("http://fdfs.xmcdn.com/group8/M05/53/EC/wKgDYFcfoIGD7OyJAB_P2WhntYE970.mp3");
        music.setMusicUrl("http://fdfs.xmcdn.com/group8/M05/53/EC/wKgDYFcfoIGD7OyJAB_P2WhntYE970.mp3");
        music.setMusicDataUrl("http://fdfs.xmcdn.com/group8/M05/53/EC/wKgDYFcfoIGD7OyJAB_P2WhntYE970.mp3");
        music.setBm(null);
       // music.setImgUrl("http://fdfs.xmcdn.com/group15/M06/94/D2/wKgDaFY7Ei6R79bNAAE0O1cKLAY443_mobile_large.jpg");
        music.setImgUrl(mImgUrl);
        music.setSharePlatform(sharePlatform);*/


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
        //webpage.setBm(null);
        //webpage.setImgUrl("http://fdfs.xmcdn.com/group15/M06/94/D2/wKgDaFY7Ei6R79bNAAE0O1cKLAY443_mobile_large.jpg");
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
        // image.setImgUrl(mImgUrl);
        //设置本地图片地址
        image.setImgUrl("/storage/emulated/0/tencent/tassistant/cloudkit/html/1/qzs.qq.com/open/yyb/coupon/img/icon01.png");
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
        //  mShareManger.shareTextOnly(textOnly);//success
        //分享音乐
       // mShareManger.shareMusic(music);//success
        //分享网页
        //webpage.setImgUrl(mImgUrl);
        webpage.setImgUrl("http://fileserver1.clife.net:8080/group1/M00/06/BC/Cvtlhlb5ATaATUJfAAD2ZMEiqKk661.jpg");
       // webpage.setImgUrl("http://www.baidu.com/img/bdlogo.png");
        //webpage.setBm(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.pager5));
        //mShareManger.shareWebpage(webpage);//success
        (mShareManger.getShareOperate()).shareWebPage(webpage,mShareManger.getShareObject(webpage.getSharePlatform()));
        //分享图片
        //mShareManger.sharePicOnly(image);//success
        //分享文本
        //mShareManger.sharePicText(webpage);//success
    }


    /**
     * 分享回调监听
     *
     * @param sharePlatform
     * @param msg
     */
    @Override
    public void onShareSuccess(final CommonSharePlatform sharePlatform, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("qq:","onShareSuccess");
                hideShareDialog();
                Toast.makeText(SunnyShareActivity.this, msg+",,,,,,,", Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });

    }

    @Override
    public void onShareFialure(final CommonSharePlatform sharePlatform, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideShareDialog();
                Toast.makeText(SunnyShareActivity.this,  msg+",,,,,,,", Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });
    }

    @Override
    public void onShareCancel(final CommonSharePlatform sharePlatform, final String msg) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("qq:","onShareCancel");
                hideShareDialog();
                Toast.makeText(SunnyShareActivity.this,  msg+",,,,,,,", Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });
    }

    private void hideShareDialog(){
        mShareDialog.dismiss();
        Log.e("qq:","dissmiss end");
    }

    /**
     * 新浪微博分享回调
     *
     * @param baseResponse
     */
    @Override
    public void onResponse(BaseResponse baseResponse) {
        CommonShareDialog.sinaOnResp(baseResponse);
    }

}
