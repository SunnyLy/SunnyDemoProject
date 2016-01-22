package com.smartbracelet.sunny.sunnydemo2.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.het.share.dialog.CommonShareDialog;
import com.het.share.listener.ICommonShareLinstener;
import com.het.share.manager.CommonShareManager;
import com.het.share.manager.CommonSharePlatform;
import com.sina.weibo.sdk.utils.LogUtil;
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

    private ICommonShareLinstener mShareListener;

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
        LogUtil.e("platform",resp.getType()+"");
        CommonShareDialog.onResp(resp);
       /* String result = "";
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "发送成功";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                break;
            default:
                result = "发送返回";
                break;
        }

        Toast.makeText(this, result+resp.getType()+","+resp.getClass().getSimpleName(), Toast.LENGTH_LONG).show();*/
        this.finish();
    }

}