package com.het.common.callback;

/**
 * 登出回调接口
 * @author weatherfish
 *
 */
public interface ITokenExpired {
	void logout(String message);
}
