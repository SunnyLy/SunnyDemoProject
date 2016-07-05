package sunnydemo2.plugin;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.smartbracelet.sunny.sunnydemo2.R;

import sunnydemo2.AppApplication;

/**
 * @Author sunny
 * @Date 2016/7/5  14:14
 * @Annotation
 */
public class GrabRedpacketActivity extends GrabRedpacketBaseSettingActivity {

    private Dialog mTipsDialog;
    private MainFragment mMainFragment;

    public static void startGrabRedpacketActivity(Context context){
        Intent targetIntent = new Intent(context,GrabRedpacketActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String version = "";
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = " v" + info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        setTitle(getString(R.string.app_name) + version);

        AppApplication.activityStartMain(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(GrabRedpacketConfig.ACTION_QIANGHONGBAO_SERVICE_CONNECT);
        filter.addAction(GrabRedpacketConfig.ACTION_QIANGHONGBAO_SERVICE_DISCONNECT);
        filter.addAction(GrabRedpacketConfig.ACTION_NOTIFY_LISTENER_SERVICE_DISCONNECT);
        filter.addAction(GrabRedpacketConfig.ACTION_NOTIFY_LISTENER_SERVICE_CONNECT);
        registerReceiver(qhbConnectReceiver, filter);
    }

    @Override
    public Fragment getSettingsFragment() {
        mMainFragment = new MainFragment();
        return mMainFragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(qhbConnectReceiver);
        } catch (Exception e) {}
        mTipsDialog = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(GrabRedpacketService.isRunning()) {
            if(mTipsDialog != null) {
                mTipsDialog.dismiss();
            }
        } else {
            showOpenAccessibilityServiceDialog();
        }

        boolean isAgreement = GrabRedpacketConfig.getConfig(this).isAgreement();
        if(!isAgreement) {
            showAgreementDialog();
        }
    }

    /**
     * 开启无障碍服务
     */
    private void showOpenAccessibilityServiceDialog() {
        if(mTipsDialog != null && mTipsDialog.isShowing()) {
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.dialog_tips_layout, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccessibilityServiceSettings();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.open_service_title);
        builder.setView(view);
        builder.setPositiveButton(R.string.open_service_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAccessibilityServiceSettings();
            }
        });
        mTipsDialog = builder.show();
    }

    /** 打开辅助服务的设置*/
    private void openAccessibilityServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 显示免责声明的对话框*/
    private void showAgreementDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("免责声明");
        builder.setMessage(getString(R.string.accessibility_description));
        builder.setPositiveButton("同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GrabRedpacketConfig.getConfig(getApplicationContext()).setAgreement(true);
                AppApplication.eventStatistics(GrabRedpacketActivity.this, "agreement", "true");
            }
        });
        builder.setNegativeButton("不同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GrabRedpacketConfig.getConfig(getApplicationContext()).setAgreement(false);
                AppApplication.eventStatistics(GrabRedpacketActivity.this, "agreement", "false");
                finish();
            }
        });
        builder.show();
    }

    private BroadcastReceiver qhbConnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(isFinishing()) {
                return;
            }
            String action = intent.getAction();
            Log.d("MainActivity", "receive-->" + action);
            if(GrabRedpacketConfig.ACTION_QIANGHONGBAO_SERVICE_CONNECT.equals(action)) {
                if (mTipsDialog != null) {
                    mTipsDialog.dismiss();
                }
            } else if(GrabRedpacketConfig.ACTION_QIANGHONGBAO_SERVICE_DISCONNECT.equals(action)) {
                showOpenAccessibilityServiceDialog();
            } else if(GrabRedpacketConfig.ACTION_NOTIFY_LISTENER_SERVICE_CONNECT.equals(action)) {
                if(mMainFragment != null) {
                    mMainFragment.updateNotifyPreference();
                }
            } else if(GrabRedpacketConfig.ACTION_NOTIFY_LISTENER_SERVICE_DISCONNECT.equals(action)) {
                if(mMainFragment != null) {
                    mMainFragment.updateNotifyPreference();
                }
            }
        }
    };

    /** 打开通知栏设置*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private void openNotificationServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 内容界面
     */
    public static class MainFragment extends BaseSettingsFragment {

        private SwitchPreference notificationPref;
        private boolean notificationChangeByUser = true;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.main);

            //微信红包开关
            Preference wechatPref = findPreference(GrabRedpacketConfig.KEY_ENABLE_WECHAT);
            wechatPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if((Boolean) newValue && !GrabRedpacketService.isRunning()) {
                        ((GrabRedpacketActivity)getActivity()).showOpenAccessibilityServiceDialog();
                    }
                    return true;
                }
            });

            notificationPref = (SwitchPreference) findPreference("KEY_NOTIFICATION_SERVICE_TEMP_ENABLE");
            notificationPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        Toast.makeText(getActivity(), "该功能只支持安卓4.3以上的系统", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    if(!notificationChangeByUser) {
                        notificationChangeByUser = true;
                        return true;
                    }

                    boolean enalbe = (boolean) newValue;

                    GrabRedpacketConfig.getConfig(getActivity()).setNotificationServiceEnable(enalbe);

                    if(enalbe && !GrabRedpacketService.isNotificationServiceRunning()) {
                        ((GrabRedpacketActivity)getActivity()).openNotificationServiceSettings();
                        return false;
                    }
                    AppApplication.eventStatistics(getActivity(), "notify_service", String.valueOf(newValue));
                    return true;
                }
            });

            Preference preference = findPreference("KEY_FOLLOW_ME");
            if(preference != null) {
                preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                       // ((GrabRedpacketActivity) getActivity()).showQrDialog();
                        AppApplication.eventStatistics(getActivity(), "about_author");
                        return true;
                    }
                });
            }

            preference = findPreference("KEY_DONATE_ME");
            if(preference != null) {
                preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                       // ((GrabRedpacketActivity) getActivity()).showDonateDialog();
                        AppApplication.eventStatistics(getActivity(), "donate");
                        return true;
                    }
                });
            }

            findPreference("WECHAT_SETTINGS").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                   // startActivity(new Intent(getActivity(), WechatSettingsActivity.class));
                    return true;
                }
            });

            findPreference("NOTIFY_SETTINGS").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    //startActivity(new Intent(getActivity(), NotifySettingsActivity.class));
                    return true;
                }
            });

        }

        /** 更新快速读取通知的设置*/
        public void updateNotifyPreference() {
            if(notificationPref == null) {
                return;
            }
            boolean running = GrabRedpacketService.isNotificationServiceRunning();
            boolean enable = GrabRedpacketConfig.getConfig(getActivity()).isEnableNotificationService();
            if( enable && running && !notificationPref.isChecked()) {
                AppApplication.eventStatistics(getActivity(), "notify_service", String.valueOf(true));
                notificationChangeByUser = false;
                notificationPref.setChecked(true);
            } else if((!enable || !running) && notificationPref.isChecked()) {
                notificationChangeByUser = false;
                notificationPref.setChecked(false);
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            updateNotifyPreference();
        }
    }
}
