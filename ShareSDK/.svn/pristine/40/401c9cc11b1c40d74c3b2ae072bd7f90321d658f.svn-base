package com.het.share.manager;

import android.content.Context;
import android.graphics.Bitmap;

import com.het.share.listener.ICommonShareLinstener;
import com.het.share.model.CommonShareBean;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
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

    private CommonShareOperate mShareOperate;

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

    public void setShareOperate(CommonShareOperate mShareOperate) {
        this.mShareOperate = mShareOperate;
    }

    private CommonShareManager() {
    }

    /**
     * 1.分享文本
     */
    public void shareText(String content, CommonSharePlatform sharePlatform,
                          ICommonShareLinstener shareLinstener) {
        Object shareType = getShareObject(sharePlatform);
        mShareOperate.shareText(content, sharePlatform, shareType, shareLinstener);
    }

    /**
     * 2.分享图片
     *
     * @param imgUrl        图片url,网络图片或本地图片
     * @param description   描述
     * @param sharePlatform 分享平台
     *                      分享类型，比如：微信(IWXAPI)，qq(Tencent),微博(IWeiboShareAPI)
     */
    public void sharePic(String imgUrl, String description, CommonSharePlatform sharePlatform) {
        mShareOperate.sharePic(imgUrl, description, sharePlatform, getShareObject(sharePlatform));
    }

    /**
     * 3.分享网页
     *
     * @param webUrl        网页链接
     * @param title         标题
     * @param description   描述
     * @param bm            图片
     * @param sharePlatform 分享平台
     *  分享类型，比如：微信(IWXAPI)，qq(Tencent),微博(IWeiboShareAPI)
     */
    public void shareWebpage(CommonShareBean webPageBean) {
        mShareOperate.shareWebPage(webPageBean, getShareObject(webPageBean.getSharePlatform()));
    }

    /**
     * 4.分享音乐
     *
     * @param musicUrl      音频网页地址：注，不能超过10k
     * @param musicDataUrl  音频数据地址： 注，不能超过10k
     * @param title         标题
     * @param description   描述
     * @param bm            图片
     * @param sharePlatform 分享平台
     * 分享类型，比如：微信(IWXAPI)，qq(Tencent),微博(IWeiboShareAPI)
     */
    public void shareMusic(CommonShareBean shareBean) {

        mShareOperate.shareMusic(shareBean, getShareObject(shareBean.getSharePlatform()));
    }

    public void shareVideo(String videoUrl,String title,String description,Bitmap bm,CommonSharePlatform sharePlatform){
        mShareOperate.shareVideo(videoUrl,title,description,bm,sharePlatform,getShareObject(sharePlatform));
    }

    /**
     * 释放资源
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
    private static CommonShareManager getInstance() {
        if (mInstance == null) {
            synchronized (CommonShareManager.class) {
                if (mInstance == null) {
                    mInstance = new CommonShareManager();
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
    private Object getShareObject(CommonSharePlatform sharePlatform) {
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
        private CommonShareOperate mShareOperate;
        private Context context;

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

        public Builder(Context cxt) {

            mShareManger = CommonShareManager.getInstance();
            this.context = cxt;

        }

        /**
         * 微信注册
         */
        public Builder registerWeixin(String weixinAppId) {

            mIWeixinApi = WXAPIFactory.createWXAPI(context, weixinAppId, false);
            mIWeixinApi.registerApp(weixinAppId);

            mShareManger.setIWeixinApi(mIWeixinApi);
            return this;
        }


        /**
         * QQ注册
         */
        public Builder registerQQ(String tencentAppId) {
            if (mTencent == null) {
                mTencent = Tencent.createInstance(tencentAppId, context);
            }

            mShareManger.setTencent(mTencent);
            return this;
        }


        /**
         * 微博注册
         */
        public Builder registerSinaWeibo(String sinaAppKey) {

            if (mWeibo == null) {
                mWeibo = WeiboShareSDK.createWeiboAPI(context, sinaAppKey);
            }

            mWeibo.registerApp();

            mShareManger.setWeibo(mWeibo);
            return this;
        }

        /**
         * 注册所有
         * 一般是指注册 约定的几个
         *
         * @return
         */
        public Builder registerAll(String weixinAppId, String tencentAppId, String weiboAppKey) {

            registerWeixin(weixinAppId);
            registerQQ(tencentAppId);
            registerSinaWeibo(weiboAppKey);
            return this;
        }


        /**
         * 生成CommonShareManager实例
         */
        public CommonShareManager create() {
            mShareOperate = new CommonShareOperate(context);
            mShareManger.setShareOperate(mShareOperate);
            return mShareManger;
        }


    }
}
