package com.het.share.listener;

import android.graphics.Bitmap;

import com.sina.weibo.sdk.api.BaseMediaObject;

/**
 * @Author sunny
 * @Date 2016/6/8  10:08
 * @Annotation
 */
public interface ILoadNetImage {

    void loadImageSuccess(Bitmap bm, BaseMediaObject musicObject);
    void loadImageFailure();
}
