package com.tcl.smartconfig.sdk.handler;

import com.tcl.smartconfig.sdk.ConfigEngine;
import com.tcl.smartconfig.sdk.ConfigException;
import com.tcl.smartconfig.sdk.ConfigRequest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description BaseHandler
 * <p>
 * Date 2019-07-20
 *
 * @author wenjianes@163.com
 */
public class BaseHandler implements ConfigHandler {

    private final CountDownLatch mDownLatch = new CountDownLatch(1);

    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();

    @Override
    public void handle(ConfigRequest request) throws ConfigException {


        doAction(request);
        try {
            mDownLatch.wait(timeout());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw ConfigException.interrupt(e.getMessage());
        }


    }

    private long timeout() {
        return 0;
    }

    private void doAction(ConfigRequest request) throws ConfigException {

    }


}
