package com.het.share.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.utils.LogUtil;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.tauth.Tencent;

import java.io.File;
import java.io.IOException;

/**
 * Created by sunny on 2016/1/8.
 * 分享通用工具类
 * 主要用来处理：判断是否安装客户端，图片下载 ，图片压缩等
 */
public class CommonShareUtils {
    private static String SDK_PATH = "/mnt/sdcard/";

    /**
     * 根据分享的平台，
     * 来判断是否已经安装客户端，及SDK版本是否支持
     * @param sharePlatform
     * @param t 第三方分享对象，不可为空
     * @return
     */
    public static boolean isSupport(Context context,CommonSharePlatform sharePlatform, Object t){
        boolean isSupport = true;
        if( t == null){
            throw new NullPointerException("the T may not be null,eg.IWXAPI");
        }

        //微信
        if(sharePlatform == CommonSharePlatform.WeixinFriend || sharePlatform == CommonSharePlatform.WeixinFriendCircle){
            IWXAPI iwxapi = (IWXAPI) t;
            isSupport = iwxapi.isWXAppInstalled()?iwxapi.isWXAppSupportAPI():false;
        }else if(sharePlatform == CommonSharePlatform.QQ_Friend||
                sharePlatform == CommonSharePlatform.QQ_Weibo ||
                sharePlatform == CommonSharePlatform.QQ_Zone){
            //QQ
            Tencent tencent = (Tencent) t;
            isSupport = tencent.isSupportSSOLogin((Activity)context);
        }else if(sharePlatform == CommonSharePlatform.SinaWeibo){
            //SinaWeibo
            IWeiboShareAPI iWeiboShareAPI = (IWeiboShareAPI) t;
            isSupport = iWeiboShareAPI.isWeiboAppInstalled()?iWeiboShareAPI.isWeiboAppSupportAPI():false;
        }
        return isSupport;
    }

    public static File creatSDFile(String path, String fileName) throws IOException {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return null;
        }
        File file = new File(SDK_PATH + path + fileName);
        if(!isFileExist(path, fileName)){
            file.mkdir();
            file.createNewFile();
        }
        return file;
    }

    /**
     * 判断SD卡上的文件夹是否存在
     */
    public static boolean isFileExist(String path, String fileName) {
        File file = new File(SDK_PATH + path + fileName);
        return file.exists();
    }


    /**
     * 标记请求的唯一性
     * @param type
     * @return
     */
    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }

}
