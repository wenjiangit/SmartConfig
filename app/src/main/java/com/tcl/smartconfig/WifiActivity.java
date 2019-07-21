package com.tcl.smartconfig;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tcl.smartconfig.sdk.WifiHelper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

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


        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,

        }, 100);


        findViewById(R.id.bt_connect)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String ssid = mEditSsid.getText().toString().trim();
                        final String pwd = mEditPwd.getText().toString().trim();
                        helper.connect(pwd, ssid);
                    }
                });


        final Executor executor = Executors.newCachedThreadPool();

        final UdpSender sender = new UdpSender();

        final AtomicInteger count = new AtomicInteger(0);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (count.get() % 2 == 0){

                    sender.send("hello -- " + count.incrementAndGet());
                }else if (count.get() % 5 == 0){
                    sender.send("haha-- " + count.incrementAndGet());
                }else {
                    sender.send("doubi-- " + count.incrementAndGet());
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    executor.execute(runnable);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();




    }
}
