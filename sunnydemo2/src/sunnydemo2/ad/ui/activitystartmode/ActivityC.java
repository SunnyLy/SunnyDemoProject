package sunnydemo2.ad.ui.activitystartmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/11/18.
 * Annotion:
 */
public class ActivityC extends Activity{

    private TextView mtaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        mtaskId = (TextView) findViewById(R.id.taskId);
        mtaskId.setText("当前Activity所属TaskId:"+getTaskId());
    }

    public void start(View view){
        Intent intent = new Intent(this,ActivityC.class);
        startActivity(intent);
    }

    public void startD(View view){
        Intent intent = new Intent(this,ActivityD.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("singleInstance", "onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("singleInstance", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("singleInstance", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("singleInstance", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("singleInstance", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("singleInstance", "onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("singleInstance", "onSaveInstanceState");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        Log.e("singleInstance", "onStateNotSaved");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e("singleInstance", "onPostResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("singleInstance", "onDestroy");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.e("singleInstance", "onWindowFocusChanged:"+hasFocus);
    }
}
