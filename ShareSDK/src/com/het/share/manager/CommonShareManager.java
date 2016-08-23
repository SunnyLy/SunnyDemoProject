package com.het.share.manager;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.het.frescosupport.FrescoManager;
import com.het.share.listener.IShareOperate;
import com.het.share.model.CommonShareBaseBean;
import com.het.share.model.CommonShareMusic;
import com.het.share.model.CommonShareTextOnly;
import com.het.share.model.CommonShareVideo;
import com.het.share.model.CommonShareWebpage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

/**
 * Created by sunny on 2016/1/8.
 * 分享管理类：
 * 主要用来对上层将要进行的微信，QQ，微博等分享进行一个统一的处理，
 * 此类将采用构造器的模式来达到上层进行动态注册的目的，
 * 同时，此类采用组合的方式，来拿到进行文本，图片，网页，音乐，视频等的分享
 * 最后，当分享完毕，退出应用时，要调用releaseResource()方法来释放资源，
 */
public class CommonShareManager {

    private static CommonShareManager mInstance;

    public IShareOperate mShareOperate;

    /**
     * 微信API
     */
    private IWXAPI mIWeixinApi;
    /**
     * QQ，QQ空间
     */
    public Tencent mTencent;

    /**
     * 新浪微博分享
     */
    public IWeiboShareAPI mWeibo;
    public SsoHandler mSsoHandler;
    public  String mSinaAppKey;
    public  String mSinaRedirectUrl;
    /**
     * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
     * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
     * 选择赋予应用的功能。
     *
     * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
     * 使用权限，高级权限需要进行申请。
     *
     * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
     *
     * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
     * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
     */
    public  String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";


    public void setSsoHandler(SsoHandler mSsoHandler) {
        this.mSsoHandler = mSsoHandler;
    }

    public  void setSinaAppKey(String mSinaAppKey) {
        this.mSinaAppKey = mSinaAppKey;
    }

    public  void setSinaRedirectUrl(String mSinaRedirectUrl) {
        this.mSinaRedirectUrl = mSinaRedirectUrl;
    }

    public String getSinaAppKey() {
        return mSinaAppKey;
    }

    public String getSinaRedirectUrl() {
        return mSinaRedirectUrl;
    }

    /**
     * 实例化IWXAPI,通过Builder来实例化
     *
     * @param mIWeixinApi
     */
    private void setIWeixinApi(IWXAPI mIWeixinApi) {
        this.mIWeixinApi = mIWeixinApi;
    }

    /**
     * 实例化Tencent,
     *
     * @param mTencent
     */
    private void setTencent(Tencent mTencent) {
        this.mTencent = mTencent;
    }

    /**
     * 实例化IWeiboShareAPI
     *
     * @param mWeibo
     */
    private void setWeibo(IWeiboShareAPI mWeibo) {
        this.mWeibo = mWeibo;
    }

    public void setShareOperate(IShareOperate mShareOperate) {
        this.mShareOperate = mShareOperate;
    }

    public IShareOperate getShareOperate() {
        return mShareOperate;
    }


    public CommonShareManager() {
    }

    /**
     * 1.分享文本
     * 分享纯文本：目前只有微信可以
     */
    public void shareTextOnly(CommonShareTextOnly shareTextBean) {
        Object shareType = getShareObject(shareTextBean.getSharePlatform());
        mShareOperate.shareText(shareTextBean,shareType);
    }

    /**
     * 2.分享纯图片
     *
     * @param imgUrl        图片url,网络图片或本地图片
     * @param description   描述
     * @param sharePlatform 分享平台
     *                      分享类型，比如：微信(IWXAPI)，qq(Tencent),微博(IWeiboShareAPI)
     */
    public void sharePicOnly(CommonShareBaseBean sharePicBean) {
        mShareOperate.sharePic(sharePicBean, getShareObject(sharePicBean.getSharePlatform()));
    }

    /**
     * 3.分享图文
     * @param sharePicTextBean
     */
    public void sharePicText(CommonShareWebpage sharePicTextBean){
        mShareOperate.shareWebPage(sharePicTextBean,getShareObject(sharePicTextBean.getSharePlatform()));
    }

    /**
     * 4.分享网页
     * 目前只有微信 才有这一功能
     *
     * @param webUrl        网页链接
     * @param title         标题
     * @param description   描述
     * @param bm            图片
     * @param sharePlatform 分享平台
     *  分享类型，比如：微信(IWXAPI)，qq(Tencent),微博(IWeiboShareAPI)
     */
    public void shareWebpage(CommonShareWebpage webPageBean) {
        mShareOperate.shareWebPage(webPageBean, getShareObject(webPageBean.getSharePlatform()));
    }

    /**
     * 5.分享音乐
     *
     * @param musicUrl      音频网页地址：注，不能超过10k
     * @param musicDataUrl  音频数据地址： 注，不能超过10k
     * @param title         标题
     * @param description   描述
     * @param bm            图片
     * @param sharePlatform 分享平台
     * 分享类型，比如：微信(IWXAPI)，qq(Tencent),微博(IWeiboShareAPI)
     */
    public void shareMusic(CommonShareMusic shareBean) {

        mShareOperate.shareMusic(shareBean, getShareObject(shareBean.getSharePlatform()));
    }

    /**
     * 6.分享视频
     * @param videoUrl
     * @param title
     * @param description
     * @param bm
     * @param sharePlatform
     */
    public void shareVideo(CommonShareVideo shareVideo){
        mShareOperate.shareVideo(shareVideo,getShareObject(shareVideo.getSharePlatform()));
    }

