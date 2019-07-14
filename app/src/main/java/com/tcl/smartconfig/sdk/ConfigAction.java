package com.tcl.smartconfig.sdk;

import java.util.concurrent.Callable;

/**
 * Description ConfigAction
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public interface ConfigAction<V> extends Callable<V> {

    /**
     * 取消，释放资源
     */
    void dispose();

    /**
     * 超时时间
     *
     * @return
     */
    long timeout();
}
