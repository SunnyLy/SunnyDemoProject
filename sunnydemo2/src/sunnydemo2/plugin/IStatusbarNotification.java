package sunnydemo2.plugin;

import android.app.Notification;

/**
 * @Author sunny
 * @Date 2016/7/5  11:33
 * @Annotation 状态栏通知接口
 */
public interface IStatusbarNotification {

    //获取包名
    String getPackageName();

    Notification getNotification();
}
