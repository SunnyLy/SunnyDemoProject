package sunnydemo2.androidl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/12/17.
 * Android L 新控件Toolbar的使用范例
 * Toolbar:toolbar代替了之前的actionbar
 */
public class ToolbarActivity extends AppCompatActivity {


    public static void startToolbarActivity(Context context){
        Intent targetIntent = new Intent(context,ToolbarActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        this.setSupportActionBar(toolbar);

    }

}
