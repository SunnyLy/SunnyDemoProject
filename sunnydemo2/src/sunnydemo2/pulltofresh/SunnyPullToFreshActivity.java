package sunnydemo2.pulltofresh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2016/1/5.
 * 刷新头用动画的下拉刷新控件
 */
public class SunnyPullToFreshActivity  extends Activity{

    public static final String TAG = SunnyPullToFreshActivity.class.getSimpleName();

    public static void startSunnyPullToFreshActivity(Context context){
        Intent targetIntent = new Intent(context,SunnyPullToFreshActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunny_pulltofresh);

        final SunnyPullToFreshLayout pullToFreshLayout = (SunnyPullToFreshLayout) findViewById(R.id.pull_to_refresh_head);
        pullToFreshLayout.setOnRefreshListener(new SunnyPullToFreshLayout.PullToRefreshListener() {
            @Override
            public void onRefresh() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                pullToFreshLayout.finishRefreshing();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SunnyPullToFreshActivity.this,"刷新完毕",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        },0);
    }
}
