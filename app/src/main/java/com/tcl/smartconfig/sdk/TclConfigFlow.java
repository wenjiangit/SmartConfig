package com.tcl.smartconfig.sdk;

import android.content.Context;

/**
 * Description TclConfigFlow
 * <p>
 * Date 2019-07-14
 *
 * @author wenjianes@163.com
 */
public interface TclConfigFlow {

    void init(Context context, long timeout);

    void getBindCode() throws ConfigException;

    void connectAp(String ssid, String pwd) throws ConfigException;

    void sendRouteInfo(String ssid, String pwd) throws ConfigException;

    void backToRouter(String ssid, String pwd) throws ConfigException;

    void findDevice() throws ConfigException;

    void bindDevice() throws ConfigException;

    String getResult();

    void dispose();

}
