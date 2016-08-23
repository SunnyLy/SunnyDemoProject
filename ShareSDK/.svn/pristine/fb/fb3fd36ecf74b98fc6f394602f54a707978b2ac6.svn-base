package com.het.share.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.het.share.QQ;
import com.het.share.R;
import com.het.share.SinaWeibo;
import com.het.share.WeiXin;
import com.het.share.listener.ICommonShareLinstener;
import com.het.share.model.CommonShareBean;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

/**
 * Created by sunny on 2016/1/8.
 * 分享的操作类
 * 当外界
 * 1，分享文本
 * 2，分享图片
 * 3，分享网页
 * 4，分享音乐
 * 5，分享视频
 */
public class CommonShareOperate {

    private static final int THUMB_SIZE = 120;

    private Context mContext;

    private WeiXin mWeixin;

    private QQ mQQ;

    private SinaWeibo mSinaWeibo;

    public CommonShareOperate(Context context) {
        this.mContext = context;
        mWeixin = new WeiXin(mContext);
        mQQ = new QQ(mContext);
        mSinaWeibo = new SinaWeibo(mContext);
    }


    /**
     * 1,分享文本
     *
     * @param content       分享的文本，当不传时，分享为空
     * @param sharePlatform 分享的平台：微信好友，微信朋友圈，QQ等
     * @param object        目标分享平台的对象，eg:IWXAPI
     */
    public void shareText(String content, CommonSharePlatform sharePlatform, Object object, ICommonShareLinstener shareLinstener) {

        boolean isInstalled = CommonShareUtils.isSupport(mContext, sharePlatform, object);
        if (!isInstalled) {
            if (shareLinstener != null) {
                shareLinstener.onShareFialure(sharePlatform, mContext.getResources().getString(R.string.share_not_supported));
            }
        }
        int type = SendMessageToWX.Req.WXSceneTimeline;
        try {
            if (sharePlatform == CommonSharePlatform.WeixinFriend) {
                //分享至微信好友
                type = SendMessageToWX.Req.WXSceneSession;
            } else if (sharePlatform == CommonSharePlatform.WeixinFriendCircle) {
                //分享至微信朋友圈
                type = SendMessageToWX.Req.WXSceneTimeline;
            }
            mWeixin.shareTextToWeixin(content, type, (IWXAPI) object);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("object必须传分享目标对象，eg:微信(IWXAPI),QQ(Tencent),微博(IWeiboShareAPI)");
        }

    }

    /**
     * 2，分享图片
     *
     * @param imgUrl         图片Url
     * @param imgDescription 图片描述
     * @param sharePlatform  分享的平台：微信好友，微信朋友圈，QQ等
     * @param object         目标分享平台的对象，eg:IWXAPI
     */
    public void sharePic(String imgUrl, String imgDescription, CommonSharePlatform sharePlatform, Object object) {

        boolean isInstalled = CommonShareUtils.isSupport(mContext, sharePlatform, object);
        if (isInstalled) {
            try {
                int type = SendMessageToWX.Req.WXSceneTimeline;
                if (sharePlatform == CommonSharePlatform.WeixinFriend) {
                    //分享至微信好友
                    type = SendMessageToWX.Req.WXSceneSession;
                } else if (sharePlatform == CommonSharePlatform.WeixinFriendCircle) {
                    //分享至微信朋友圈
                    type = SendMessageToWX.Req.WXSceneTimeline;
                }
                mWeixin.sharePicToWeixin(imgUrl, imgDescription, type, (IWXAPI) object);
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("object必须传分享目标对象，eg:微信(IWXAPI),QQ(Tencent),微博(IWeiboShareAPI)");
            }

        }
    }

    /**
     * 3.分享网页
     *
     * @param webUrl
     * @param title
     * @param description
     * @param bm
     * @param sharePlatform
     * @param object
     */
    public void shareWebPage(CommonShareBean webPageBean, Object object) {
        CommonSharePlatform sharePlatform = webPageBean.getSharePlatform();
        boolean isInstalled = CommonShareUtils.isSupport(mContext, sharePlatform, object);
        if (isInstalled) {
            try {
                if (sharePlatform == CommonSharePlatform.WeixinFriend
                        || sharePlatform == CommonSharePlatform.WeixinFriendCircle) {
                    //分享至微信好友
                    mWeixin.shareWebPageToWeixin(webPageBean.getTargetUrl(),
                            webPageBean.getTitle(),
                            webPageBean.getDescription(),
                            webPageBean.getBm(), sharePlatform, (IWXAPI) object);
                }else if(sharePlatform == CommonSharePlatform.QQ_Friend ||
                        sharePlatform == CommonSharePlatform.QQ_Zone ||
                        sharePlatform == CommonSharePlatform.QQ_Weibo){
                    mQQ.sharePicText(webPageBean.getTitle(),webPageBean.getDescription(),
                            webPageBean.getAppName(),new String[]{webPageBean.getImgUrl()},webPageBean.getTargetUrl(),
                            sharePlatform,(Tencent)object,webPageBean.getUiListener());
                }

            } catch (ClassCastException e) {
                throw new IllegalArgumentException("object必须传分享目标对象，eg:微信(IWXAPI),QQ(Tencent),微博(IWeiboShareAPI)");
            }

        }
    }

