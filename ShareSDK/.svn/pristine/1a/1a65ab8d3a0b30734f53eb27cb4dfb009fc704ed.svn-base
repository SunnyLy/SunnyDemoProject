package com.het.share.utils;


import com.het.share.R;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HetToast extends Toast {
	
	private static final long DROP_DUPLICATE_TOAST_TS = 2 * 1000; // 2s

	private static String sLast = "";

	private static long sLastTs = 0;

	private static Toast mBasicToast = null;
	
	public static enum CommonToastType {
		TOAST_TYPE_NORMAL, // 普通toast-笑脸
		TOAST_TYPE_SUC, // 成功toast-笑脸
		TOAST_TYPE_ACC, // 加速toast-笑脸
		TOAST_TYPE_SMILE, // 微笑toast-笑脸
		TOAST_TYPE_ALARM// 失败or警告toast-哭脸
	};


	public HetToast(Context context) {
		super(context);
	}
	
	 public static Toast makeText(Context context, CharSequence text, int duration) {
	        Toast result = new Toast(context);

	        LayoutInflater inflate = (LayoutInflater)
	                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        View v = inflate.inflate(R.layout.layout_toast, null);
	        TextView tv = (TextView)v.findViewById(R.id.tvToast);
	        tv.setText(text);
	        
	        result.setView(v);
	        result.setGravity(Gravity.BOTTOM, 0, 163);  
	        result.setDuration(duration);

	        return result;
	    }
	 
	 public static void showToast(Context context,String str){
	        showToast(context,CommonToastType.TOAST_TYPE_NORMAL,str);
	    }
	 
	 /**
		 * toast（带动画）-显示时间2S
		 * 
		 * @param context
		 * @param type
		 * @param str
		 */
		public static void showToast(Context context, CommonToastType type,
				String str) {
			long newTs = System.currentTimeMillis();
			if (str != null
					&& (!str.equals(sLast) || newTs < sLastTs || (newTs - sLastTs) > DROP_DUPLICATE_TOAST_TS)) {
				sLast = str;
				sLastTs = newTs;
				if (mBasicToast == null) {
					mBasicToast = new Toast(context);
				}
				View toastView = LayoutInflater.from(context).inflate(
						R.layout.layout_toast, null);
				TextView txt = (TextView) toastView.findViewById(R.id.tvToast);
				txt.setText(str);

				mBasicToast.setView(toastView);
				int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
						60, context.getResources().getDisplayMetrics());
				mBasicToast.setGravity(Gravity.BOTTOM, 0, px);
				mBasicToast.setDuration(Toast.LENGTH_SHORT);// 默认只显示2S
				mBasicToast.show();
			}
		}

}
