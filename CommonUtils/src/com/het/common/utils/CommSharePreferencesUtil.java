/*
 * -----------------------------------------------------------------
 * Copyright (C) 2012-2013, by Het, ShenZhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: AppPreferencesUtils.java
 * Author: clark
 * Version: 1.0
 * Create: 2013-9-27
 *
 * Changes (from 2013-9-27)
 * -----------------------------------------------------------------
 * 2013-9-27 : 创建 AppPreferencesUtils.java (clark);
 * -----------------------------------------------------------------
 */
package com.het.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.het.common.constant.CommonConstant;

/**
 * @ClassName: AppPreferencesUtils
 * @Description: App共享属性通用属性
 * @Author: clark
 * @Create: 2013-9-27
 */

public final class CommSharePreferencesUtil {

    /**
     * 未识别的Key.
     */
    public static final int INT_KEY_NOT_FOUND = -99999;

    /**
     * 未识别的Key.
     */
    public static final String STRING_KEY_NOT_FOUND = null;

    /**
     * 未识别的Key.
     */
    public static final boolean BOOLEAN_KEY_NOT_FOUND = false;

    /**
     * 私有的构造方法
     */
    private CommSharePreferencesUtil() {
    }

    /**
     * 获取App SharePreferences实例.
     *
     * @param context 上下文信使
     * @return App SharePreferences实例
     */
    private static SharedPreferences getAppPreferences(final Context context) {
        SharedPreferences appPreferences = context.getSharedPreferences(
        		CommonConstant.APP_PREFERENCE, Context.MODE_MULTI_PROCESS);
        return appPreferences;
    }

    /**
     * 移出App SharePreferences中的键
     *
     * @param context 上下文信
     * @param strKey 
     */
    public static void removeKey(final Context context, final String strKey) {
        SharedPreferences appPreferences = CommSharePreferencesUtil
                .getAppPreferences(context);
        Editor editor = appPreferences.edit();
        editor.remove(strKey);
        editor.commit();
    }

    /**
     * 移出App SharePreferences中所有的键
     *
     * @param context 上下文信
     */
    public static void clear(final Context context) {
        SharedPreferences appPreferences = CommSharePreferencesUtil
                .getAppPreferences(context);
        Editor editor = appPreferences.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 将键值对保存到App SharePreferences
     *
     * @param context 上下文信
     * @param strKey  
     * @param iValue  
     */
    public static void putInt(final Context context, final String strKey,
                              final int iValue) {
        SharedPreferences appPreferences = CommSharePreferencesUtil
                .getAppPreferences(context);
        Editor editor = appPreferences.edit();
        editor.putInt(strKey, iValue);
        editor.commit();
    }

    /**
     * 将键值对保存到App SharePreferences
     *
     * @param context 上下文信
     * @param strKey  
     * @param bFlag 
     */
    public static void putBoolean(final Context context, final String strKey,
                                  final boolean bFlag) {
        SharedPreferences appPreferences = CommSharePreferencesUtil
                .getAppPreferences(context);
        Editor editor = appPreferences.edit();
        editor.putBoolean(strKey, bFlag);
        editor.commit();
    }

    /**
     * 将键值对保存到App SharePreferences
     *
     * @param context  上下文
     * @param strKey   
     * @param strValue 
     */
    public static void putString(final Context context, final String strKey,
                                 final String strValue) {
        SharedPreferences appPreferences = CommSharePreferencesUtil
                .getAppPreferences(context);
        Editor editor = appPreferences.edit();
        editor.putString(strKey, strValue);
        editor.commit();
    }

    /**
     * 从App SharePreferences中读取键对应的
     *
     * @param context 上下文信
     * @param strKey 
     * @return App SharePreferences中读取键对应的，未找到返回
     * {@link CommSharePreferencesUtil#INT_KEY_NOT_FOUND}
     */
    public static int getInt(final Context context, final String strKey) {
        SharedPreferences appPreferences = CommSharePreferencesUtil
                .getAppPreferences(context);
        return appPreferences.getInt(strKey,
                CommSharePreferencesUtil.INT_KEY_NOT_FOUND);
    }

    /**
     * 从App SharePreferences中读取键对应的
     *
     * @param context 上下文信
     * @param strKey 
     * @return App SharePreferences中读取键对应的，未找到返回
     * {@link CommSharePreferencesUtil#BOOLEAN_KEY_NOT_FOUND}
     */
    public static boolean getBoolean(final Context context, final String strKey) {
        SharedPreferences appPreferences = CommSharePreferencesUtil
                .getAppPreferences(context);
        return appPreferences.getBoolean(strKey,
                CommSharePreferencesUtil.BOOLEAN_KEY_NOT_FOUND);
    }

    /**
     * 从App SharePreferences中读取键对应的
     *
     * @param context 上下文信
     * @param strKey  
     * @return App SharePreferences中读取键对应的，未找到返回
     * {@link CommSharePreferencesUtil#STRING_KEY_NOT_FOUND}
     */
    public static String getString(final Context context, final String strKey) {
        SharedPreferences appPreferences = CommSharePreferencesUtil
                .getAppPreferences(context);
        return appPreferences.getString(strKey,
                CommSharePreferencesUtil.STRING_KEY_NOT_FOUND);
    }

    
    
    public static void setIsNewUser(final Context context){
    	CommSharePreferencesUtil.putBoolean(context, "newuser", true);
    }
    
    public static boolean isNewUser(final Context context){
    	return CommSharePreferencesUtil.getBoolean(context, "newuser");
    }
}
