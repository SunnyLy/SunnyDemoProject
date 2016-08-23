package sunnydemo2.sharesdk;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.het.share.listener.IShareOperate;
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
 * @Date 2016/8/23  14:59
 * @Version v1.0.0
 * @Annotation
 */
public class TestShareOpreate implements IShareOperate {
    private Context mContext;

    public TestShareOpreate(Context context) {
        this.mContext = context;
    }

    @Override
    public void sharePic(CommonShareBaseBean sharePicBean, Object iPlatform) {
        tips("图片分享");
    }

    @Override
    public void shareMusic(CommonShareMusic shareBean, Object object) {
        tips("音乐分享");
    }

    @Override
    public void shareText(CommonShareTextOnly shareTextBean, Object object) {
        tips("分享文字");
    }

    @Override
    public void shareVideo(CommonShareVideo shareVideo, Object object) {
        tips("分享视频");
    }

    @Override
    public void shareWebPage(CommonShareWebpage webPageBean, Object object) {
        tips("分享网页");
    }

    public void shareAll(){
        Toast.makeText(mContext,"分享测试===",Toast.LENGTH_SHORT).show();
    }

    private void tips(String msg){
        Log.e("TestShareOpreate",msg);
    }
}
