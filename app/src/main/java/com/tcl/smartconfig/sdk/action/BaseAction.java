package com.tcl.smartconfig.sdk.action;

import android.support.annotation.CallSuper;

import com.tcl.smartconfig.sdk.ConfigAction;
import com.tcl.smartconfig.sdk.ConfigContext;
import com.tcl.smartconfig.sdk.ConfigException;

import java.util.concurrent.CountDownLatch;

/**
 * Description BaseAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public abstract class BaseAction implements ConfigAction {

    private static final long TIMEOUT = 20_000;

    private CountDownLatch mDownLatch;

    protected ConfigContext mContext;

    @Override
    public final Void call() throws Exception {
        onStart();
        doAction();

        return null;
    }

    protected void onStart() {

    }

    @Override
    public void attachContext(ConfigContext context) {
        this.mContext = context;

    }

    @CallSuper
    protected void onActionEnd() throws ConfigException {
        if (mDownLatch != null) {
            mDownLatch.countDown();
        }
    }

    protected void waitActionEnd() {
        if (mDownLatch == null) {
            mDownLatch = new CountDownLatch(1);
        }
    }

    protected abstract void doAction() throws Exception;

    @Override
    public long timeout() {
        return TIMEOUT;
    }

}
