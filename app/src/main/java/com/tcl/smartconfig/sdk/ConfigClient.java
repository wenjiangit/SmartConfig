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


    final ConfigEngine engine;

    private ConfigManager mHandler;

    private ConfigClient(ConfigEngine engine) {
        this.engine = engine;
    }


    public void startConfig(ConfigRequest request) {
        if (ConfigManager.isRunning()) {
            Log.i(TAG, "上次配网还未结束，请稍后重试。。。 ");
            return;
        }

        ConfigContext context = new ConfigContext(
                request, null, new ConfigOptions()
        );

        mHandler = new ConfigManager(context, engine);
        mHandler.start();
    }


    public void dispose() {
        if (mHandler != null) {
            mHandler.dispose();
            mHandler = null;
        }
    }


    public static class Builder {

        private ConfigEngine engine;

        private Context context;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder configEngine(ConfigEngine engine) {
            this.engine = engine;
            return this;
        }


        public ConfigClient build() {
            ConfigHelper.checkNotNull(context, "context == null");

            if (engine == null) {
                engine = new TclConfigEngine();
            }

            return new ConfigClient(engine);
        }
    }

}
