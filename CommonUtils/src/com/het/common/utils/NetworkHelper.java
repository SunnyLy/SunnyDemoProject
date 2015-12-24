/*
 * -----------------------------------------------------------------
 * Copyright (C) 2012-2013, by Het, ShenZhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: NetworkHelper.java
 * Author: clark
 * Version: 1.0
 * Create: 2013-5-25
 *
 * Changes (from 2013-5-25)
 * -----------------------------------------------------------------
 * 2013-5-25 : 创建 NetworkHelper.java (clark);
 * -----------------------------------------------------------------
 */

package com.het.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * @ClassName: NetworkHelper
 * @Description: �?��网络的类
 * @Author: clark
 * @Create: 2013-5-25
 */

public final class NetworkHelper {

    /****************************** 常量 ******************************/

    /**
     * 未知网络类型.
     */
    public static final String NETWORK_TYPE_UNKNOW = "未知网络";

    /**
     * 其他网络类型.
     */
    public static final String NETWORK_TYPE_OTHER = "其他网络";

    /**
     * Wi-Fi网络类型.
     */
    public static final String NETWORK_TYPE_WIFI = "Wi-Fi网络";

    /**
     * 电信2G网络.
     */
    public static final String NETWORK_TYPE_2G_TELECOM = "电信2G网络";

    /**
     * 移动2G网络.
     */
    public static final String NETWORK_TYPE_2G_MOBILE = "移动2G网络";

    /**
     * 联�?2G网络.
     */
    public static final String NETWORK_TYPE_2G_UNICOM = "联通2G网络";

    /**
     * 电信3G网络.
     */
    public static final String NETWORK_TYPE_3G_TELECOM = "电信3G网络";

    /**
     * 联�?3G网络.
     */
    public static final String NETWORK_TYPE_3G_UNICOM = "联通3G网络";

    /**
     * 私有的构造方�?
     */
    private NetworkHelper() {
    }

    /**
     * 从上下文信息中获取ConnectivityManager.
     *
     * @param context 上下文信�?
     * @return 上下文信息中获取的ConnectivityManager
     */
    private static ConnectivityManager getConnectivityManager(
            final Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager;
    }

    /**
     * 从上下文信息中获取指定的NetworkInfo.
     *
     * @param context      上下文信�?
     * @param iNetworkType 网络类型
     * @return 上下文信息中获取到的NetworkInfo
     */
    private static NetworkInfo getNetworkInfo(final Context context,
                                              final int iNetworkType) {
        ConnectivityManager connectivityManager = NetworkHelper
                .getConnectivityManager(context);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getNetworkInfo(iNetworkType);
        }
        return networkInfo;
    }

    /**
     * 从上下文信息中获取活动的NetworkInfo.
     *
     * @param context 上下文信�?
     * @return 上下文信息中获取到活动的NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo(final Context context) {
        ConnectivityManager connectivityManager = NetworkHelper
                .getConnectivityManager(context);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo;
    }

    /**
     * �?��网络是否可用.
     *
     * @param context 上下文信�?
     * @return ture表示网络可用，否则不可用
     */
    public static boolean isNetworkAvailable(final Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo networkInfo = NetworkHelper.getActiveNetworkInfo(context);
        if (networkInfo != null) {
            return networkInfo.isConnectedOrConnecting();
        }
        return false;
    }

    public static boolean isWiFiActive(Context inContext) {
        Context context = inContext.getApplicationContext();
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    /**
     * �?��移动网络是否可用.
     *
     * @param context 上下文信�?
     * @return true表示移动网络可用，否则不可用
     */
    public static boolean isMobileAvailable(final Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo networkInfo = NetworkHelper.getNetworkInfo(context,
                ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null) {
            return networkInfo.isConnectedOrConnecting();
        }
        return false;
    }

    /**
     * �?��Wifi是否可用.
     *
     * @param context 上下文信�?
     * @return true表示Wifi可用，否则不可用
     */
    public static boolean isWifiAvailable(final Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo networkInfo = NetworkHelper.getNetworkInfo(context,
                ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null) {
            return networkInfo.isConnectedOrConnecting();
        }
        return false;
    }

    /**
     * 获取当前网络类型.
     *
     * @param context 上下文信�?
     * @return 当前网络类型
     */
    public static String getNetworkType(final Context context) {
        if (context == null) {
            return NetworkHelper.NETWORK_TYPE_UNKNOW;
        }
        String strNetWorkType = NetworkHelper.NETWORK_TYPE_UNKNOW;
        ConnectivityManager connectivityManager = NetworkHelper
                .getConnectivityManager(context);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                switch (networkInfo.getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        strNetWorkType = NetworkHelper.NETWORK_TYPE_WIFI;
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        switch (networkInfo.getSubtype()) {
                            case TelephonyManager.NETWORK_TYPE_GPRS:
                                strNetWorkType = NetworkHelper.NETWORK_TYPE_2G_UNICOM;
                                break;
                            case TelephonyManager.NETWORK_TYPE_EDGE:
                                strNetWorkType = NetworkHelper.NETWORK_TYPE_2G_MOBILE;
                                break;
                            case TelephonyManager.NETWORK_TYPE_CDMA:
                                strNetWorkType = NetworkHelper.NETWORK_TYPE_2G_TELECOM;
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                                strNetWorkType = NetworkHelper.NETWORK_TYPE_3G_TELECOM;
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSDPA:
                            case TelephonyManager.NETWORK_TYPE_UMTS:
                                strNetWorkType = NetworkHelper.NETWORK_TYPE_3G_UNICOM;
                                break;
                            default:
                                strNetWorkType = NetworkHelper.NETWORK_TYPE_OTHER;
                                break;
                        }
                        break;
                    default:
                        strNetWorkType = NetworkHelper.NETWORK_TYPE_OTHER;
                        break;
                }
            }
        }
        return strNetWorkType;
    }
}