    /**
     * 7.释放资源
     */
    public void releaseResource() {
        if (mIWeixinApi != null) {
            mIWeixinApi.unregisterApp();
        }
        if (mTencent != null) {
            mTencent.releaseResource();
        }

        if (mWeibo != null) {
            //
        }
    }


    /**
     * 获取单例实例
     *
     * @return
     */
    public static CommonShareManager getInstance() {
        if (mInstance == null) {
            synchronized (CommonShareManager.class) {
                if (mInstance == null) {
                    mInstance = new CommonShareManager(){};
                }
            }
        }

        return mInstance;
    }

    /**
     * 根据分享平台，获取相应的分享对象
     *
     * @param sharePlatform
     * @return
     */
    public Object getShareObject(CommonSharePlatform sharePlatform) {
        Object shareObject = null;
        if (sharePlatform == CommonSharePlatform.SinaWeibo) {
            shareObject = mWeibo;
        } else if (sharePlatform == CommonSharePlatform.WeixinFriendCircle ||
                sharePlatform == CommonSharePlatform.WeixinFriend) {
            shareObject = mIWeixinApi;
        } else if (sharePlatform == CommonSharePlatform.QQ_Friend ||
                sharePlatform == CommonSharePlatform.QQ_Weibo ||
                sharePlatform == CommonSharePlatform.QQ_Zone) {
            shareObject = mTencent;
        }


        return shareObject;
    }

    /**
     * 分享构造器内部类，
     * 上层可通过它，进行动态的注册
     */
    public static class Builder {
        private CommonShareManager mShareManger;
        private IShareOperate mShareOperate;
        private Activity context;

        /**
         * 微信API
         */
        private IWXAPI mIWeixinApi;
        /**
         * QQ，QQ空间
         */
        private Tencent mTencent;

        /**
         * 新浪微博分享
         */
        private IWeiboShareAPI mWeibo;
        private AuthInfo mAuthInfo;

        private String weixinId;
        private String sinaId;
        private String qqId;

        public Builder(Activity cxt) {
            FrescoManager.getInstance(cxt).init();
            mShareManger = CommonShareManager.getInstance();
            this.context = cxt;


            //Test.从AndroidManifest.xml中读取meta-data值，这里meta-data节点放在application下。
            //但是这样数据是不安全的。所以还是放在代码里面写的好。
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(cxt.getPackageName(),
                        PackageManager.GET_META_DATA);

                weixinId = applicationInfo.metaData.getString("WeixinAppID");
                sinaId = applicationInfo.metaData.getInt("SinaAppID")+"";
                qqId = applicationInfo.metaData.getInt("QQID")+"";

                Log.e("meta-data","WeixinId:"+weixinId+"\nsinaId:"+sinaId+"\nqqId"+qqId);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }

        /**
         * 微信注册
         */
        @Deprecated
        public Builder registerWeixin(String weixinAppId) {

            mIWeixinApi = WXAPIFactory.createWXAPI(context, weixinAppId, false);
            mIWeixinApi.registerApp(weixinAppId);

            mShareManger.setIWeixinApi(mIWeixinApi);
            return this;
        }
        public Builder registerWeixin(){
            mIWeixinApi = WXAPIFactory.createWXAPI(context, weixinId, false);
            mIWeixinApi.registerApp(weixinId);

            mShareManger.setIWeixinApi(mIWeixinApi);
            return this;
        }


        /**
         * QQ注册
         */
        @Deprecated
        public Builder registerQQ(String tencentAppId) {
            if (mTencent == null) {
                mTencent = Tencent.createInstance(tencentAppId, ((Activity)context).getApplicationContext());
            }
            mShareManger.setTencent(mTencent);
            return this;
        }
        public Builder registerQQ() {
            if (mTencent == null) {
                mTencent = Tencent.createInstance(qqId, ((Activity)context).getApplicationContext());
            }
            mShareManger.setTencent(mTencent);
            return this;
        }



        /**
         * 微博注册
         */
        @Deprecated
        public Builder registerSinaWeibo(String sinaAppKey,String redicrectUrl) {
            if (mWeibo == null) {
                mWeibo = WeiboShareSDK.createWeiboAPI(context, sinaAppKey);
            }
            mWeibo.registerApp();
            mShareManger.setWeibo(mWeibo);
            mShareManger.setSinaAppKey(sinaAppKey);
            mShareManger.setSinaRedirectUrl(redicrectUrl);
            return this;
        }
        public Builder registerSinaWeibo(String redicrectUrl) {
            if (mWeibo == null) {
                mWeibo = WeiboShareSDK.createWeiboAPI(context, sinaId);
            }
            mWeibo.registerApp();
            mShareManger.setWeibo(mWeibo);
            mShareManger.setSinaAppKey(sinaId);
            mShareManger.setSinaRedirectUrl(redicrectUrl);
            return this;
        }

        public Builder setShareOperate(IShareOperate shareOperate) {
            mShareOperate = shareOperate;
            return this;
        }

        /**
         * 注册所有
         * 一般是指注册 约定的几个
         *
         * @return
         */
        public Builder registerAll(String weixinAppId, String tencentAppId, String weiboAppKey,String wiboRedirectUrl) {

            registerWeixin(weixinAppId);
            registerQQ(tencentAppId);
            registerSinaWeibo(weiboAppKey,wiboRedirectUrl);
            return this;
        }


        /**
         * 生成CommonShareManager实例
         */
        public CommonShareManager create() {
            if(mShareOperate == null)
            mShareOperate = new CommonShareOperate(context);
            mShareManger.setShareOperate(mShareOperate);
            return mShareManger;
        }


    }
}
