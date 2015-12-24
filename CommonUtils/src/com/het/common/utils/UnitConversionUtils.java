package com.het.common.utils;

import android.content.Context;

/**
 * 单位转换工具类
 * @author sunny
 * @date 2015年5月6日 下午2:08:17
 */
public class UnitConversionUtils {
	
	/**
	 * dip转px
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(final Context context, final float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px转dip
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int px2dip(final Context context, final float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	

}
