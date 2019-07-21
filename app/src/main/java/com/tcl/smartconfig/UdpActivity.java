package com.tcl.smartconfig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class UdpActivity extends AppCompatActivity {

    private static final String TAG = "UdpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udp);

        UdpPacketManager.get().registerListener("hello", new UdpPacketManager.PacketReceiveListener() {
            @Override
            public void onReceived(String msg) {
                Log.i(TAG, "onReceived: " + msg);
            }
        });


        final UdpSender sender = new UdpSender();

        Runnable runnable = new Runnable() {

            int count = 0;
            @Override
            public void run() {
                while (true){
                    sender.send("hello world ... " + (++count));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(runnable).start();


    }

    public void startUdp(View view) {
        UdpPacketManager.get().start();
    }

    public void pauseUdp(View view) {
        UdpPacketManager.get().pause();
    }

    public void stopUdp(View view) {
        UdpPacketManager.get().stop();
    }
}
