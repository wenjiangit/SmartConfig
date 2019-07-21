package com.tcl.smartconfig.sdk;

/**
 * Description ConfigEngine
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
public interface ConfigEngine {


    /**
     * 执行配网
     *
     * @param context
     * @return
     * @throws ConfigException
     */
    ConfigResult performConfig(ConfigContext context) throws ConfigException;


    /**
     * 取消配网
     */
    void dispose();

}
