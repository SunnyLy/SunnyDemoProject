package com.het.common.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public final class SecurityUtils {

    /**
     * 消息摘要.
     */
    private static MessageDigest sDigest;

    static {
        try {
            SecurityUtils.sDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
    }

    /**
     * 私有的构造方法.
     */
    private SecurityUtils() {
    }

    /**
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        if (SecurityUtils.sDigest == null) {
            return null;
        } else if (StringUtils.isNull(inStr)) {
            return null;
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = SecurityUtils.sDigest.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 先使用MD5进行加密，再使用Base64进行编码， 若不支持此类字符集合的加密，返回null.
     *
     * @param strSource 待加密的源字符串
     * @return 加密后的字符串，不支持此类字符集合返回null
     */
    public static String encrypt(final String strSource) {
        if (SecurityUtils.sDigest == null) {
            return null;
        } else if (StringUtils.isNull(strSource)) {
            return null;
        }
        try {
            byte[] md5Bytes = SecurityUtils.sDigest.digest(strSource
                    .getBytes("UTF-8"));
            byte[] encryptBytes = Base64.encode(md5Bytes, Base64.DEFAULT);
            String strEncrypt = new String(encryptBytes, "UTF-8");
            return strEncrypt.substring(0, strEncrypt.length() - 1); // 截断Base64产生的换行符
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
    
    public static String encrypt4login(final String strSource, String appSecert) {
    	String str = encrypt(strSource) + appSecert;
		return MD5.getMD5(str);
    }
    
}
