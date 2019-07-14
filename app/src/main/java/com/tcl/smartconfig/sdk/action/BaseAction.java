package com.tcl.smartconfig.sdk.action;

import com.tcl.smartconfig.sdk.ConfigAction;

/**
 * Description BaseAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public abstract class BaseAction<V> implements ConfigAction<V> {

    private long timeout;

    public BaseAction(long timeout){
        this.timeout = timeout;
    }

    @Override
    public long timeout() {
        return timeout;
    }

}
