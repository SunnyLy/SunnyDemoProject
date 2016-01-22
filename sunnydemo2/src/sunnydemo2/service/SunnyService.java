package sunnydemo2.service;

import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by sunny on 2016/1/16.
 * Annotion:
 */
public class SunnyService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SunnyService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //onHandledIntent处理发生在后台进程中，
        //所有的耗时任务应该在此实现
        //传入的intent将会被逐个处理，当所有intent处理完后，它会stopSelf()终止自己
    }

    public class SunnyBinder extends Binder{
        public SunnyService getService(){
            return SunnyService.this;
        }
    }

    private SunnyBinder binder = new SunnyBinder();



}
