package com.het.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.http.protocol.HTTP;
import android.text.TextUtils;
import android.util.Base64;
public class MD5Utils {

    /**
     * 消息摘要.
     */
    private static MessageDigest sDigest;

    static {
        try {
            MD5Utils.sDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LogUtils.e("获取MD5信息摘要失败"+ e);
        }
    }

    /**
     * 私有的构造方法.
     */
    private MD5Utils() {
    }

    /**
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        if (MD5Utils.sDigest == null) {
            LogUtils.e("MD5信息摘要初始化失败");
            return null;
        } else if (TextUtils.isEmpty(inStr)) {
            LogUtils.e("参数strSource不能为空");
            return null;
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = MD5Utils.sDigest.digest(byteArray);
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
        if (MD5Utils.sDigest == null) {
            LogUtils.e( "MD5信息摘要初始化失败");
            return null;
        } else if (TextUtils.isEmpty(strSource)) {
            LogUtils.e("参数strSource不能为空");
            return null;
        }
        try {
            byte[] md5Bytes = MD5Utils.sDigest.digest(strSource
                    .getBytes(HTTP.UTF_8));
            byte[] encryptBytes = Base64.encode(md5Bytes, Base64.DEFAULT);
            String strEncrypt = new String(encryptBytes,HTTP.UTF_8);
            return strEncrypt.substring(0, strEncrypt.length() - 1); // 截断Base64产生的换行符
        } catch (UnsupportedEncodingException e) {
            LogUtils.e("加密模块暂不支持此字符集合"+ e);
        }
        return null;
    }
}
