package com.het.share.listener;

import com.het.share.manager.CommonSharePlatform;

/**
 * Created by sunny on 2016/2/25.
 * Annotion:
 */
public interface ICommonShareListener {
    void onStartShare(CommonSharePlatform sharePlatform);
    void onShareSuccess(CommonSharePlatform sharePlatform, String msg);
    void onShareFialure(CommonSharePlatform sharePlatform, String msg);
    void onShareCancel(CommonSharePlatform sharePlatform,String msg);
}
