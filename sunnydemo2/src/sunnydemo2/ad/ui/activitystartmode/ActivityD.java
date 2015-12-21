package sunnydemo2.ad.ui.activitystartmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smartbracelet.sunny.sunnydemo2.R;

import sunnydemo2.StartActivity;

/**
 * Created by sunny on 2015/11/18.
 * Annotion:
 */
public class ActivityD extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
        TextView activityD_taskId = (TextView) findViewById(R.id.activityD_taskId);
        activityD_taskId.setText("ActivityD所在taskID:"+getTaskId());
    }

    public void start(View view){
        Intent intent = new Intent(this,ActivityD.class);
        startActivity(intent);
    }

    public void startA(View view){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void startC(View view){
        Intent intent = new Intent(this, ActivityC.class);
        startActivity(intent);
    }


}
