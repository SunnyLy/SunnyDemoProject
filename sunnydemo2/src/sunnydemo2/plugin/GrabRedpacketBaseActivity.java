package sunnydemo2.plugin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import sunnydemo2.AppApplication;

/**
 * @Author sunny
 * @Date 2016/7/5  14:35
 * @Annotation
 */
public class GrabRedpacketBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppApplication.activityCreateStatistics(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppApplication.activityResumeStatistics(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppApplication.activityPauseStatistics(this);
    }
}
