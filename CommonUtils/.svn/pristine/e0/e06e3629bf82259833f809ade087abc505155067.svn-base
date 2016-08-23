package com.het.common.utils;

import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Time;

import com.het.common.constant.TimeConsts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class TimeUtils {

    /**
     * 私有的构造方法.
     */
    private TimeUtils() {
    }

    /****************************** 获取时间 ******************************/

    /**
     * 获取用户所在的时区.<br />
     * eg：830表示8小时30分
     *
     * @return 用户所在的时区
     */
    public static String getTimeZone() {
        TimeZone timeZone = TimeZone.getDefault();
        long lRawOffset = timeZone.getRawOffset();
        long lAbsRawOffset = Math.abs(lRawOffset);
        int iHour = (int) (lAbsRawOffset / 3600000);
        int iMinute = (int) (lAbsRawOffset - iHour * 3600000);

        String strTimeZone = iHour + String.format(Locale.US, "%02d", iMinute);
        if (lRawOffset < 0) {
            if (strTimeZone.length() < 4) {
                strTimeZone = "-0" + strTimeZone;
            } else {
                strTimeZone = "-" + strTimeZone;
            }
        } else {
            if (strTimeZone.length() < 4) {
                strTimeZone = "+0" + strTimeZone;
            } else {
                strTimeZone = "+" + strTimeZone;
            }
        }
        return strTimeZone;
    }

    /**
     * 获取用户所在的时区分钟差.<br />
     * eg：830表示8小时30分
     *
     * @return 用户所在的时区
     */
    public static String getTimeZoneOfMinutes() {
        TimeZone timeZone = TimeZone.getDefault();
        long lRawOffset = timeZone.getRawOffset();
        long lAbsRawOffset = Math.abs(lRawOffset);
        int iMinute = (int) (lAbsRawOffset / 60000);
        String str = String.valueOf(iMinute);
        if (lRawOffset < 0) {
            if (str.length() < 4) {
            	str = "-0" + str;
            } else {
            	str = "-" + str;
            }
        } else {
            if (str.length() < 4) {
            	str = "+0" + str;
            } else {
            	str = "+" + str;
            }
        }
        return str;
    }

    public static String getTimeDifference() {
        StringBuilder builder = new StringBuilder(getTimeZone());
        if (builder.charAt(0) == '+') builder.deleteCharAt(0);
        return String.valueOf(Integer.valueOf(builder.toString()) / 100 * 60 + Integer.valueOf(builder.toString()) % 100);
    }

    /**
     * 根据时间距离1970-01-01的毫秒数获取对应的time对象.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @return 时间距离1970-01-01的毫秒数对应的time对象
     */
    public static Time getTime(final long lMillis) {
        Time time = new Time();
        time.set(lMillis);
        return time;
    }

    /**
     * 获取UTC当前时间距离1970-01-01的毫秒数，UTC当前时间忽略毫秒值.
     *
     * @return UTC当前时间距离1970-01-01的毫秒数.
     */
    public static long getCurUtcMillis() {
        Time time = new Time();
        time.setToNow();
        long lCurMillis = time.toMillis(false);
        long lOffset = time.gmtoff * DateUtils.SECOND_IN_MILLIS;
        long lUtcMillis = lCurMillis - lOffset;
        return lUtcMillis;
    }

    /**
     * 获取UTC当前时间的描述.
     *
     * @param strInFmt 时间格式
     * @return UTC当前时间的描述
     */
    public static String getCurUtcString(final String strInFmt) {
        if (StringUtils.isNull(strInFmt)) {
            throw new NullPointerException("参数strInFmt不能为空");
        }
        return TimeUtils.format(TimeUtils.getCurUtcMillis(), strInFmt);
    }

    /**
     * 获取UTC当前时间的描述，格式为yyyy-MM-dd HH:mm:ss.
     *
     * @return UTC当前时间的描述
     */
    public static String getCurUtcDateTimeString() {
        return TimeUtils.getCurUtcString(TimeConsts.YYYY_MM_DD_KK_MM_SS);
    }

    /**
     * 获取UTC当前时间的描述，格式为yyyy-MM-dd.
     *
     * @return UTC当前时间的描述
     */
    public static String getCurUtcDateString() {
        return TimeUtils.getCurUtcString(TimeConsts.YYYY_MM_DD);
    }

    /**
     * 获取用户时区当前时间的日描述.
     *
     * @return 用户时区当前时间的日描述
     */
    public static String getCurUserZoneDay() {
        Time time = new Time();
        time.setToNow();
        return String.valueOf(time.monthDay);
    }

    /**
     * 获取用户时区当前时间的描述，格式为yyyy-MM-dd.
     *
     * @return 用户时区当前时间的描述
     */
    public static String getCurUserZoneDateString() {
        return TimeUtils.format(System.currentTimeMillis(),
                TimeConsts.YYYY_MM_DD);
    }


    /**
     * 获取用户时区当前时间的描述，格式为yyyy-MM-dd HH:mm:ss.
     *
     * @return 用户时区当前时间的描述
     */
    public static String getCurUserZoneDateTimeString() {
        return TimeUtils.format(System.currentTimeMillis(),
                TimeConsts.YYYY_MM_DD_KK_MM_SS);
    }

    /**
     * 将UTC-0时区时间字符串转换成用户时区时间距离1970-01-01的毫秒数.
     *
     * @param strUtcTime UTC-0时区的时间字符串
     * @param strInFmt   时间格式
     * @return 用户时区时间距离1970-01-01的毫秒数.
     * @throws ParseException 时间转换异常
     */
    public static long getUserZoneMillis(final String strUtcTime,
                                         final String strInFmt) throws ParseException {
        if (StringUtils.isNull(strUtcTime)) {
            throw new NullPointerException("参数strUtcTime不能为空");
        } else if (StringUtils.isNull(strInFmt)) {
            throw new NullPointerException("参数strInFmt不能为空");
        }
        long lUtcMillis = TimeUtils.parseMillis(strUtcTime, strInFmt);
        Time time = new Time();
        time.setToNow();
        long lOffset = time.gmtoff * DateUtils.SECOND_IN_MILLIS;
        long lUserZoneMillis = lUtcMillis + lOffset;
        return lUserZoneMillis;
    }

    /**
     * 将UTC-0时区时间字符串转换成用户时区时间的描述.
     *
     * @param strUtcTime UTC-0时区的时间
     * @param strInFmt   时间的输入格式
     * @param strOutFmt  时间的输出格式，若为null则输出格式与输入格式相同
     * @return 用户时区的时间描述.
     * @throws ParseException 时间转换异常
     */
    public static String getUserZoneString(final String strUtcTime,
                                           final String strInFmt, final String strOutFmt)
            throws ParseException {
        if (StringUtils.isNull(strUtcTime)) {
            throw new NullPointerException("参数strDate不能为空");
        } else if (StringUtils.isNull(strInFmt)) {
            throw new NullPointerException("参数strInFmt不能为空");
        }
        long lUserMillis = TimeUtils.getUserZoneMillis(strUtcTime, strInFmt);
        String strFmt = strInFmt;
        if (!StringUtils.isNull(strOutFmt)) {
            strFmt = strOutFmt;
        }
        return TimeUtils.format(lUserMillis, strFmt);
    }

    /**
     * 将UTC-0时区时间字符串转换成用户时区时间的描述，格式为yyyy-MM-dd HH:mm:ss.
     *
     * @param strUtcDateTime UTC-0时区的时间，格式为yyyy-MM-dd HH:mm:ss
     * @return 用户时区的时间描述，格式为yyyy-MM-dd HH:mm:ss
     * @throws ParseException 时间转换异常
     */
    public static String getUserZoneDateTimeString(final String strUtcDateTime)
            throws ParseException {
        if (StringUtils.isNull(strUtcDateTime)) {
            throw new NullPointerException("参数strUtcDateTime不能为空");
        }
        return TimeUtils.getUserZoneString(strUtcDateTime,
                TimeConsts.YYYY_MM_DD_KK_MM_SS, null);
    }

    /**
     * 将UTC-0时区时间字符串转换成用户时区时间的描述，格式为yyyy-MM-dd.
     *
     * @param strUtcDate UTC-0时区的时间，格式为yyyy-MM-dd
     * @return 用户时区的时间描述，格式为yyyy-MM-dd
     * @throws ParseException 时间转换异常
     */
    public static String getUserZoneDateString(final String strUtcDate)
            throws ParseException {

        if (StringUtils.isNull(strUtcDate)) {
            throw new NullPointerException("参数strDateTime不能为空");
        }
        return TimeUtils.getUserZoneString(strUtcDate, TimeConsts.YYYY_MM_DD,
                null);
    }

    /**
     * 将用户时区时间字符串转换成UTC-0时区时间距离1970-01-01的毫秒数.
     *
     * @param strUserZoneTime 用户时区时间字符串
     * @param strInFmt        时间格式
     * @return UTC-0时区时间距离1970-01-01的毫秒数.
     * @throws ParseException 时间转换异常
     */
    public static long getUtcMillis(final String strUserZoneTime,
                                    final String strInFmt) throws ParseException {
        if (StringUtils.isNull(strUserZoneTime)) {
            throw new NullPointerException("参数strUserZoneTime不能为空");
        } else if (StringUtils.isNull(strInFmt)) {
            throw new NullPointerException("参数strInFmt不能为空");
        }
        long lUserZoneMillis = TimeUtils.parseMillis(strUserZoneTime, strInFmt);
        Time time = new Time();
        time.setToNow();
        long lOffset = time.gmtoff * DateUtils.SECOND_IN_MILLIS;
        long lUtcMillis = lUserZoneMillis - lOffset;
        return lUtcMillis;
    }

    /**
     * 将用户时区时间字符串转换成UTC-0时区时间的描述.
     *
     * @param strUserZoneTime 用户时区的时间
     * @param strInFmt        时间的输入格式
     * @param strOutFmt       时间的输出格式，若为null则输出格式与输入格式相同
     * @return UTC-0时区的时间描述.
     * @throws ParseException 时间转换异常
     */
    public static String getUtcString(final String strUserZoneTime,
                                      final String strInFmt, final String strOutFmt)
            throws ParseException {
        if (StringUtils.isNull(strUserZoneTime)) {
            throw new NullPointerException("参数strUserZoneTime不能为空");
        } else if (StringUtils.isNull(strInFmt)) {
            throw new NullPointerException("参数strInFmt不能为空");
        }
        long lUtcMillis = TimeUtils.getUtcMillis(strUserZoneTime, strInFmt);
        String strFmt = strInFmt;
        if (!StringUtils.isNull(strOutFmt)) {
            strFmt = strOutFmt;
        }
        return TimeUtils.format(lUtcMillis, strFmt);
    }

    /**
     * 将用户时区时间字符串转换成UTC-0时区时间的描述，格式为yyyy-MM-dd HH:mm:ss.
     *
     * @param strUserZoneDateTime 用户时区的时间，格式为yyyy-MM-dd HH:mm:ss
     * @return UTC-0时区的时间描述，格式为yyyy-MM-dd HH:mm:ss
     * @throws ParseException 时间转换异常
     */
    public static String getUtcDateTimeString(final String strUserZoneDateTime)
            throws ParseException {
        if (StringUtils.isNull(strUserZoneDateTime)) {
            throw new NullPointerException("参数strUserZoneDateTime不能为空");
        }
        return TimeUtils.getUtcString(strUserZoneDateTime,
                TimeConsts.YYYY_MM_DD_KK_MM_SS, null);
    }

    /**
     * 将用户时区时间字符串转换成UTC-0时区时间的描述，格式为yyyy-MM-dd.
     *
     * @param strUserZoneDate 用户时区的时间，格式为yyyy-MM-dd
     * @return UTC-0时区的时间描述，格式为yyyy-MM-dd
     * @throws ParseException 时间转换异常
     */
    public static String getUtcDateString(final String strUserZoneDate)
            throws ParseException {
        if (StringUtils.isNull(strUserZoneDate)) {
            throw new NullPointerException("参数strUserZoneDate不能为空");
        }
        return TimeUtils.getUtcString(strUserZoneDate, TimeConsts.YYYY_MM_DD,
                null);
    }

    /**
     * 获取指定时间所在周的第一天时间.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @return 指定时间所在周的第一天时间
     */
    public static String getFirstDayInWeek(final long lMillis) {
        Calendar nativeCalendar = TimeUtils.parseCalendar(lMillis);
        nativeCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return TimeUtils.format(nativeCalendar.getTimeInMillis(),
                TimeConsts.YYYY_MM_DD_KK_MM_SS);
    }

    /**
     * 获取指定时间所在周的第一天时间.
     *
     * @param strDateTime 指定时间，格式为yyyy-MM-dd HH:mm:ss
     * @return 指定时间所在周的第一天时间
     * @throws ParseException 时间转换异常
     */
    public static String getFirstDayInWeek(final String strDateTime)
            throws ParseException {
        long lMillis = TimeUtils.parseMillis(strDateTime,
                TimeConsts.YYYY_MM_DD_HH_MM_SS);
        return TimeUtils.getFirstDayInWeek(lMillis);
    }

    /**
     * 获取指定时间上一周的第一天时间.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @return 指定时间上一周的第一天时间
     */
    public static String getFirstDayLastWeek(final long lMillis) {
        return TimeUtils.getFirstDayInWeek(TimeUtils.modWeek(lMillis, -1));
    }

    /**
     * 获取指定时间上一周的第一天时间.
     *
     * @param strDateTime 指定时间，格式为yyyy-MM-dd HH:mm:ss
     * @return 指定时间上一周的第一天时间
     * @throws ParseException 时间转换异常
     */
    public static String getFirstDayLastWeek(final String strDateTime)
            throws ParseException {
        long lMillis = TimeUtils.parseMillis(strDateTime,
                TimeConsts.YYYY_MM_DD_HH_MM_SS);
        return TimeUtils.getFirstDayLastWeek(lMillis);
    }

    /**
     * 获取指定时间下一周的第一天时间.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @return 指定时间下一周的第一天时间
     */
    public static String getFirstDayNextWeek(final long lMillis) {
        return TimeUtils.getFirstDayInWeek(TimeUtils.modWeek(lMillis, 1));
    }

    /**
     * 获取指定时间下一周的第一天时间.
     *
     * @param strDateTime 指定时间，格式为yyyy-MM-dd HH:mm:ss
     * @return 指定时间下一周的第一天时间
     * @throws ParseException 时间转换异常
     */
    public static String getFirstDayNextWeek(final String strDateTime)
            throws ParseException {
        long lMillis = TimeUtils.parseMillis(strDateTime,
                TimeConsts.YYYY_MM_DD_HH_MM_SS);
        return TimeUtils.getFirstDayNextWeek(lMillis);
    }

    /**
     * 获取指定时间所在月的第一天时间.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @return 指定时间所在月的第一天时间
     */
    public static String getFirstDayInMonth(final long lMillis) {
        Calendar nativeCalendar = TimeUtils.parseCalendar(lMillis);
        nativeCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return TimeUtils.format(nativeCalendar.getTimeInMillis(),
                TimeConsts.YYYY_MM_DD_KK_MM_SS);
    }

    /**
     * 获取指定时间所在月的第一天时间.
     *
     * @param strDateTime 指定时间，格式为yyyy-MM-dd HH:mm:ss
     * @return 指定时间所在月的第一天时间
     * @throws ParseException 时间转换异常
     */
    public static String getFirstDayInMonth(final String strDateTime)
            throws ParseException {
        long lMillis = TimeUtils.parseMillis(strDateTime,
                TimeConsts.YYYY_MM_DD_HH_MM_SS);
        return TimeUtils.getFirstDayInMonth(lMillis);
    }

    /**
     * 获取指定时间上一月的第一天时间距离1970-01-01的毫秒数.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @return 指定时间所在月的第一天时间
     */
    public static String getFirstDayLastMonth(final long lMillis) {
        return TimeUtils.getFirstDayInMonth(TimeUtils.modMonth(lMillis, -1));
    }

    /**
     * 获取指定时间上一月的第一天时间.
     *
     * @param strDateTime 指定时间，格式为yyyy-MM-dd HH:mm:ss
     * @return 指定时间上一月的第一天时间
     * @throws ParseException 时间转换异常
     */
    public static String getFirstDayLastMonth(final String strDateTime)
            throws ParseException {
        long lMillis = TimeUtils.parseMillis(strDateTime,
                TimeConsts.YYYY_MM_DD_HH_MM_SS);
        return TimeUtils.getFirstDayLastMonth(lMillis);
    }

    /****************************** 转换时间 ******************************/

    /**
     * 转换时间格式，将字符串转换为距离1970-01-01的毫秒数.
     *
     * @param strDate  指定时间的字符串
     * @param strInFmt 时间字符串的格式
     * @return 指定时间字符串距离1970-01-01的毫秒数
     * @throws ParseException 时间转换异常
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

    /**
     * 转换时间格式，把毫秒值转换成Calendar对象.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @return 指定时间对应的Calendar对象
     */
    public static Calendar parseCalendar(final long lMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(lMillis);
        return calendar;
    }

    /****************************** 调整时间 ******************************/

    /**
     * 重置时间中的时、分、秒、毫秒属性.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @return 时、分、秒、毫秒属性置0的毫秒数
     */
    public static long remainMillis(final long lMillis) {
        Calendar nativeCalendar = Calendar.getInstance();
        nativeCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        nativeCalendar.setTimeInMillis(lMillis);
        nativeCalendar.set(Calendar.HOUR_OF_DAY, 0);
        nativeCalendar.set(Calendar.MINUTE, 0);
        nativeCalendar.set(Calendar.SECOND, 0);
        nativeCalendar.set(Calendar.MILLISECOND, 0);
        return nativeCalendar.getTimeInMillis();
    }

    /**
     * 按毫秒调整时间.
     *
     * @param lMillis    距离1970-01-01的毫秒数
     * @param lRawOffset 调整的毫秒值
     * @return 调整后的时间距离1970-01-01的毫秒数
     */
    public static long modMillis(final long lMillis, final long lRawOffset) {
        return lMillis + lRawOffset;
    }

    /**
     * 按秒调整时间.
     *
     * @param lMillis  距离1970-01-01的毫秒数
     * @param iSeconds 调整的秒数
     * @return 调整后的时间距离1970-01-01的毫秒数
     */
    public static long modSecond(final long lMillis, final int iSeconds) {
        Time time = new Time();
        time.set(lMillis);
        time.second += iSeconds;
        return time.toMillis(false);
    }

    /**
     * 按分钟调整时间.
     *
     * @param lMillis  距离1970-01-01的毫秒数
     * @param iMinutes 调整的分钟数
     * @return 调整后的时间距离1970-01-01的毫秒数
     */
    public static long modMinute(final long lMillis, final int iMinutes) {
        Time time = new Time();
        time.set(lMillis);
        time.minute += iMinutes;
        return time.toMillis(false);
    }

    /**
     * 按日调整时间.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @param iDays   调整的天数
     * @return 调整后的时间距离1970-01-01的毫秒数
     */
    public static long modDay(final long lMillis, final int iDays) {
        Time time = new Time();
        time.set(lMillis);
        time.monthDay += iDays;
        return time.toMillis(false);
    }

    /**
     * 按周调整时间.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @param iWeeks  调整的周数
     * @return 调整后的时间距离1970-01-01的毫秒数
     */
    public static long modWeek(final long lMillis, final int iWeeks) {
        Time time = new Time();
        time.set(lMillis);
        time.monthDay += (iWeeks * 7);
        return time.toMillis(false);
    }

    /**
     * 按月调整时间.
     *
     * @param lMillis 距离1970-01-01的毫秒数
     * @param iMonths 调整的月数
     * @return 调整后的时间距离1970-01-01的毫秒数
     */
    public static long modMonth(final long lMillis, final int iMonths) {
        Time time = new Time();
        time.set(lMillis);
        time.month += iMonths;
        return time.toMillis(false);
    }

    /****************************** 格式化时间 *****************************/

    /**
     * 格式化时间.
     *
     * @param lMillis  时间参数
     * @param strInFmt 时间格式
     * @return 对应的时间字符串
     */
    public static String format(final long lMillis, final String strInFmt) {
        if (StringUtils.isNull(strInFmt)) {
            throw new NullPointerException("参数strInFmt不能为空");
        }
        return (String) DateFormat.format(strInFmt, lMillis);
    }

    /**
     * 将时间字符串格式化为其他格式的字符串.
     *
     * @param strIn     输入时间字符串
     * @param strInFmt  输入时间字符串格式
     * @param strOutFmt 输出时间字符串格式
     * @return 格式化后的时间字符串
     * @throws ParseException 时间转换异常
     */
    public static String format(final String strIn, final String strInFmt,
                                final String strOutFmt) throws ParseException {
        if (StringUtils.isNull(strIn)) {
            throw new NullPointerException("参数strIn不能为空");
        } else if (StringUtils.isNull(strInFmt)) {
            throw new NullPointerException("参数strInFmt不能为空");
        } else if (StringUtils.isNull(strOutFmt)) {
            throw new NullPointerException("参数strOutFmt不能为空");
        }
        long lMillis = TimeUtils.parseMillis(strIn, strInFmt);
        return TimeUtils.format(lMillis, strOutFmt);
    }

    /****************************** 比较时间 *****************************/

    /**
     * 判断主动比较的时间参数是否晚于被动比较的时间参数.
     *
     * @param lPsv 主动比较的时间距离1970-01-01的毫秒数
     * @param lNgv 被动比较的时间距离1970-01-01的毫秒数
     * @return 若主动比较的时间参数早于被动比较的时间参数返回false, 否则返回true
     */
    public static boolean isLater(final long lPsv, final long lNgv) {
        long lResult = lPsv - lNgv;
        if (lResult <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 判断主动比较的时间参数是否晚于被动比较的时间参数.
     *
     * @param strPsv    主动比较的时间
     * @param strNgv    被动比较的时间
     * @param strFormat 时间格式
     * @return 若主动比较的时间参数早于被动比较的时间参数返回false, 否则返回true
     * @throws ParseException 时间转换异常
     */
    public static boolean isLater(final String strPsv, final String strNgv,
                                  final String strFormat) throws ParseException {
        if (StringUtils.isNull(strPsv)) {
            throw new NullPointerException("参数strPsv不能为空");
        } else if (StringUtils.isNull(strNgv)) {
            throw new NullPointerException("参数strNgv不能为空");
        } else if (StringUtils.isNull(strFormat)) {
            throw new NullPointerException("参数strFormat不能为空");
        }
        long lPsv = TimeUtils.parseMillis(strPsv, strFormat);
        long lNgv = TimeUtils.parseMillis(strNgv, strFormat);
        return TimeUtils.isLater(lPsv, lNgv);
    }

    /****************************** 计算时间差 *****************************/

    /**
     * 计算起始时间与结束时间之间相差的月份.
     *
     * @param lFrom 起始时间距离1970-01-01的毫秒数
     * @param lTo   结束时间距离1970-01-01的毫秒数
     * @return 起始时间与结束时间之间相差的月份
     */
    public static int calMonths(final long lFrom, final long lTo) {
        Time fromTime = TimeUtils.getTime(lFrom);
        Time toTime = TimeUtils.getTime(lTo);
        int iFromYear = fromTime.year;
        int iToYear = toTime.year;
        int iFromMonth = fromTime.month;
        int iToMonth = toTime.month;
        return (iToYear - iFromYear) * 12 + (iToMonth - iFromMonth);
    }

    /**
     * 计算起始时间与结束时间之间相差的分钟.
     *
     * @param lFrom 起始时间距离1970-01-01的毫秒数
     * @param lTo   结束时间距离1970-01-01的毫秒数
     * @return 起始时间与结束时间之间相差的分钟
     */
    public static int calMinutes(final long lFrom, final long lTo) {
        long lMillis = lTo - lFrom;
        int iMinutes = (int) (lMillis / DateUtils.MINUTE_IN_MILLIS);
        return iMinutes;
    }

    /****************************** 时间显示效果 *****************************/

    /**
     * 获取星期的中文描述，例如周一、周日.
     *
     * @param iDayOfWeek 一周的第几天，范围从0到6
     * @return 星期的中文描述
     */
    public static String getDayOfWeekStringCN(final int iDayOfWeek) {
        String strWhatDay = null;
        switch (iDayOfWeek + 1) {
            case Calendar.SUNDAY:
                strWhatDay = TimeConsts.CN_SUNDAY;
                break;
            case Calendar.MONDAY:
                strWhatDay = TimeConsts.CN_MONDAY;
                break;
            case Calendar.TUESDAY:
                strWhatDay = TimeConsts.CN_TUESDAY;
                break;
            case Calendar.WEDNESDAY:
                strWhatDay = TimeConsts.CN_WEDNESDAY;
                break;
            case Calendar.THURSDAY:
                strWhatDay = TimeConsts.CN_THURSDAY;
                break;
            case Calendar.FRIDAY:
                strWhatDay = TimeConsts.CN_FRIDAY;
                break;
            case Calendar.SATURDAY:
                strWhatDay = TimeConsts.CN_SATURDAY;
                break;
            default:
                strWhatDay = TimeConsts.CN_MONDAY;
                break;
        }
        return strWhatDay;
    }

    /**
     * 获取月份的中文描述.
     *
     * @param iMonth 一年的第几月，范围从0到11
     * @return 月份的中文描述
     */
    public static String getMonthStringCN(final int iMonth) {
        String strWhatMonth = null;
        switch (iMonth) {
            case Calendar.JANUARY:
                strWhatMonth = TimeConsts.CN_JANUARY;
                break;
            case Calendar.FEBRUARY:
                strWhatMonth = TimeConsts.CN_FEBRUARY;
                break;
            case Calendar.MARCH:
                strWhatMonth = TimeConsts.CN_MARCH;
                break;
            case Calendar.APRIL:
                strWhatMonth = TimeConsts.CN_APRIL;
                break;
            case Calendar.MAY:
                strWhatMonth = TimeConsts.CN_MAY;
                break;
            case Calendar.JUNE:
                strWhatMonth = TimeConsts.CN_JUNE;
                break;
            case Calendar.JULY:
                strWhatMonth = TimeConsts.CN_JULY;
                break;
            case Calendar.AUGUST:
                strWhatMonth = TimeConsts.CN_AUGUST;
                break;
            case Calendar.SEPTEMBER:
                strWhatMonth = TimeConsts.CN_SEPTEMBER;
                break;
            case Calendar.OCTOBER:
                strWhatMonth = TimeConsts.CN_OCTOBER;
                break;
            case Calendar.NOVEMBER:
                strWhatMonth = TimeConsts.CN_NOVEMBER;
                break;
            case Calendar.DECEMBER:
                strWhatMonth = TimeConsts.CN_DECEMBER;
                break;
            default:
                strWhatMonth = TimeConsts.CN_JANUARY;
                break;
        }
        return strWhatMonth;
    }

    /**
     * 当天日期与参数日期进行比较，根据时间差返回对应的字符串.
     *
     * @param strDate  比较的日期
     * @param strInFmt 日期的格式
     * @return 时间差为0返回“今天”，时间差(0,1]天返回“昨天“，时间差(1,2]天返回“前天”，否则返回星期几
     * @throws ParseException 日期格式转换异常
     */
    public static String getComparedDateStringCN(final String strDate,
                                                 final String strInFmt) throws ParseException {
        long lTodayMillis = TimeUtils.remainMillis(System.currentTimeMillis()); // 当天零点时间距离1970-01-01的毫秒数
        long lComparedMillis = TimeUtils.parseMillis(strDate, strInFmt); // 需要比较的日期距离1970-01-01的毫秒数
        long lOffset = lTodayMillis - lComparedMillis; // 时间差
        if (lOffset == 0) {
            return TimeConsts.CN_TODAY; // 今天
        } else if (lOffset > 0 && lOffset <= DateUtils.DAY_IN_MILLIS) {
            return TimeConsts.CN_YESTERDAY; // 昨天
        } else if (lOffset > DateUtils.DAY_IN_MILLIS
                && lOffset <= TimeConsts.TWO_DAYS_IN_MILLIS) {
            return TimeConsts.CN_BEFORE_YESTERDAY; // 前天
        } else {
            Time time = new Time();
            time.set(lComparedMillis);
            int iWhatDay = time.weekDay;
            return TimeUtils.getDayOfWeekStringCN(iWhatDay); // 星期几
        }
    }

    /**
     * 获取日期的中文描述，日期为一号时返回月份名，否则返回日数.
     *
     * @param strDate  日期参数
     * @param strInFmt 日期格式
     * @return 日期的中文描述
     * @throws ParseException 日期格式转换异常
     */
    public static String getDateStringForDayCN(final String strDate,
                                               final String strInFmt) throws ParseException {
        long lMillis = TimeUtils.parseMillis(strDate, strInFmt);
        Time time = new Time();
        time.set(lMillis);
        int iDay = time.monthDay;
        if (iDay == 1) {
            /* 日期为一号返回月份信息 */
            int iMonth = time.month;
            return TimeUtils.getMonthStringCN(iMonth);
        } else {
                  /* 日期非一号返回日信息 */
            return String.valueOf(iDay);
        }
    }

    /**
     * 获取日期的中文描述，日期所在周为当前月份的第一周时返回月份名，否则返回日数.
     *
     * @param strDate  日期参数
     * @param strInFmt 日期格式
     * @return 日期的中文描述
     * @throws ParseException 日期格式转换异常
     */
    public static String getDateStringForWeekCN(final String strDate,
                                                final String strInFmt) throws ParseException {
        long lMillis = TimeUtils.parseMillis(strDate, strInFmt);
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(lMillis);
        int iWeek = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        if (iWeek == 1) {
            /* 所在周为当前月份第一周时返回月份名 */
            int iMonth = calendar.get(Calendar.MONTH);
            return TimeUtils.getMonthStringCN(iMonth);
        } else {
            /* 日期非一号返回日信息 */
            int iDay = calendar.get(Calendar.DAY_OF_MONTH);
            return String.valueOf(iDay);
        }
    }

//    /**
//     * 获取指定时间相对与当前时间的比较描述.
//     *
//     * @param iDeviceSubType  设备子类型
//     * @param lComparedMillis 待比较的指定时间
//     * @return 指定时间相对与当前时间的比较描述
//     */
//    public static String getRelativeDateTimeStringCN(final int iDeviceSubType,
//                                                     final long lComparedMillis) {
//        long lCurMillis = System.currentTimeMillis(); // 当前时间距离1970-01-01的毫秒数
//        long lOffset = lCurMillis - lComparedMillis; // 时间差，单位毫秒
//        if (TimeUtils.remainMillis(lComparedMillis) == TimeUtils
//                .remainMillis(lCurMillis)) {
//            String strWord = TimeConsts.CN_UPLOAD;
//            if (iDeviceSubType == UserDeviceRelationModel.PEDOMETER_SUB_TYPE_WIFI) {
//                strWord = TimeConsts.CN_UPDATE;
//            } else if (iDeviceSubType == UserDeviceRelationModel.PEDOMETER_SUB_TYPE_2A
//                    || iDeviceSubType == UserDeviceRelationModel.PEDOMETER_SUB_TYPE_V2
//                    || iDeviceSubType == UserDeviceRelationModel.PEDOMETER_SUB_TYPE_WRISTBAND) {
//                strWord = TimeConsts.CN_UPLOAD;
//            }
//            if (lOffset < 0) {
//				/* 服务器的更新时间比手机当前时间晚 */
//                return "刚刚" + strWord;
//            } else if (lOffset <= TimeConsts.THIRTY_SECONDS_IN_MILLIS) {
//				/* [0, 30]秒内更新 */
//                return "刚刚" + strWord;
//            } else if (lOffset <= DateUtils.MINUTE_IN_MILLIS) {
//				/* (30, 60]秒内更新 */
//                return "30秒前" + strWord;
//            } else if (lOffset <= TimeConsts.TWO_MINUTES_IN_MILLIS) {
//				/* (1, 2]分钟内更新 */
//                return "1分钟前" + strWord;
//            } else if (lOffset <= TimeConsts.THREE_MINUTES_IN_MILLIS) {
//				/* (2, 3]分钟内更新 */
//                return "2分钟前" + strWord;
//            } else if (lOffset <= TimeConsts.FIVE_MINUTES_IN_MILLIS) {
//				/* (3, 5]分钟内更新 */
//                return "3分钟前" + strWord;
//            } else if (lOffset <= TimeConsts.TEN_MINUTES_IN_MILLIS) {
//				/* (5, 10]分钟内更新 */
//                return "5分钟前" + strWord;
//            } else if (lOffset <= TimeConsts.FIFTEEN_MINUTES_IN_MILLIS) {
//				/* (10, 15]分钟内更新 */
//                return "10分钟前" + strWord;
//            } else if (lOffset <= TimeConsts.THIRTY_MINUTES_IN_MILLIS) {
//				/* (15, 30]分钟内更新 */
//                return "15分钟前" + strWord;
//            } else if (lOffset <= DateUtils.HOUR_IN_MILLIS) {
//				/* (30, 60]分钟内更新 */
//                return "半小时前" + strWord;
//            } else if (lOffset <= TimeConsts.TWO_HOURS_IN_MILLIS) {
//				/* (1, 2]小时内更新 */
//                return "1小时前" + strWord;
//            } else if (lOffset <= TimeConsts.THREE_HOURS_IN_MILLIS) {
//				/* (2, 3]小时内内更新 */
//                return "2小时前" + strWord;
//            } else if (lOffset <= DateUtils.DAY_IN_MILLIS) {
//				/* (0, 1]天内更新 */
//                return "数小时前" + strWord;
//            }
//        } else {
//            if (iDeviceSubType == UserDeviceRelationModel.PEDOMETER_SUB_TYPE_WIFI) {
//                return "今日未刷新";
//            } else if (iDeviceSubType == UserDeviceRelationModel.PEDOMETER_SUB_TYPE_2A
//                    || iDeviceSubType == UserDeviceRelationModel.PEDOMETER_SUB_TYPE_V2
//                    || iDeviceSubType == UserDeviceRelationModel.PEDOMETER_SUB_TYPE_WRISTBAND) {
//                return "今日未上传";
//            }
//        }
//        return "";
//    }

    /**
     * 判断是否是周末
     *
     * @return
     */
    public static boolean isWeekend(Calendar cal) {
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 6 || week == 0) {//0代表周日，6代表周六
            return true;
        }
        return false;
    }

    /**
     * 90分钟 转换为  1:30
     *
     * @param leftTime
     * @return
     */
    public static String dealLeftTime(int leftTime) {
        String res = "";
        int hour = leftTime / 60;
        int min = leftTime % 60;
        if (hour < 10) {
            res = "0" + hour;
        } else {
            res = "" + hour;
        }
        if (min < 10) {
            res = res + ":0" + min;
        } else {
            res = res + ":" + min;
        }
        return res;
    }


    /**
     * 根据时间戳获取周几
     *
     * @param Millis
     * @return
     */
    public static String getWeekName(long Millis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(Millis));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        return "周" + dayOfWeek;
    }


    /**
     * 根据时间戳获取本周第几天
     *
     * @param Millis
     * @return
     */
    public static int getWeekIndex(long Millis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(Millis));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek;
    }


}
