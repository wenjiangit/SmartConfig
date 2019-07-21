package com.tcl.smartconfig.sdk.action;

import android.util.Log;

import okhttp3.OkHttpClient;

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description ConnectApAction
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
public class ConnectApAction extends BaseAction {

    String ssid;
    String pwd;

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "开始连接wifi热点。。。");
        pwd = mContext.request.apPwd;
        ssid = mContext.request.apSSID;
    }

    @Override
    protected void doAction() throws Exception {


        Thread.sleep(10_000);


    }

    @Override
    public void dispose() {
        Log.i(TAG, "ConnectApAction dispose: ");
    }

}
