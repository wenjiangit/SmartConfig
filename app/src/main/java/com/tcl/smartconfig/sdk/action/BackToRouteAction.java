package com.tcl.smartconfig.sdk.action;

/**
 * Description BackToRouteAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public class BackToRouteAction extends BaseAction<Boolean> {
    public BackToRouteAction(long timeout) {
        super(timeout);
    }

    @Override
    public void dispose() {

    }

    @Override
    public Boolean call() throws Exception {

        Thread.sleep(4000);
        return null;
    }
}
