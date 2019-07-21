package com.tcl.smartconfig.sdk;

/**
 * Description ConfigException
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
public class ConfigException extends Exception {

    private final int code;

    private static final int TIMEOUT = 1;
    private static final int ERROR = 2;
    private static final int CANCELLED = 3;
    private static final int INTERRUPT = 4;

    private ConfigException(String message, int code) {
        super(message);
        this.code = code;
    }


    public static ConfigException error(String message) {
        return new ConfigException(message, ERROR);
    }

    public static ConfigException timeout(String message) {
        return new ConfigException(message, TIMEOUT);
    }

    public static ConfigException cancel(String message) {
        return new ConfigException(message, CANCELLED);
    }

    public static ConfigException interrupt(String message) {
        return new ConfigException(message, INTERRUPT);
    }


    @Override
    public String getMessage() {
        switch (code) {
            case TIMEOUT:
                return "超时";
            case ERROR:
                return "执行错误: " + super.getMessage();
            case CANCELLED:
                return "取消";
            case INTERRUPT:
                return "打断";
            default:
                return super.getMessage();
        }

    }
}
