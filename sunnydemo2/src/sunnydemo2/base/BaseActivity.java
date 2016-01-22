package sunnydemo2.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.het.share.utils.CommonShareProxy;

import de.greenrobot.event.EventBus;
import sunnydemo2.widget.CommonLoadingDialog;

/**
 * Created by sunny on 2015/12/29.
 * Annotion:
 */
public class BaseActivity extends Activity {

    public Context mContext;
    public CommonLoadingDialog.LoadingDialog mCommonLoadingDialog;
    private CommonShareProxy commonShareProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonShareProxy = new CommonShareProxy(this);
        commonShareProxy.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        commonShareProxy.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        commonShareProxy.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        mContext = this;
        //EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // EventBus.getDefault().unregister(this);
    }


    public void showDialog() {
        if (mCommonLoadingDialog == null) {
            mCommonLoadingDialog = CommonLoadingDialog.newInstance(mContext);
        }
        mCommonLoadingDialog.show(null);
    }

    public void hideDialog() {
        if (mCommonLoadingDialog != null && mCommonLoadingDialog.isShowing()) {
            mCommonLoadingDialog.hide();
            mCommonLoadingDialog = null;
        }
    }

}
