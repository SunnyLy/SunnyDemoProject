package sunnydemo2.wps;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.io.File;
import java.io.IOException;

/**
 * ------------------------------------------------
 * Copyright © 2016年 clife. All rights reserved.
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------
 *
 * @Author sunny
 * @Date 2016/8/24  18:49
 * @Version v1.0.0
 * @Annotation
 */
public class WpsDemoActivity extends Activity {

    private static final String packageName = "cn.wps.moffice_eng";//普通版
    private static final String className = "cn.wps.moffice.documentmanager.PreStartActivity2";//WPS的Activity
    private static final String docType = "application/msword";//doc格式的Type
    private static final String docxType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";//docx格式的Type

    private static final String OPEN_MODE = "OpenMode";
    private WpsBroadcastReceiver mReceiver;

    private float prexProgress = 0.0f;//上次查看文件的进度


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wps);
      // IntentFilter saveIntentFilter = new IntentFilter("cn.wps.moffice.file.save");
        IntentFilter closeIntentFilter = new IntentFilter("cn.wps.moffice.file.close");
        mReceiver = new WpsBroadcastReceiver();
        this.registerReceiver(mReceiver,closeIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mReceiver != null || mReceiver.isOrderedBroadcast())
        unregisterReceiver(mReceiver);
    }

    /**
     * 打开文件
     *
     * @param view
     */
    public void openFile(View view) {

        String parentPath = Environment.getExternalStorageDirectory()+File.separator+getApplication().getPackageName();
        String fileName = "aa.docx";
       // String fileName = "abc.doc";

        File parent = new File(parentPath);
        if(parent == null || !parent.exists()){
            parent.mkdirs();
        }

        File file = new File(parent,fileName);
        if(file == null || !file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.e("doc", file.getAbsolutePath());
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        //bundle.putString("OpenMode","Normal");//打开方式，默认为Normal正常模式
        bundle.putBoolean("SendCloseBroad", true);//文件关闭时，发送广播
        bundle.putBoolean("SendSavedBroad",true);//文件保存时，发送广播
        bundle.putBoolean("AutoJump",true);//是否自动跳转至上次查看的进度
        bundle.putString("ThirdPackage", getApplication().getPackageName());
        bundle.putBoolean("ClearBuffer", true);
        bundle.putBoolean("ClearTrace", true);
       // bundle.putBoolean(CLEAR_FILE, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setClassName(packageName, className);
        Uri uri = Uri.fromFile(file);
        if(file.getName().endsWith(".doc")){
            intent.setDataAndType(uri,docType);
        }else if(file.getName().endsWith(".docx")){
            intent.setDataAndType(uri, docxType);
        }
        intent.putExtras(bundle);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(WpsDemoActivity.this,"请先下载安装WPS",Toast.LENGTH_SHORT).show();
        }


    }


    private class WpsBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                Bundle bundle = intent.getExtras();
                if (bundle != null){
                    prexProgress = bundle.getFloat("ViewProgress");
                }
            }
            Log.e("============","收到广播:"+prexProgress);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WpsDemoActivity.this, "收到广播", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
