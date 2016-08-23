package com.het.share.listener;

import com.het.share.model.CommonShareBaseBean;
import com.het.share.model.CommonShareMusic;
import com.het.share.model.CommonShareTextOnly;
import com.het.share.model.CommonShareVideo;
import com.het.share.model.CommonShareWebpage;

/**
 * ------------------------------------------------
 * Copyright © 2016年 clife. All rights reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2016/8/23  13:57
 * @Version v1.0.0
 * @Annotation
 */
public interface IShareOperate {

    /**
     * 1.分享文字
     * @param textOnly  分享的文本，当不传时，分享为空
     * @param iPlatform 目标分享平台的对象，eg:IWXAPI
     */
    void shareText(CommonShareTextOnly textOnly, Object iPlatform);

    /**
     * 2.分享图片
     * @param sharePicBean
     * @param iPlatform  目标分享平台的对象，eg:IWXAPI
     */
    void sharePic(CommonShareBaseBean sharePicBean, Object iPlatform);

    /**
     * 3.分享网页
     * @param webPageBean
     * @param iPlatform
     */
    void shareWebPage(CommonShareWebpage webPageBean, Object iPlatform);

    /**
     * 4.分享音乐
     * @param shareBean
     * @param object
     */
    void shareMusic(CommonShareMusic shareBean, Object object);

    /**
     * 5.分享视频
     * @param shareVideo
     * @param object
     */
    void shareVideo(CommonShareVideo shareVideo, Object object);

    /**
     * 6.其它，就由上层继承来自己添加。
     */
}
