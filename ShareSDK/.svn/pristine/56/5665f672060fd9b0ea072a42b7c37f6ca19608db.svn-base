package com.het.share;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.het.share.manager.CommonSharePlatform;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

/**
 * QQ工具类：
 * 1，分享图文
 * 2，分享纯图片
 * 3，分享App应用
 * 4,分享音乐
 * 注：腾讯开放平台
 * 1.14 分享到QQ空间（无需QQ登录）
 * 完善了分享到QZone功能，分享类型参数Tencent.SHARE_TO_QQ_KEY_TYPE，目前只支持图文分享。
 * Tencent. shareToQzone()函数可直接调用，不用用户授权（使用手机QQ当前的登录态）。
 * 调用后将打开手机QQ内QQ空间的界面，或者用浏览器打开QQ空间页面进行分享操作。
 */
public class QQ {
    private Context mContext;

    public QQ(Context context) {
        this.mContext = context;
    }

    /**
     * 1.分享图文信息至QQ好友
     */
    public void sharePicText(String title, String description, String appName, String[] imgUrl, String targetUrl,
                             CommonSharePlatform platform, Tencent tencent, IUiListener uiListener) {
        Bundle mParams = new Bundle();
        mParams.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        mParams.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        mParams.putString(QQShare.SHARE_TO_QQ_SUMMARY, description);
        mParams.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
        mParams.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        if (platform == CommonSharePlatform.QQ_Friend) {
            if (imgUrl == null || imgUrl.length == 0) {
                return;
            }

            if (imgUrl[0].startsWith("http://") || imgUrl[0].startsWith("https://")) {
                mParams.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl[0]);
            } else {
                mParams.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgUrl[0]);
            }
            tencent.shareToQQ((Activity) mContext, mParams, uiListener);
        } else if (platform == CommonSharePlatform.QQ_Zone) {
            ArrayList<String> imgs = new ArrayList<>();
            for (String picUrl : imgUrl) {
                imgs.add(picUrl);
            }
            mParams.putStringArrayList(QQShare.SHARE_TO_QQ_IMAGE_URL, imgs);
            tencent.shareToQzone((Activity) mContext, mParams, uiListener);
        } else if (platform == CommonSharePlatform.QQ_Weibo) {
            //分享至腾讯微博
        }
    }

    /**
     * 2.分享应用至QQ好友
     * 应用分享后，发送方和接收方在聊天窗口中点击消息气泡即可进入应用的详情页。
     */
    public void shareApp(String title, String description, String appName, String imgUrl, String targetUrl,
                         CommonSharePlatform platform, Tencent tencent, IUiListener uiListener) {
        Bundle mParams = new Bundle();
        mParams.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
        mParams.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        mParams.putString(QQShare.SHARE_TO_QQ_SUMMARY, description);
        mParams.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
        mParams.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        mParams.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);
        if (platform == CommonSharePlatform.QQ_Friend) {
            tencent.shareToQQ((Activity) mContext, mParams, uiListener);
        }else if(platform == CommonSharePlatform.QQ_Zone){
            tencent.shareToQzone((Activity)mContext,mParams,uiListener);
        }
    }

    /**
     * 3.分享音乐至QQ好友
     * 音乐分享后，发送方和接收方在聊天窗口中点击消息气泡即可开始播放音乐。
     */
    public void shareMusic(String title, String description, String appName,
                           String imgUrl, String targetUrl, String musicUrl,
                           CommonSharePlatform platform, Tencent tencent, IUiListener uiListener) {
        Bundle mParams = new Bundle();
        mParams.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO);
        mParams.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        mParams.putString(QQShare.SHARE_TO_QQ_SUMMARY, description);
        mParams.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
        mParams.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        mParams.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);
        mParams.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, musicUrl);
        mParams.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        if (platform == CommonSharePlatform.QQ_Friend) {
            tencent.shareToQQ((Activity) mContext, mParams, uiListener);
        }else if(platform == CommonSharePlatform.QQ_Zone){
            tencent.shareToQzone((Activity)mContext,mParams,uiListener);
        }
    }


    /**
     * 4.分享纯图片
     */
    public void sharePicJust(String appName, String imgUrl, Tencent tencent, IUiListener uiListener) {
        Bundle mParams = new Bundle();
        mParams.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
        mParams.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgUrl);
        mParams.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        mParams.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        tencent.shareToQQ((Activity) mContext, mParams, uiListener);
    }

    /**
     * 分享至QQ微博
     *
     * @param mParams
     * @param tencent
     * @param uiListener
     */
    public void shareToQQWeibo(Bundle mParams, Tencent tencent, IUiListener uiListener) {
    }

    /**
     * 分享至QQ空间
     *
     * @param mParams
     * @param tencent
     * @param uiListener
     */
    public void shareToQQZone(final Bundle mParams, final Tencent tencent, final IUiListener uiListener) {
        new Thread() {
            @Override
            public void run() {
                tencent.shareToQzone((Activity) mContext, mParams, uiListener);
            }
        }.start();
    }

    /**
     * 分享至QQ好友
     *
     * @param mParams
     * @param tencent
     * @param uiListener
     */
    public void shareToQQ(final Bundle mParams, final Tencent tencent, final IUiListener uiListener) {
        new Thread() {
            @Override
            public void run() {
                tencent.shareToQQ((Activity) mContext, mParams, uiListener);
            }
        }.start();
    }
}
