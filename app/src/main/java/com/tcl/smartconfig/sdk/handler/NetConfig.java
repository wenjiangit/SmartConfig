package com.tcl.smartconfig.sdk.handler;

import com.tcl.smartconfig.sdk.ConfigException;
import com.tcl.smartconfig.sdk.ConfigRequest;
import com.tcl.smartconfig.sdk.ConfigResult;

/**
 * Description NetConfig
 * <p>
 * Date 2019-07-20
 *
 * @author wenjianes@163.com
 */
public interface NetConfig {

    ConfigResult<?> performConfig(ConfigRequest request) throws ConfigException;
}
