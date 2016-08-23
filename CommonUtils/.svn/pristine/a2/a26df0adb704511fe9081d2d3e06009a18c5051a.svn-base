package com.het.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import com.het.common.constant.TimeConsts;
import android.text.format.DateFormat;

public class HetTimeUtils {
	public static Long GetUTCTime() {  
		Calendar cal = Calendar.getInstance(TimeZone.getDefault(),Locale.getDefault());
	    int zoneOffset = cal.get(Calendar.ZONE_OFFSET);  
	    int dstOffset = cal.get(Calendar.DST_OFFSET);  
	    cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		return  cal.getTimeInMillis();
	} 
	 /**
     * 获得标准时区的日期
     * @return UTC日期
     */
    public static CharSequence getUtcDateString()
    {
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MILLISECOND, -TimeZone.getDefault().getRawOffset());
    	return DateFormat.format(TimeConsts.YYYY_MM_DD_HH_MM_SS, cal.getTime().getTime()) ;
    }
    

	/**
	 * 转换时间格式，将字符串转换为距离1970-01-01的毫秒数.
	 * 
	 * @param strDate
	 *            指定时间的字符串
	 * @param strInFmt
	 *            时间字符串的格式
	 * @return 指定时间字符串距离1970-01-01的毫秒数
	 * @throws ParseException
	 *             时间转换异常
	 */
	public static long parseMillis(final String strDate, final String strInFmt)
			throws ParseException {
		if (StringUtils.isNull(strDate)) {
			throw new NullPointerException("参数strDate不能为空");
		} else if (StringUtils.isNull(strInFmt)) {
			throw new NullPointerException("参数strInFmt不能为空");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(strInFmt,
				Locale.getDefault());
		Date date = sdf.parse(strDate);
		return date.getTime();
	}
	
}
