package sunnydemo2.ad.ui.newproperties;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/11/23.
 * Annotion:
 */
public class PullToRefreshLayout extends Activity implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mRefreshLayout;

    public static void startPullToRefreshLayout(Context context){
        Intent targetIntent = new Intent(context,PullToRefreshLayout.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltofresh);
    }

    @Override
    public void onContentChanged() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        mRefreshLayout.setProgressBackgroundColor(android.R.color.holo_red_light);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
                Toast.makeText(PullToRefreshLayout.this,"Stop to refresh",Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(PullToRefreshLayout.this).setTitle("SingleChoice").
                        setIcon(android.R.drawable.ic_dialog_info)
                        .setSingleChoiceItems(new String[]{"Item1", "Item2"}, 0,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();
                                    }
                                }).show();
            }
        },3000);
    }
}
