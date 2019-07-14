package com.tcl.smartconfig.sdk;

/**
 * Description ConfigState
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public enum ConfigState {

    /**
     * 获取绑定码
     */
    GET_BIND_CODE(1),

    /**
     * 连接wifi设备
     */
    CONNECT_AP(2),

    /**
     * 发送路由信息
     */
    SEND_ROUTE_INFO(3),

    /**
     * 回连路由
     */
    BACK_TO_ROUTE(4),

    /**
     * 发现设备
     */
    FIND_DEVICE(5),

    /**
     * 绑定设备
     */
    BIND_DEVICE(6);


    public int code;

    ConfigState(int code) {
        this.code = code;
    }


}
