package com.het.common.utils;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import android.content.Context;
import android.text.TextUtils;

import com.het.common.constant.CommonConsts;

/**
 * @ClassName: HttpUtils
 * @Description: Http请求通用类
 * @Author: clark
 * @Create: 2013-5-26
 */

public final class HttpUtils {

    /****************************** 日志 ******************************/

    /**
     * 私有的构造方法.
     */
    private HttpUtils() {
    }

    /**
     * 检测移动网络是否可用.
     *
     * @param context 上下文信息
     * @return true表示移动网络可用，否则不可用
     */
    public static boolean isMobileAvailable(final Context context) {
        return NetworkHelper.isMobileAvailable(context);
    }

    /**
     * 检测Wifi是否可用.
     *
     * @param context 上下文信息
     * @return true表示Wifi可用，否则不可用
     */
    public static boolean isWifiAvailable(final Context context) {
        return NetworkHelper.isWifiAvailable(context);
    }

    /**
     * 生成网络请求的代理信息.
     *
     * @param context 上下文信息
     * @return 网络请求的代理信息
     * @throws java.io.UnsupportedEncodingException 不支持的字符集
     */
    public static String genUserAgent(final Context context)
            throws UnsupportedEncodingException {
        StringBuffer sbUserAgent = new StringBuffer();
        //C-life;V1.1.7;Android;samsung;SM-N9005;4.4.2;KOT49H.N9005ZHUENB5;Wi-Fi网络;
//        -------------------------------------------------------------------------
        sbUserAgent.append("C-life");//应用名称
        sbUserAgent.append(CommonConsts.SEMICOLON);
        sbUserAgent.append(SystemInfoUtils.getAppVersionName(context)); // App版本
        sbUserAgent.append(CommonConsts.SEMICOLON);
        sbUserAgent.append(CommonConsts.SourceType);//设备分类
        sbUserAgent.append(CommonConsts.SEMICOLON);
        sbUserAgent.append(SystemInfoUtils.getOSVersionName()); // OS版本
        sbUserAgent.append(CommonConsts.SEMICOLON);
        sbUserAgent.append(SystemInfoUtils.getOSVersionDisplayName()); // OS显示版本
        sbUserAgent.append(CommonConsts.SEMICOLON);
        sbUserAgent.append(SystemInfoUtils.getBrandName()); // 品牌厂商
        sbUserAgent.append(CommonConsts.SEMICOLON);
        sbUserAgent.append(SystemInfoUtils.getModelName()); // 设备
        sbUserAgent.append(CommonConsts.SEMICOLON);
        sbUserAgent.append("480*800"); // 分辨率
        sbUserAgent.append(CommonConsts.SEMICOLON);
        sbUserAgent.append("Het"); // 分发渠道
        sbUserAgent.append(CommonConsts.SEMICOLON);
        if(isWifiAvailable(context)){
        	  sbUserAgent.append("wifi"); // 网络类型
        }else if(isMobileAvailable(context)){
        	  sbUserAgent.append("mobile"); // 网络类型
        }
        sbUserAgent.append(CommonConsts.SEMICOLON);
        return sbUserAgent.toString();
    }
    
    public static String getHeader(HttpResponse response, String key) {
		Header header = response.getFirstHeader(key);
		return header == null ? null : header.getValue();
	}
    
    public static boolean isSupportRange(HttpResponse response) {
		if (TextUtils.equals(getHeader(response, "Accept-Ranges"), "bytes")) {
			return true;
		}
		String value = getHeader(response, "Content-Range");
		return value != null && value.startsWith("bytes");
	}
    
    public static boolean isGzipContent(HttpResponse response) {
		return TextUtils.equals(getHeader(response, "Content-Encoding"), "gzip");
	}
    
}
