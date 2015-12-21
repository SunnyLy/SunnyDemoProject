package sunnydemo2.network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sunny on 2015/11/30.
 * Annotion:
 */
public class SunnyHandler extends Handler {
    private Context mContext;

    public SunnyHandler(Context context,Looper looper) {
        super(looper);
        this.mContext = context;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 0:
                Toast.makeText(mContext,(String )msg.obj,Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(mContext,(String )msg.obj,Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(mContext,(String )msg.obj,Toast.LENGTH_SHORT).show();
                Log.e("okhttp:result:",(String)msg.obj);
                break;
            default:
                break;
        }
    }
}
