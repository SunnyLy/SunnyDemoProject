package com.smartbracelet.sunny.sunnydemo3;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sunny on 2015/11/24.
 * Annotion:
 */
public class SettingsActivity extends AppCompatActivity {


    private Toolbar mToolBar;

    public static void startSettingsActivity(Context context){
        Intent tagertIntent = new Intent(context,SettingsActivity.class);
        context.startActivity(tagertIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void onContentChanged() {
        mToolBar = (Toolbar) findViewById(R.id.setting_toolbar);
        mToolBar.setLogo(R.mipmap.ic_launcher);
        mToolBar.setTitle("SunnyDemo");
        //setSupportActionBar(mToolBar);
    }
}
