package com.tcl.smartconfig.sdk;

/**
 * Description ConfigResult
 * <p>
 * Date 2019-07-14
 *
 * @author wenjianes@163.com
 */
public class ConfigResult<T> {

    public final T data;
    public final Exception ex;

    private ConfigResult(T data, Exception ex) {
        this.data = data;
        this.ex = ex;
    }

    @SuppressWarnings("unchecked")
    static <T> ConfigResult success(T data) {
        return new ConfigResult(data, null);
    }

    @SuppressWarnings("unchecked")
    static ConfigResult error(Exception e) {
        return new ConfigResult(null, e);
    }


    public boolean success() {
        return ex == null;
    }

    @Override
    public String toString() {
        return "ConfigResult{" +
                "data=" + data +
                ", ex=" + ex +
                '}';
    }
}
