package com.tongyuan.android.zhiquleyuan.activity;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tongyuan.android.zhiquleyuan.R;

/**
 * Created by android on 2017/3/12.
 */

public class SetupWlanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netconfig_inputinfo);
        //TODO 完成设置网络的逻辑. 未完成
        WifiManager wifiManager= (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID();
        //自动获取当前的连接的wifi信息 名称,但是密码拿不到




    }
}
