package sunnydemo2.androidl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/12/17.
 * Android L 新控件Toolbar的使用范例
 * Toolbar:toolbar代替了之前的actionbar
 */
public class ToolbarActivity extends ActionBarActivity {


    public static void startToolbarActivity(Context context){
        Intent targetIntent = new Intent(context,ToolbarActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);


       /* Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        this.setSupportActionBar(toolbar);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,null);
        return true;
    }

    /**
     * 菜单项item选择
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                break;
        }
        return true;
    }
}
