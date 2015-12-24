package sunnydemo2.ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.smartbracelet.sunny.sunnydemo2.R;

import sunnydemo2.widget.CompassView;

/**
 * Created by sunny on 2015/12/24.
 * 罗盘
 */
public class ComprassViewActivity extends Activity {

    public static final String TAG = ComprassViewActivity.class.getSimpleName();

    private CompassView mCompassView;

    public static void startCompassViewActivity(Context context){
        Intent targetIntent = new Intent(context,ComprassViewActivity.class);
        context.startActivity(targetIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_view);

        mCompassView = (CompassView) findViewById(R.id.compass_view);

        mCompassView.setBearing(45);
    }
}
