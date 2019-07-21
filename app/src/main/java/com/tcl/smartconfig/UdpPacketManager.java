package com.tcl.smartconfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description UdpPacketManager
 * <p>
 * Date 2019-07-21
 *
 * @author wenjianes@163.com
 */
public class UdpPacketManager {

    private final Map<String, PacketReceiveListener> mMap = new ConcurrentHashMap<>();

    public static final Executor EXECUTOR = Executors.newSingleThreadExecutor();

    private UdpReceiver mUdpReceiver;

    private UdpPacketManager() {
        mUdpReceiver = new UdpReceiver(10074);
        mUdpReceiver.setListener(new UdpReceiver.MessageListener() {
            @Override
            public void onMessageArravied(String msg) {
                for (Map.Entry<String, PacketReceiveListener> entry : mMap.entrySet()) {
                    if (msg.contains(entry.getKey())) {
                        entry.getValue().onReceived(msg);
                    }
                }
            }
        });
    }

    public static UdpPacketManager get() {
        return Holder.INSTANCE;
    }


    private static class Holder {
        static final UdpPacketManager INSTANCE = new UdpPacketManager();
    }


    public void registerListener(String topic, PacketReceiveListener listener) {
        mMap.put(topic, listener);
        start();
    }


    public void unRegisterListener(String topic) {
        mMap.remove(topic);
        stopIfNeed();
    }

    public void start(){
        EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                mUdpReceiver.startReceive();
            }
        });
    }

    public void pause(){
        mUdpReceiver.pause();
    }

    public void stop(){
        mUdpReceiver.stop();
    }

    private void stopIfNeed() {
        if (mMap.isEmpty()) {
            mUdpReceiver.stop();
        }
    }


    public interface PacketReceiveListener {

        void onReceived(String msg);
    }
}
