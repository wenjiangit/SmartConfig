package com.tcl.smartconfig.sdk;

import android.util.Log;

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description ConfigHandler
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
public class ConfigHandler extends Thread {

    private final ConfigEngine mEngine;
    private final ConfigClient mClient;

    private static volatile boolean isRunning = false;

    ConfigHandler(ConfigClient client) {
        mEngine = client.engine;
        mClient = client;
        isRunning = true;
    }

    @Override
    public void run() {
        super.run();
        Log.i(TAG, "***************配网开始****************");
        isRunning = true;
        try {
            final ConfigResult configResult = mEngine.performConfig(mClient);
            mClient.dispatcher.postResult(configResult);
            Log.i(TAG, "配网成功 : " + configResult);
        } catch (ConfigException e) {
            mClient.dispatcher.postResult(ConfigResult.error(e));
            Log.i(TAG, "配网失败 : " + e.getMessage());
        } finally {
            Log.i(TAG, "***************配网结束****************");
            isRunning = false;
        }
    }

    public void dispose() {
        mEngine.dispose();
    }

    static boolean isRunning() {
        return isRunning;
    }
}
