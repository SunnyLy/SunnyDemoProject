/*
 * -----------------------------------------------------------------
 * Copyright (C) 2012-2013, by Het, ShenZhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: StringUtils.java
 * Author: clark
 * Version: 1.0
 * Create: 2013-6-29
 *
 * Changes (from 2013-6-29)
 * -----------------------------------------------------------------
 * 2013-8-16 : 创建 StringUtils.java (clark);
 * -----------------------------------------------------------------
 */

package com.het.common.utils;

import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: StringUtils
 * @Description: 字符串�?用类
 * @Author: clark
 * @Create: 2013-8-16
 */
public final class StringUtils {

    /**
     * 私有的构造方�?
     */
    private StringUtils() {
    }

    /**
     * Mac地址转换
     *
     * @param resBytes
     * @return
     */
    public static String byteToMac(byte[] resBytes) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < resBytes.length; i++) {
            String hex = Integer.toHexString(resBytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            buffer.append(hex.toUpperCase());
        }
        return buffer.toString();
    }


    public static String toHexString(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                s = "0" + s;
            }
            buffer.append(s + " ");
        }
        return buffer.toString();
    }

    public static String[] getDeviceType(String str) {
        String[] type = new String[]{"-1", "-1"};
        if (str == null)
            return type;
        if (str.length() >= 3) {
            String sub = str.substring(str.length() - 3, str.length());
            if (sub.contains("-")) {
                type = sub.split("-");
                if (!isNum(type[0])) {
                    type[0] = "-1";
                }
                if (!isNum(type[1])) {
                    type[1] = "-1";
                }
            } else {
                return null;
            }
        }
        return type;
    }

    public static String[] getDeviceType1(String str) {
        String[] type = new String[]{"-1", "-1"};
        if (isNull(str))
            return type;
        if (str.contains("-")) {
            String[] tmp = str.split("-");
            int pos1 = tmp.length - 2;
            if (pos1 >= 0) {
                if (isNum(tmp[pos1])) {
                    type[0] = tmp[pos1];
                }
            }
            int pos2 = tmp.length - 1;
            if (pos2 > 0) {
                if (isNum(tmp[pos2])) {
                    type[1] = tmp[pos2];
                }
            }
        } else {
            return null;
        }
        return type;
    }

    /**
     * 字节数组转为16进制字符串
     *
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    public static String byteArrayToHexString(byte[] bytes) {
        @SuppressWarnings("resource")
        Formatter fmt = new Formatter(new StringBuilder(bytes.length * 2));
        for (byte b : bytes) {
            fmt.format("%02x", b);
        }
        return fmt.toString();
    }

    /**
     * 判断字符串是否为空或空字�?
     *
     * @param strSource 源字符串
     * @return true表示为空，false表示不为�?
     */
    public static boolean isNull(final String strSource) {
        return strSource == null || "".equals(strSource.trim());
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 判断参数是否为数�?
     *
     * @param strNum 待判断的数字参数
     * @return true表示参数为数字，false表示参数非数�?
     */
    public static boolean isNum(final String strNum) {
        return strNum.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * 判断参数是否为手机号.
     *
     * @param strPhoneNum 待判断的手机号参�?
     * @return true表示参数为手机号，false表示不是手机�?
     */
    public static boolean isPhoneNum(final String strPhoneNum) {
        return Pattern.matches(RegularExpressions.PHONE_FORMAT, strPhoneNum);
    }

    /**
     * 判断�?��字符是Ascill字符还是其它字符(如汉，日，韩文字�?.
     *
     * @param c �?��判断的字�?
     * @return true表示Ascill字符，false表示非Ascill字符
     */
    public static boolean isLetter(final char c) {
        int k = 0xFF;
        if (c / k == 0) {
            return true;
        }
        return false;
    }

    /**
     * 计算字符的长�? Ascii字符算一个长�? 非Ascii字符算两个长�?
     *
     * @param c �?��计算的字�?
     * @return 字符的长�?
     */
    public static int getCharLength(final char c) {
        if (StringUtils.isLetter(c)) {
            return 1;
        }
        return 2;
    }

    /**
     * 获取字符串的长度, Ascii字符算一个长�? 非Ascii字符算两个长�?
     *
     * @param strSource �?��计算长度的字符串
     * @return 字符串的长度
     */
    public static int getStringLength(final String strSource) {
        int iSrcLen = 0;
        char[] arrChars = strSource.toCharArray();
        for (char arrChar : arrChars) {
            iSrcLen += StringUtils.getCharLength(arrChar);
        }
        return iSrcLen;
    }

    /**
     * 截取字符串，若参数strSuffix不为null，则加上该参数作为后�?
     *
     * @param strSource 原始字符�?
     * @param iSubLen   截取的长�?
     * @param strSuffix 后缀字符串，null表示不需要后�?
     * @return 截取后的字符�?
     */
    public static String sub(final String strSource, final int iSubLen,
                             final String strSuffix) {
        if (StringUtils.isNull(strSource)) {
            return strSource;
        }
        String strFilter = strSource.trim(); // 过滤首尾空字�?
        int iLength = StringUtils.getStringLength(strFilter); // 字符的长�?
        if (iLength <= iSubLen) {
            return strFilter; // 字符长度小于待截取的长度
        }
        int iNum = iSubLen; // 可截取字符的数量
        int iSubIndex = 0; // 截取位置的游�?
        char[] arrChars = strFilter.toCharArray();
        int iArrLength = arrChars.length;
        char c = arrChars[iSubIndex];
        StringBuffer sbContent = new StringBuffer();
        iNum -= StringUtils.getCharLength(c);
        while (iNum > -1 && iSubIndex < iArrLength) {
            ++iSubIndex;
            sbContent.append(c);
            if (iSubIndex < iArrLength) {
                c = arrChars[iSubIndex];
                iNum -= StringUtils.getCharLength(c);
            }
        }
        strFilter = sbContent.toString();
        if (!StringUtils.isNull(strSuffix)) {
            strFilter += strSuffix;
        }
        return strFilter;
    }

    /**
     * 截取字符串，长度超出的部分用省略号替�?
     *
     * @param strSource 原始字符�?
     * @param iSubLen   截取的长�?
     * @return 截取后的字符�?
     */
    public static String subWithDots(final String strSource, final int iSubLen) {
        return StringUtils.sub(strSource, iSubLen, "...");
    }
}
