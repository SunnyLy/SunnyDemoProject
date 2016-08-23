package com.het.share;

import android.app.Activity;
import android.content.Context;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;

/**
 * Created by sunny on 2016/1/9.
 * 新浪微博基本实现类：
 * 1，分享图文
 * 2，分享图片
 * 3，分享音乐
 * 4，分享视频
 * 5，分享语音
 * ……
 */
public class SinaWeibo {
    private Context mContext;

    public SinaWeibo(Context context) {
        this.mContext = context;
    }

    /**
     * 1.新浪微博分享文本
     *
     * @param title          标题
     * @param description    描述
     * @param mWeiboShareAPI 新浪微博分享对像
     */
    private void shareTextToSina(String title, String description, IWeiboShareAPI mWeiboShareAPI) {

        // 1. 初始化微博的分享消息
        TextObject textObj = new TextObject();
        textObj.title = title;
        textObj.text = description;
        // WebpageObject
        WebpageObject webObj = new WebpageObject();

        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.textObject = textObj;

        // 用户可以分享其它媒体资源（网页、音乐、视频、声音中的一种）
        weiboMessage.mediaObject = webObj;

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        mWeiboShareAPI.sendRequest((Activity) mContext, request);

    }

    /**
     * 2.分享图片
     *
     * @param title
     * @param description
     * @param imgUrl
     * @param mWeiboShareAPI
     */
    private void sendMultiMessage(String title, String description, String imgUrl, IWeiboShareAPI mWeiboShareAPI) {

        // 1. 初始化微博的分享消息
        TextObject textObj = new TextObject();
        textObj.title = title;
        textObj.text = description;
        // imageObject
        ImageObject imageObj = new ImageObject();
        imageObj.imagePath = imgUrl;
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.textObject = textObj;

        weiboMessage.imageObject = imageObj;

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        mWeiboShareAPI.sendRequest((Activity) mContext, request);

    }

    /**
     * 3.分享网页
     *
     * @param title       网页标题
     * @param description 网页描述
     * @param imgUrl      图片地址
     * @param targetUrl   网页链接地址
     */
    public void shareWebpageToSina(String title, String description, String targetUrl, String imgUrl, IWeiboShareAPI weiboShareAPI) {
        // 1. 初始化微博的分享消息
        TextObject textObj = new TextObject();
        textObj.title = title;
        textObj.text = description;
        // imageObject
        ImageObject imageObj = new ImageObject();
        imageObj.imagePath = imgUrl;
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.textObject = textObj;
        //webpageObject
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.actionUrl = targetUrl;

        weiboMessage.imageObject = imageObj;
        weiboMessage.mediaObject = webpageObject;

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        weiboShareAPI.sendRequest((Activity) mContext, request);
    }

    /**
     * 4.分享音乐
     *
     * @param title        音乐标题
     * @param description  音乐描述
     * @param imgUrl       图片路径
     * @param musicUrl     音乐的网页链接
     * @param musicDataUrl 歌曲的地址
     */
    public void shareMusicToSina(String title, String description,
                                 String imgUrl, String musicUrl, String musicDataUrl, IWeiboShareAPI weiboShareAPI) {
        // 1. 初始化微博的分享消息
        TextObject textObj = new TextObject();
        textObj.title = title;
        textObj.text = description;
        // imageObject
        ImageObject imageObj = new ImageObject();
        imageObj.imagePath = imgUrl;
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.textObject = textObj;
        //MusicObject
        MusicObject musicObject = new MusicObject();
        musicObject.dataUrl = musicDataUrl;
        musicObject.h5Url = musicUrl;

        weiboMessage.imageObject = imageObj;
        weiboMessage.mediaObject = musicObject;

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        weiboShareAPI.sendRequest((Activity) mContext, request);
    }

    /**
     * 5.分享视频
     *
     * @param title        视频标题
     * @param description  视频描述
     * @param imgUrl       视频图片
     * @param videoUrl     视频网页地址
     * @param videoDataUrl 视频资源地址
     */
    public void shareVideoToSina(String title, String description, String imgUrl,
                                 String videoUrl, String videoDataUrl, IWeiboShareAPI weiboShareAPI) {
        // 1. 初始化微博的分享消息
        TextObject textObj = new TextObject();
        textObj.title = title;
        textObj.text = description;
        // imageObject
        ImageObject imageObj = new ImageObject();
        imageObj.imagePath = imgUrl;
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.textObject = textObj;
        //VideoObject
        VideoObject videoObject = new VideoObject();
        videoObject.dataUrl = videoDataUrl;
        videoObject.h5Url = videoUrl;

        weiboMessage.imageObject = imageObj;
        weiboMessage.mediaObject = videoObject;

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        weiboShareAPI.sendRequest((Activity) mContext, request);
    }

}
