/*
 * -----------------------------------------------------------------
 * Copyright (C) 2012-2013, by Het, ShenZhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: TimeConsts.java
 * Author: clark
 * Version: 1.0
 * Create: 2013-8-29
 *
 * Changes (from 2013-8-29)
 * -----------------------------------------------------------------
 * 2013-8-29 : 创建 TimeConsts.java (clark);
 * -----------------------------------------------------------------
 */

package com.het.common.constant;

import android.text.format.DateUtils;

/**
 * @ClassName: TimeConsts
 * @Description: 时间相关常量
 * @Author: clark
 * @Create: 2013-8-29
 */

public final class TimeConsts {

    /**
     * 三十秒对应的毫秒数.
     */
    public static final long THIRTY_SECONDS_IN_MILLIS = DateUtils.SECOND_IN_MILLIS * 30;

    /**
     * 两分钟对应的毫秒数.
     */
    public static final long TWO_MINUTES_IN_MILLIS = DateUtils.MINUTE_IN_MILLIS * 2;

    /**
     * 三分钟对应的毫秒数.
     */
    public static final long THREE_MINUTES_IN_MILLIS = DateUtils.MINUTE_IN_MILLIS * 3;

    /**
     * 五分钟对应的毫秒数.
     */
    public static final long FIVE_MINUTES_IN_MILLIS = DateUtils.MINUTE_IN_MILLIS * 5;

    /**
     * 十分钟对应的毫秒数.
     */
    public static final long TEN_MINUTES_IN_MILLIS = DateUtils.MINUTE_IN_MILLIS * 10;

    /**
     * 十五分钟对应的毫秒数.
     */
    public static final long FIFTEEN_MINUTES_IN_MILLIS = DateUtils.MINUTE_IN_MILLIS * 15;

    /**
     * 三十分钟对应的毫秒数.
     */
    public static final long THIRTY_MINUTES_IN_MILLIS = DateUtils.MINUTE_IN_MILLIS * 30;

    /**
     * 两小时对应的毫秒数.
     */
    public static final long TWO_HOURS_IN_MILLIS = DateUtils.HOUR_IN_MILLIS * 2;

    /**
     * 三小时对应的毫秒数.
     */
    public static final long THREE_HOURS_IN_MILLIS = DateUtils.HOUR_IN_MILLIS * 3;

    /**
     * 两天对应的毫秒数.
     */
    public static final long TWO_DAYS_IN_MILLIS = DateUtils.DAY_IN_MILLIS * 2;

    /**
     * 三天对应的毫秒数.
     */
    public static final long THREE_DAYS_IN_MILLIS = DateUtils.DAY_IN_MILLIS * 3;

    /**
     * 一周对应的毫秒数.
     */
    public static final long WEEK_IN_MILLIS = DateUtils.DAY_IN_MILLIS * 7;

    /**
     * 半小时对应的分钟数.
     */
    public static final int HALFHOUR_MINUTES = 30;

    /**
     * 今天.
     */
    public static final String CN_TODAY = "今天";

    /**
     * 昨天.
     */
    public static final String CN_YESTERDAY = "昨天";

    /**
     * 前天.
     */
    public static final String CN_BEFORE_YESTERDAY = "前天";

    /**
     * 周一.
     */
    public static final String CN_MONDAY = "周一";

    /**
     * 周二.
     */
    public static final String CN_TUESDAY = "周二";

    /**
     * 周三.
     */
    public static final String CN_WEDNESDAY = "周三";

    /**
     * 周四.
     */
    public static final String CN_THURSDAY = "周四";

    /**
     * 周五.
     */
    public static final String CN_FRIDAY = "周五";

    /**
     * 周六.
     */
    public static final String CN_SATURDAY = "周六";

    /**
     * 周日.
     */
    public static final String CN_SUNDAY = "周日";

    /**
     * 一月.
     */
    public static final String CN_JANUARY = "1月";

    /**
     * 二月.
     */
    public static final String CN_FEBRUARY = "2月";

    /**
     * 三月.
     */
    public static final String CN_MARCH = "3月";

    /**
     * 四月.
     */
    public static final String CN_APRIL = "4月";

    /**
     * 五月.
     */
    public static final String CN_MAY = "5月";

    /**
     * 六月.
     */
    public static final String CN_JUNE = "6月";

    /**
     * 七月.
     */
    public static final String CN_JULY = "7月";

    /**
     * 八月.
     */
    public static final String CN_AUGUST = "8月";

    /**
     * 九月.
     */
    public static final String CN_SEPTEMBER = "9月";

    /**
     * 十月.
     */
    public static final String CN_OCTOBER = "10月";

    /**
     * 十一月.
     */
    public static final String CN_NOVEMBER = "11月";

    /**
     * 十二月.
     */
    public static final String CN_DECEMBER = "12月";

    /**
     * 更新.
     */
    public static final String CN_UPDATE = "更新";

    /**
     * 上传.
     */
    public static final String CN_UPLOAD = "上传";

    /**
     * eg: 2013-05-25.
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * eg：2013.6.27.
     */
    public static final String YYYYPMPD = "yyyy.M.d";

    /**
     * eg: 15:06.
     */
    public static final String KK_MM = "kk:mm";

    /**
     * eg: 2013-05-25 15:35:20.
     */
    public static final String YYYY_MM_DD_KK_MM_SS = "yyyy-MM-dd kk:mm:ss";

    /**
     * eg: 2013-05-25 15:35:20.
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * eg: 6月19日.
     */
    public static final String CN_M_D = "M月d日";

    /**
     * eg: 2013年6月19日.
     */
    public static final String CN_YYYY_M_D = "yyyy年M月d日";

    /**
     * eg:20-13-05-25-15-35-20
     */
    public static final String YY_MM_DD_DD_HH_MM_SS = "yyyy-MM-dd-hh-mm-ss";

    /**
     * 私有的构造方法.
     */
    private TimeConsts() {
    }
}
