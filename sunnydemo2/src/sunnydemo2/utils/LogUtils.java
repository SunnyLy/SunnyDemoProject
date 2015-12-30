package sunnydemo2.utils;

import android.util.Log;

/**
 * 描述：常用日志工具类
 * 作者： Sunny
 * 日期： 2015-09-17 15:06
 * 版本： v2.7.2
 */
public class LogUtils
{

	private LogUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = true;
	private static final String TAG = "c-life";

	public static void i(String msg)
	{
		if (isDebug)
			Log.i(TAG, msg);
	}

	public static void d(String msg)
	{
		if (isDebug)
			Log.d(TAG, msg);
	}

	public static void e(String msg)
	{
		if (isDebug)
			Log.e(TAG, msg);
	}

	public static void v(String msg)
	{
		if (isDebug)
			Log.v(TAG, msg);
	}

	public static void i(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void e(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void v(String tag, String msg)
	{
		if (isDebug)
			Log.i(tag, msg);
	}
}