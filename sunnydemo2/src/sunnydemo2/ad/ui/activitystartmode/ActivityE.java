package sunnydemo2.ad.ui.activitystartmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/11/18.
 * Annotion:
 */
public class ActivityE extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
    }

    public void startA(View view){
        Intent intent = new Intent(this,ActivityA.class);
        startActivity(intent);
    }
}
