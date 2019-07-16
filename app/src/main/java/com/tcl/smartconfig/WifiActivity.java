package com.tcl.smartconfig;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tcl.smartconfig.sdk.WifiHelper;

public class WifiActivity extends AppCompatActivity {

    private EditText mEditSsid;
    private EditText mEditPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        mEditSsid = findViewById(R.id.edit_ssid);
        mEditPwd = findViewById(R.id.edit_pwd);

        final WifiHelper helper = new WifiHelper(this);
        helper.openWifi();


        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,

        },100);


        findViewById(R.id.bt_connect)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String ssid = mEditSsid.getText().toString().trim();
                        final String pwd = mEditPwd.getText().toString().trim();
                        helper.connect(pwd,ssid);
                    }
                });


    }
}
