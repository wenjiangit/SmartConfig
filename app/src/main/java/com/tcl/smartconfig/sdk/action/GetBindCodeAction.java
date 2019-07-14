package com.tcl.smartconfig.sdk.action;

/**
 * Description GetBindCodeAction
 * <p>
 * Date 2019-07-14
 *
 * @author wenjianes@163.com
 */
public class GetBindCodeAction extends BaseAction<String> {
    public GetBindCodeAction(long timeout) {
        super(timeout);
    }

    @Override
    public void dispose() {

    }

    @Override
    public String call() throws Exception {

        Thread.sleep(3000);

        return "bindcode";
    }
}
