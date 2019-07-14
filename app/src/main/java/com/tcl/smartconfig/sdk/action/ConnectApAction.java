package com.tcl.smartconfig.sdk.action;

/**
 * Description ConnectApAction
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
public class ConnectApAction extends BaseAction<Void> {

    String ssid;
    String pwd;

    public ConnectApAction(String ssid, String pwd,long timeout) {
        super(timeout);
        this.ssid = ssid;
        this.pwd = pwd;
    }


    @Override
    public Void call() throws Exception {
        Thread.sleep(2000);
        return null;
    }

    @Override
    public void dispose() {

    }

}
