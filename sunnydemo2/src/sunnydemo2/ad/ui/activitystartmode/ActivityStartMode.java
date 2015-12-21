package sunnydemo2.ad.ui.activitystartmode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/11/18.
 * Annotion:Activity启动模式
 */
public class ActivityStartMode extends Activity {

    private TextView activity_info;

    public static void startActivityStartMode(Context context){
        Intent targetIntent = new Intent(context,ActivityStartMode.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_mode);
        activity_info = (TextView) findViewById(R.id.activity_info);
        activity_info.setText(this.toString());
        Log.e("standard", "onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if(intent !=null)
            setIntent(intent);
        Log.e("standard", "onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("standard", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("standard", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("standard", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("standard", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("standard", "onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("standard", "onSaveInstanceState");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        Log.e("standard", "onStateNotSaved");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e("standard", "onPostResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("standard", "onDestroy");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.e("standard", "onWindowFocusChanged:"+hasFocus);
    }

    public void standard(View view){
        int i=0;
        startActivityStartMode(this);
        i++;
        Toast.makeText(this,"创建次数："+i,Toast.LENGTH_SHORT).show();
    }

    public void singleTask(View view){
        Intent intent = new Intent(this,ActivityA.class);
        startActivity(intent);
    }

    public void singleInstance(View view){
        Intent intent = new Intent(this,ActivityC.class);
        startActivity(intent);
    }

    public void singleTop(View view){
        Intent intent = new Intent(this,ActivityB.class);
        startActivity(intent);
    }
}
