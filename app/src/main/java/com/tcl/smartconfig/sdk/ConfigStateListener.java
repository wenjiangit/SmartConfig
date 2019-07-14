package com.tcl.smartconfig.sdk;

/**
 * Description ConfigStateListener
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
public interface ConfigStateListener<T> {


    /**
     * 配网状态变更
     *
     * @param state 状态
     */
    void onConfigStateChanged(ConfigState state);


    /**
     * 配网完成
     *
     * @param result 结果
     */
    void onConfigFinish(ConfigResult<T> result);

}
