package sunnydemo2.ad.ui.ble;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sunny on 2015/11/19.
 * Annotion:蓝牙界面
 */
public class SunnyBLEActivity extends Activity {

    private static final String REMOTE_DEVICE_NAME = "csr1010-mesh";

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothManager mBluetoothManager;


    private TextView ble_scan_result;
    private ProgressBar mProgressBar;

    private StringBuffer sb;
    private Handler mHandler;
    private List<BluetoothDevice> mScanDevices = new ArrayList<>();

    public static void startSunnyBLEActivity(Context context) {
        Intent targetIntent = new Intent(context, SunnyBLEActivity.class);
        context.startActivity(targetIntent);
    }

    //BLE设备扫描回调接口
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(!mScanDevices.contains(device)){
                        mScanDevices.add(device);
                    }
                }
            });


        }
    };

    private void freshUI(List<BluetoothDevice> mScanDevices) {
        for(BluetoothDevice device:mScanDevices){
            device.getAddress();//远程蓝牙设备的Mac
            device.getType();//BLE设备类型
            device.getName();//BLE设备名称
            sb.append("==================="+"\nMac:"+device.getAddress())
                    .append("\nType:"+device.getType())
                    .append("\nName:" + device.getName() + "\n=====================");
            ble_scan_result.setText(sb.toString());
            Log.e("BLE",sb.toString());

            //对指定设备进行一个自动连接
            if(REMOTE_DEVICE_NAME.equals(device.getName())){
                String mac = device.getAddress();
                //判断指定的设备是否配对
               int bondState =  device.getBondState();
                if(bondState != BluetoothDevice.BOND_BONDED){
                    Toast.makeText(this,"指定设备，还未配对，系统正在自动配对",Toast.LENGTH_SHORT).show();

                }


            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble);

        checkMobileBLE();
        initBLEParams();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanBLEDevice(false);
        mScanDevices.clear();
    }

    @Override
    public void onContentChanged() {
        ble_scan_result = (TextView) findViewById(R.id.ble_scan_result);
        mProgressBar = (ProgressBar) findViewById(R.id.ble_pb);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openBLE();
    }

    private void initBLEParams() {
        sb = new StringBuffer();
        mHandler = new Handler();
        // 初始化 Bluetooth adapter, 通过蓝牙管理器得到一个参考蓝牙适配器(API必须在以上android4.3或以上和版本)
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
    }

    /**
     * 检查手机是否支持蓝牙，
     * 不支持，则Toast提示
     */
    private void checkMobileBLE() {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "系统检测到您的手机暂不支持蓝牙服务", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

    }

    /**
     * 如果手机蓝牙未打开，则打开手机蓝牙
     */
    private void openBLE() {

        if(mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()){
           /* //弹出系统对话框来让用户操作打开蓝牙
            Intent enbleBLEIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enbleBLEIntent);*/

            //强制打开蓝牙
            mBluetoothAdapter.enable();
            return;
        }

        //如果蓝牙是打开的，则进行自动扫描，扫描到指定mac的设备后，进行自动连接
        scanBLEDevice(true);
    }

    private void scanBLEDevice(final boolean enable) {
        if(enable){
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    mProgressBar.setVisibility(View.GONE);
                    freshUI(mScanDevices);
                }
            }, 10000);
            mProgressBar.setVisibility(View.VISIBLE);
            mScanDevices.clear();
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        }else{
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }

}
