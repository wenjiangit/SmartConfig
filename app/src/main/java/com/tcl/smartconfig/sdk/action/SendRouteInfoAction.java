package com.tcl.smartconfig.sdk.action;

import android.util.Log;

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description SendRouteInfoAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public class SendRouteInfoAction extends BaseAction {


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "开始发送路由信息。。。");
    }

    @Override
    protected void doAction() throws Exception {

        Thread.sleep(10_000);

    }

    @Override
    public void dispose() {

        Log.i(TAG, "SendRouteInfoAction dispose: ");

    }
}
