package com.tcl.smartconfig;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

import okio.ByteString;

/**
 * Description UdpReceiver
 * <p>
 * Date 2019-07-21
 *
 * @author wenjianes@163.com
 */
public class UdpReceiver {

    private volatile DatagramSocket mDatagramSocket;

    private final ByteBuffer mBuffer = ByteBuffer.allocate(1024);

    private static final String TAG = "UdpReceiver";

    /**
     * 暂停监听
     */
    private volatile boolean isPaused = false;
    /**
     * 连接已中断
     */
    private volatile boolean isClosed = false;

    private volatile boolean isRunning = false;

    private MessageListener mListener;
    private int mPort;
    private int mTimeout;

    public UdpReceiver(int port) {
        this(port, 2000);
    }

    public UdpReceiver(int port, int timeout) {
        this.mPort = port;
        this.mTimeout = timeout;
        initialize();
    }

    private void initialize() {
        try {
            mDatagramSocket = new DatagramSocket(null);
            mDatagramSocket.setReuseAddress(true);
            mDatagramSocket.bind(new InetSocketAddress(mPort));
            mDatagramSocket.setSoTimeout(mTimeout);
            isClosed = false;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    public void startReceive() {
        if (isRunning){
            return;
        }
        if (isClosed) {
            initialize();
        }
        if (isPaused) {
            setTimeout(mTimeout);
            isPaused = false;
        }
        isRunning = true;
        while (true) {
            DatagramPacket packet = new DatagramPacket(mBuffer.array(), 0, mBuffer.capacity());
            try {
                mDatagramSocket.receive(packet);

                String data = ByteString.of(packet.getData(), packet.getOffset(), packet.getLength()).utf8();
//                Log.i(TAG, "startReceive: " + data);
                if (mListener != null) {
                    mListener.onMessageArravied(data);
                }
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                if (isPaused) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                close();
                break;
            }
        }
        isRunning = false;
    }

    public void close() {
        isClosed = true;
        if (mDatagramSocket != null) {
            mDatagramSocket.close();
            mDatagramSocket = null;
        }
    }


    private void setTimeout(int timeout) {
        try {
            mDatagramSocket.setSoTimeout(timeout);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        setTimeout(10);
        isPaused = true;
    }

    public void stop() {
        close();
    }

    public boolean isPaused() {
        return isPaused;
    }


    public void setListener(MessageListener listener) {
        mListener = listener;
    }

    public interface MessageListener {

        void onMessageArravied(String msg);
    }

}
