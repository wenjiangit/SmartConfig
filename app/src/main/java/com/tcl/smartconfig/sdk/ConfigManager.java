package com.tcl.smartconfig.sdk;

import android.util.Log;

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description ConfigManager
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
public class ConfigManager extends Thread {

    private final ConfigEngine mEngine;
    private final ConfigContext mContext;

    private static volatile boolean isRunning = false;

    ConfigManager(ConfigContext context,ConfigEngine engine) {
        mEngine = engine;
        mContext = context;
        isRunning = true;
    }

    @Override
    public void run() {
        super.run();
        Log.i(TAG, "***************配网开始****************");
        isRunning = true;
        try {
            final ConfigResult configResult = mEngine.performConfig(mContext);
            Log.i(TAG, "配网成功 : " + configResult);
        } catch (ConfigException e) {
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
