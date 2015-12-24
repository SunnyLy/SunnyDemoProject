package com.het.common.utils;

import android.os.Environment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by weatherfish on 2015/3/10.
 */
public class CommonUtils {

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.reset();
                digest.update(originString.getBytes());
                String resultString = toHexString(digest.digest());
                return resultString.toLowerCase();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return originString;
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String getPluginPath(String dirType) {
        return Environment.getExternalStoragePublicDirectory(dirType).getPath();
    }

    private static boolean isExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }
}
