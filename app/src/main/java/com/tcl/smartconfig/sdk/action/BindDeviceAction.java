package com.tcl.smartconfig.sdk.action;

/**
 * Description BindDeviceAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public class BindDeviceAction extends BaseAction<String> {
    public BindDeviceAction(long timeout) {
        super(timeout);
    }

    @Override
    public void dispose() {

    }

    @Override
    public String call() throws Exception {

        Thread.sleep(10_000);
        return null;
    }
}
