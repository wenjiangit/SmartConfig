package com.tcl.smartconfig;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import okio.ByteString;

/**
 * Description UdpSender
 * <p>
 * Date 2019-07-21
 *
 * @author wenjianes@163.com
 */
public class UdpSender {


    private DatagramSocket mDatagramSocket;

    private static final String TAG = "UdpSender";


    UdpSender() {
        try {
            mDatagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    void send(byte[] source, String ip, int port, int time, int interval) {
        if (source == null || source.length == 0) {
            return;
        }

        int count = 0;

        while (true) {
            InetAddress targetAddress;
            try {
                targetAddress = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                break;
            }
            DatagramPacket packet = new DatagramPacket(source, 0, source.length, targetAddress, port);
            try {
                mDatagramSocket.send(packet);

                Log.d(TAG, "send success:  " + ByteString.of(source).utf8());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            if (++count >= time) {
                break;
            }

        }

    }


    void send(byte[] source) {
        this.send(source, "255.255.255.255", 10074, 1, 0);
    }

    void send(String msg) {
        this.send(msg.getBytes());
    }

}
