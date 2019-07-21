package com.tcl.smartconfig.sdk.action;

import android.util.Log;

import com.tcl.smartconfig.sdk.ConfigException;

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description BackToRouteAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public class BackToRouteAction extends BaseAction {


    @Override
    public void dispose() {
        Log.i(TAG, "BackToRouteAction dispose: ");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "开始回连路由 。。。");
    }

    @Override
    protected void onActionEnd() throws ConfigException {
        super.onActionEnd();

    }

    @Override
    protected void doAction() throws Exception {

        Thread.sleep(5000);

    }
}
