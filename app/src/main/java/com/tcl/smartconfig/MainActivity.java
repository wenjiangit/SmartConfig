package com.tcl.smartconfig;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tcl.smartconfig.sdk.ConfigClient;
import com.tcl.smartconfig.sdk.ConfigResult;
import com.tcl.smartconfig.sdk.ConfigState;
import com.tcl.smartconfig.sdk.ConfigStateListener;

/**
 * @author mac
 */
public class MainActivity extends AppCompatActivity implements ConfigStateListener {

    private ConfigClient mClient;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClient = new ConfigClient.Builder(this)
                .build();
    }

    public void stopConfig(View view) {
//        mClient.dispose();
    }

    public void startConfig(View view) {

//        ConfigRequest request = new ConfigRequest.Builder(this)
//                .build();
//
//        mClient.startConfig(request);


    }

    @Override
    public void onConfigStateChanged(ConfigState state) {
        Log.i(TAG, "onConfigStateChanged: " + state);

    }

    @Override
    public void onConfigFinish(ConfigResult result) {
        Log.i(TAG, "onConfigFinish: " + result);
    }

    public void connectWifi(View view) {
        startActivity(new Intent(this, WifiActivity.class));
    }

    public void udp(View view) {
        startActivity(new Intent(this, UdpActivity.class));
    }
}
