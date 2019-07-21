package com.tcl.smartconfig.sdk;

/**
 * Description ConfigContext
 * <p>
 * Date 2019-07-20
 *
 * @author wenjianes@163.com
 */
public class ConfigContext {

    public final ConfigRequest request;
     final ConfigResult result;
    public final ConfigOptions options;

    public ConfigContext(ConfigRequest request, ConfigResult result, ConfigOptions options) {
        this.request = request;
        this.result = result;
        this.options = options;
    }
}
