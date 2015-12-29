package sunnydemo2.base;

import android.app.Activity;
import android.os.Bundle;

import de.greenrobot.event.EventBus;

/**
 * Created by sunny on 2015/12/29.
 * Annotion:
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
