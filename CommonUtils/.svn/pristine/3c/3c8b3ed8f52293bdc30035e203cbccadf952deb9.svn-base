package com.het.common.utils;

import java.util.List;

/**
 * @author weatherfish
 * @version 创建时间：2015年7月13日 下午5:39:07 类说明
 */

public class DeviceControlUtils {
	public static String getUpdateFlag(List<Integer> list) {
		int flag = 0;
		for (int i = 0; i < list.size(); i++) {
			flag |= 1 << (list.get(i) - 1);
		}
		return String.valueOf(flag);
	}

	public static Integer String2Int(String str) {
		if (StringUtils.isNull(str) || str.equals("")) {
			return null;
		}
		return Integer.parseInt(str);
	}
}
