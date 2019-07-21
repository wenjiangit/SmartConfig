package com.tcl.smartconfig.sdk.action;

import android.util.Log;

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description GetBindCodeAction
 * <p>
 * Date 2019-07-14
 *
 * @author wenjianes@163.com
 */
public class GetBindCodeAction extends BaseAction {


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "开始获取BindCode。。。 ");
    }

    @Override
    public void dispose() {

        Log.i(TAG, "GetBindCodeAction dispose: ");

    }




    @Override
    protected void doAction() throws Exception {
        Thread.sleep(3000);

    }
}