    /**
     * 4.分享音乐
     */
    public void shareMusic(CommonShareBean shareBean, Object object) {
        CommonSharePlatform sharePlatform = shareBean.getSharePlatform();
        boolean isInstalled = CommonShareUtils.isSupport(mContext, sharePlatform, object);
        if (isInstalled) {
            try {
                if (sharePlatform == CommonSharePlatform.WeixinFriend ||
                        sharePlatform == CommonSharePlatform.WeixinFriendCircle) {
                    //分享至微信好友
                    mWeixin.shareMusicToWeixin(shareBean.getMusicUrl(),
                            shareBean.getMusicDataUrl(), shareBean.getTitle(), shareBean.getDescription(),
                            shareBean.getBm(), sharePlatform, (IWXAPI) object);
                } else if (sharePlatform == CommonSharePlatform.QQ_Friend ||
                        sharePlatform == CommonSharePlatform.QQ_Weibo ||
                        sharePlatform == CommonSharePlatform.QQ_Zone) {

                    mQQ.shareMusic(shareBean.getTitle(),
                            shareBean.getDescription(), shareBean.getAppName(), shareBean.getImgUrl(),
                            shareBean.getMusicUrl(), shareBean.getMusicDataUrl(), sharePlatform, (Tencent) object, null);
                }
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("object必须传分享目标对象，eg:微信(IWXAPI),QQ(Tencent),微博(IWeiboShareAPI)");
            }

        } else {
            if (sharePlatform == CommonSharePlatform.QQ_Friend ||
                    sharePlatform == CommonSharePlatform.QQ_Zone ||
                    sharePlatform == CommonSharePlatform.QQ_Weibo) {

                mQQ.shareMusic(shareBean.getTitle(),
                        shareBean.getDescription(), shareBean.getAppName(), shareBean.getImgUrl(),
                        shareBean.getMusicUrl(), shareBean.getMusicDataUrl(), sharePlatform, (Tencent) object, null);
            }
        }
    }

    /**
     * 5.分享视频
     */
    public void shareVideo(String videoUrl, String title, String description, Bitmap bm, CommonSharePlatform sharePlatform, Object object) {
        boolean isInstalled = CommonShareUtils.isSupport(mContext, sharePlatform, object);
        if (isInstalled) {
            int type = SendMessageToWX.Req.WXSceneTimeline;
            try {
                if (sharePlatform == CommonSharePlatform.WeixinFriend) {
                    //分享至微信好友
                    type = SendMessageToWX.Req.WXSceneSession;
                } else if (sharePlatform == CommonSharePlatform.WeixinFriendCircle) {
                    //分享至微信朋友圈
                    type = SendMessageToWX.Req.WXSceneTimeline;
                }
                mWeixin.shareVideoToWeixin(videoUrl, bm, title, description, type, (IWXAPI) object);
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("object必须传分享目标对象，eg:微信(IWXAPI),QQ(Tencent),微博(IWeiboShareAPI)");
            }

        }
    }


    /**
     * 分享文本至QQ
     *
     * @param content
     * @param platform
     * @param tencent
     */
    public void shareTextToTencent(final String content, CommonSharePlatform platform, final Tencent tencent, IUiListener uiListener) {
        Bundle mParams = new Bundle();
        mParams.putString(QQShare.SHARE_TO_QQ_SUMMARY, content);
        if (platform == CommonSharePlatform.QQ_Friend) {
            mQQ.shareToQQ(mParams, tencent, uiListener);
        } else if (platform == CommonSharePlatform.QQ_Zone) {
            mQQ.shareToQQZone(mParams, tencent, uiListener);
        } else if (platform == CommonSharePlatform.QQ_Weibo) {
            mQQ.shareToQQWeibo(mParams, tencent, uiListener);
        }

    }

    /**
     * 分享图文信息至QQ好友
     */
    private void sharePicTextToTencent(String title, String description, String appName, String[] imgUrl, String targetUrl,
                                       CommonSharePlatform platform, Tencent tencent, IUiListener uiListener) {
        mQQ.sharePicText(title, description, appName, imgUrl, targetUrl, platform, tencent, uiListener);
    }

    /**
     * 分享应用至QQ好友
     * 应用分享后，发送方和接收方在聊天窗口中点击消息气泡即可进入应用的详情页。
     */
    private void shareAppToQQ(String title, String description, String appName, String imgUrl, String targetUrl,
                              CommonSharePlatform platform, Tencent tencent, IUiListener uiListener) {
        mQQ.shareApp(title, description, appName, imgUrl, targetUrl, platform, tencent, uiListener);
    }

    /**
     * 分享音乐至QQ好友
     * 音乐分享后，发送方和接收方在聊天窗口中点击消息气泡即可开始播放音乐。
     */
    private void shareMusicToQQ(String title, String description, String appName,
                                String imgUrl, String targetUrl, String musicUrl,
                                CommonSharePlatform platform, Tencent tencent, IUiListener uiListener) {
        mQQ.shareMusic(title, description, appName, imgUrl, targetUrl, musicUrl, platform, tencent, uiListener);
    }


}
