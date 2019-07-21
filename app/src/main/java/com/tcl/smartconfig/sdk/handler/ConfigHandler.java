package com.tcl.smartconfig.sdk.handler;

import com.tcl.smartconfig.sdk.ConfigException;
import com.tcl.smartconfig.sdk.ConfigRequest;

/**
 * Description ConfigHandler
 * <p>
 * Date 2019-07-20
 *
 * @author wenjianes@163.com
 */
public interface ConfigHandler {


    /**
     * 处理配网
     * @param request
     * @throws ConfigException
     */
    void handle(ConfigRequest request) throws ConfigException;
}
