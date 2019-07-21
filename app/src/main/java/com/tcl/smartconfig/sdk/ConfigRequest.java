package com.tcl.smartconfig.sdk;

import android.content.Context;

/**
 * Description ConfigRequest
 * <p>
 * Date 2019-07-20
 *
 * @author wenjianes@163.com
 */
public class ConfigRequest {

    public final String apSSID;
    public final String apPwd;
    public final String routeSSID;
    public final String routePwd;
    public final Context context;
    public final long timeout;
    public final ResultDispatcher dispatcher;


    private ConfigRequest(Context context, String apSSID, String apPwd,
                         String routeSSID, String routePwd, long timeout,
                         ConfigStateListener listener) {
        this.context = context.getApplicationContext();
        this.apSSID = apSSID;
        this.apPwd = apPwd;
        this.routeSSID = routeSSID;
        this.routePwd = routePwd;
        this.timeout = timeout;

        this.dispatcher = new ResultDispatcher(listener);
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

        public ConfigRequest.Builder setApSSID(String apSSID) {
            this.apSSID = apSSID;
            return this;
        }

        public ConfigRequest.Builder setApPwd(String apPwd) {
            this.apPwd = apPwd;
            return this;
        }

        public ConfigRequest.Builder setRouteSSID(String routeSSID) {
            this.routeSSID = routeSSID;
            return this;
        }

        public ConfigRequest.Builder setRoutePwd(String routePwd) {
            this.routePwd = routePwd;
            return this;
        }

        public ConfigRequest.Builder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public ConfigRequest.Builder configListener(ConfigStateListener listener) {
            this.stateListener = listener;
            return this;
        }

        public ConfigRequest.Builder configEngine(ConfigEngine engine) {
            this.engine = engine;
            return this;
        }


        public ConfigRequest build() {
            ConfigHelper.checkNotNull(context, "context == null");
//            ConfigHelper.checkNotNull(stateListener, "stateListener == null");
//            ConfigHelper.checkStringNotEmpty(apPwd, "apPwd is empty");
//            ConfigHelper.checkStringNotEmpty(apSSID, "apSSID is empty");
//            ConfigHelper.checkStringNotEmpty(routePwd, "routePwd is empty");
//            ConfigHelper.checkStringNotEmpty(routeSSID, "routeSSID is empty");

            if (timeout <= 0) {
                timeout = Const.CONFIG_TIMEOUT;
            }

            if (engine == null) {
                engine = new TclConfigEngine();
            }

            return new ConfigRequest(context, apSSID,
                    apPwd, routeSSID, routePwd, timeout, stateListener);
        }
    }
}
