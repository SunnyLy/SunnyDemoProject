package com.smartbracelet.sunny.sunnydemo2.wxapi;

import android.app.Activity;
import android.os.Bundle;

import com.het.share.dialog.CommonShareDialog;
import com.het.share.manager.CommonShareManager;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import sunnydemo2.sharesdk.SunnyShareActivity;

/**
 * Created by sunny on 2016/1/19.
 * Annotion:
 */
public class WXEntryActivity extends Activity  implements IWXAPIEventHandler{
    private String WeiXinAppID = SunnyShareActivity.mWeixinAppId;
    private IWXAPI api;

    private CommonShareManager mShareManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShareManger = CommonShareManager.getInstance();
        api = WXAPIFactory.createWXAPI(this, WeiXinAppID, true);
        api.registerApp(WeiXinAppID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        CommonShareDialog.onResp(resp);
        this.finish();
    }

}