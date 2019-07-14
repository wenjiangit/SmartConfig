package com.tcl.smartconfig.sdk.action;

/**
 * Description SendRouteInfoAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public class SendRouteInfoAction extends BaseAction<String> {


    private boolean flag = true;

    public SendRouteInfoAction(long timeout) {
        super(timeout);
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);

        return "";
    }

    @Override
    public void dispose() {

    }
}
