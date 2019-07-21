package com.tcl.smartconfig.sdk.action;

import android.util.Log;

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description FindDeviceAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public class FindDeviceAction extends BaseAction {

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "开始搜索设备 。。。");
    }

    @Override
    public void dispose() {
        Log.i(TAG, "FindDeviceAction dispose: ");

    }


    @Override
    protected void doAction() throws Exception {

        while (true) {
            Log.i(TAG, "doAction: " + System.currentTimeMillis());
        }

    }
}
