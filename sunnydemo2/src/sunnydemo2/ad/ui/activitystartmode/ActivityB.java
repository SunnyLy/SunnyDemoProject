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
public class ActivityB extends Activity{

    private TextView activityb_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        activityb_info = (TextView) findViewById(R.id.activityb_info);
        activityb_info.setText(this.toString());
    }

    public void start(View view){
        Intent intent = new Intent(this,ActivityStartMode.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("singleTop", "onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("singleTop", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("singleTop", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("singleTop", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("singleTop", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("singleTop", "onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("singleTop", "onSaveInstanceState");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        Log.e("singleTop", "onStateNotSaved");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e("singleTop", "onPostResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("singleTop", "onDestroy");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.e("singleTop", "onWindowFocusChanged:"+hasFocus);
    }
}
