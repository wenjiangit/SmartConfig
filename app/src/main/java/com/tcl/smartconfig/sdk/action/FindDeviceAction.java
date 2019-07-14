package com.tcl.smartconfig.sdk.action;

/**
 * Description FindDeviceAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public class FindDeviceAction extends BaseAction<String> {
    public FindDeviceAction(long timeout) {
        super(timeout);
    }

    @Override
    public void dispose() {

    }

    @Override
    public String call() throws Exception {

        Thread.sleep(5000);
        return null;
    }
}
