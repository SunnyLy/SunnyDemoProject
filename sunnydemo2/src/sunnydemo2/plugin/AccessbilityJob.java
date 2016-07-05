package sunnydemo2.plugin;

import android.view.accessibility.AccessibilityEvent;

/**
 * @Author sunny
 * @Date 2016/7/5  13:39
 * @Annotation
 */
public interface AccessbilityJob {
    String getTargetPackageName();

    void onCreateJob(GrabRedpacketService service);

    void onReceiveJob(AccessibilityEvent event);

    void onStopJob();

    void onNotificationPosted(IStatusbarNotification service);

    boolean isEnable();
}
