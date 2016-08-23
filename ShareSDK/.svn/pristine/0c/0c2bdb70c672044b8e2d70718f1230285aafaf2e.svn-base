package com.het.share.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.het.share.dialog.CommonShareDialog;
import com.het.share.manager.CommonShareManager;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by sunny on 2016/1/21.
 * 此类主要用来处理新浪微博的回调，
 * 设计此类时，想到，第三方应用调用时，必须在BaseActivity中持有
 * 该类的引用，在onNewIntent()等方法中调用我的方法
 */
public class CommonShareProxy implements IWeiboHandler.Response{

    private Context mContext;
    private CommonShareManager mShareManager;
    private IWeiboHandler.Response mResponse;

    public CommonShareProxy(Context context,IWeiboHandler.Response response){
        this.mContext = context;
        this.mResponse = response;
        mShareManager = CommonShareManager.getInstance();
    }


    /**
     * 这里在分享界面Activity的onCreate()中调用，
     *
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState){
        Log.e("CommonShareProxy","onCreate");
        if (savedInstanceState != null && mShareManager.mWeibo != null) {
            mShareManager.mWeibo.handleWeiboResponse(((Activity)mContext).getIntent(), this);
        }
    }

    //这里主要是Tencent分享时会回调
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.e("CommonShareProxy","onActivityResult");
        //QQ回调
        if(requestCode ==Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_QZONE_SHARE){

            Tencent.onActivityResultData(requestCode, resultCode, data, new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    // mShareListener.onShareSuccess(getSharePlatform(),"分享成功");
                }

                @Override
                public void onError(UiError uiError) {
                    //mShareListener.onShareSuccess(getSharePlatform(),"分享失败");
                }

                @Override
                public void onCancel() {
                    // Log.e("分享平台",getSharePlatform().toString());
                    // mShareListener.onShareSuccess(getSharePlatform(),"取消分享");
                }
            });
        }
        //新浪微博回调
        if(mShareManager.mSsoHandler != null){
            mShareManager.mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    public void onNewIntent(Intent intent){
        // 从当前应用唤起微博并进行分享后，返回到当前应用时，需要在此处调用该函数
        // 来接收微博客户端返回的数据；执行成功，返回 true，并调用
        // {@link IWeiboHandler.Response#onResponse}；失败返回 false，不调用上述回调
        Log.e("CommonShareProxy","onNewIntent");
        if(mShareManager.mWeibo != null)
            mShareManager.mWeibo.handleWeiboResponse(intent, mResponse);
    }

    @Override
    public void onResponse(BaseResponse baseResponse) {
        Log.e("CommonShareProxy","onResponse");
       CommonShareDialog.sinaOnResp(baseResponse);
    }
}
