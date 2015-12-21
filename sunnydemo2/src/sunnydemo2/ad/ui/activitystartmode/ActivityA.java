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
public class ActivityA extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        TextView taskId = (TextView) findViewById(R.id.taskId);
        taskId.setText("当前Activity所属TaskId:"+getTaskId());
    }

    public void start(View view){
        Intent intent = new Intent(this,ActivityA.class);
        startActivity(intent);
    }
    public void startActivityD(View view){
        Intent intent = new Intent(this,ActivityD.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("singleTask", "onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("singleTask", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("singleTask", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("singleTask", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("singleTask", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("singleTask", "onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("singleTask", "onSaveInstanceState");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        Log.e("singleTask", "onStateNotSaved");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e("singleTask", "onPostResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("singleTask", "onDestroy");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.e("singleTask", "onWindowFocusChanged:"+hasFocus);
    }
}
