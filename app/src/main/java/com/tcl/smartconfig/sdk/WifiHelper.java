package com.tcl.smartconfig.sdk;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

/**
 * Description WifiHelper
 * <p>
 * Date 2019-07-16
 *
 * @author wenjianes@163.com
 */
public class WifiHelper {

    private WifiManager mWifiManager;

    private static final String TAG = "WifiHelper";

    public WifiHelper(Context context){
        mWifiManager = (WifiManager) context.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
    }

    public void connect( String SSID, String password) {
        WifiConfiguration apConfig = new WifiConfiguration();
        apConfig.SSID = "\"" + SSID + "\"";
        apConfig.preSharedKey = "\"" + password + "\"";
        //不广播其SSID的网络
        apConfig.hiddenSSID = true;
        apConfig.status = WifiConfiguration.Status.ENABLED;
        //公认的IEEE 802.11验证算法。
        apConfig.allowedAuthAlgorithms.clear();
        apConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        //公认的的公共组密码
        apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        //公认的密钥管理方案
        apConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        //密码为WPA。
        apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        //公认的安全协议。
        apConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);

//        final int networkId = mWifiManager.addNetwork(apConfig);
//
//        mWifiManager.enableNetwork(networkId, true);

        //判断wifi曾经是不是连接过
        WifiConfiguration tempConfig = isExsits(SSID);
        if (tempConfig != null) {
            boolean enabled = mWifiManager.enableNetwork(tempConfig.networkId, true);
            Log.d(TAG, "enableNetwork status enable=" + enabled);
        } else {
            int netID = mWifiManager.addNetwork(apConfig);
            boolean enabled = mWifiManager.enableNetwork(netID, true);
            Log.d(TAG, "enableNetwork status enable=" + enabled);
        }
    }

    public void openWifi(){
        final boolean enabled = mWifiManager.isWifiEnabled();
        if (!enabled) {
            mWifiManager.setWifiEnabled(true);
        }
    }


    private WifiConfiguration isExsits(String SSID) {
        if (mWifiManager != null) {
            List<WifiConfiguration> existingConfigs = mWifiManager
                    .getConfiguredNetworks();
            for (WifiConfiguration existingConfig : existingConfigs) {
                if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                    return existingConfig;
                }
            }
        }
        return null;
    }
}
