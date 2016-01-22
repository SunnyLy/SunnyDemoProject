package sunnydemo2.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by sunny on 2016/1/16.
 * Annotion:
 */
public class ServiceActivity extends Activity {

    private SunnyService sunnyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定Service,只有调用该方法，才会回调ServiceConnection
        bindService(new Intent(this,SunnyService.class),serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //当Service与Activity建立连接时调用
            sunnyService = ((SunnyService.SunnyBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            //断开连接时调用
            sunnyService = null;
        }
    };
}
