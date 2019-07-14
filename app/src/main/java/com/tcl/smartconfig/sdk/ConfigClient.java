package com.tcl.smartconfig.sdk;

import android.content.Context;
import android.util.Log;

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description ConfigClient
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
public class ConfigClient {

    final String apSSID;
    final String apPwd;
    final String routeSSID;
    final String routePwd;
    final Context context;
    final long timeout;
    final ResultDispatcher dispatcher;
    final ConfigEngine engine;

    private ConfigHandler mHandler;

    private ConfigClient(Context context, String apSSID, String apPwd,
                         String routeSSID, String routePwd, long timeout,
                         ConfigStateListener listener, ConfigEngine engine) {
        this.context = context.getApplicationContext();
        this.apSSID = apSSID;
        this.apPwd = apPwd;
        this.routeSSID = routeSSID;
        this.routePwd = routePwd;
        this.timeout = timeout;
        this.engine = engine;

        this.dispatcher = new ResultDispatcher(listener);
    }


    public void startConfig() {
        if (ConfigHandler.isRunning()) {
            Log.i(TAG, "上次配网还未结束，请稍后重试。。。 ");
            return;
        }

        mHandler = new ConfigHandler(this);
        mHandler.start();
    }


    public void dispose() {
        if (mHandler != null) {
            mHandler.dispose();
            mHandler = null;
        }
    }


    public static class Builder {
        private String apSSID;
        private String apPwd;
        private String routeSSID;
        private String routePwd;
        private long timeout = Const.CONFIG_TIMEOUT;
        private ConfigStateListener stateListener;
        private ConfigEngine engine;

        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setApSSID(String apSSID) {
            this.apSSID = apSSID;
            return this;
        }

        public Builder setApPwd(String apPwd) {
            this.apPwd = apPwd;
            return this;
        }

        public Builder setRouteSSID(String routeSSID) {
            this.routeSSID = routeSSID;
            return this;
        }

        public Builder setRoutePwd(String routePwd) {
            this.routePwd = routePwd;
            return this;
        }

        public Builder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder configListener(ConfigStateListener listener) {
            this.stateListener = listener;
            return this;
        }

        public Builder configEngine(ConfigEngine engine) {
            this.engine = engine;
            return this;
        }


        public ConfigClient build() {
            ConfigHelper.checkNotNull(context, "context == null");
            ConfigHelper.checkNotNull(stateListener, "stateListener == null");
            ConfigHelper.checkStringNotEmpty(apPwd, "apPwd is empty");
            ConfigHelper.checkStringNotEmpty(apSSID, "apSSID is empty");
            ConfigHelper.checkStringNotEmpty(routePwd, "routePwd is empty");
            ConfigHelper.checkStringNotEmpty(routeSSID, "routeSSID is empty");

            if (timeout <= 0) {
                timeout = Const.CONFIG_TIMEOUT;
            }

            if (engine == null) {
                engine = new TclConfigEngine();
            }

            return new ConfigClient(context, apSSID,
                    apPwd, routeSSID, routePwd, timeout, stateListener, engine);
        }
    }

}
