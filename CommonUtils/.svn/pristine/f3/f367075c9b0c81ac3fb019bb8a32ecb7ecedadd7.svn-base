package com.het.common.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	private ToastUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isShow = true;

	/**
	 * ?????Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		if (isShow && context != null)
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ?????Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, int message) {
		if (isShow && context != null)
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ?????Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		if (isShow && context != null)
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * ?????Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, int message) {
		if (isShow && context != null)
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * ?????Toast??
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, CharSequence message, int duration) {
		if (isShow && context != null)
			Toast.makeText(context, message, duration).show();
	}

	/**
	 * ?????Toast??
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, int message, int duration) {
		if (isShow && context != null)
			Toast.makeText(context, message, duration).show();
	}

}