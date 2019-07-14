package com.tcl.smartconfig.sdk;

import android.util.Log;

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description TclConfigEngine
 * <p>
 * Date 2019-07-14
 *
 * @author wenjianes@163.com
 */
public class TclConfigEngine implements ConfigEngine {

    private TclConfigFlow mTclFlow = new TclConfigFlowImpl();


    @Override
    public ConfigResult performConfig(ConfigClient client) throws ConfigException {
        try {
            mTclFlow.init(client.context,client.timeout);

            Log.i(TAG, "获取BindCode。。。");

            client.dispatcher.postState(ConfigState.GET_BIND_CODE);
            mTclFlow.getBindCode();

            Log.i(TAG, "开始连接设备热点。。。");

            client.dispatcher.postState(ConfigState.CONNECT_AP);
            mTclFlow.connectAp(client.apSSID, client.apPwd);

            Log.i(TAG, "开始发送路由信息。。。");

            client.dispatcher.postState(ConfigState.SEND_ROUTE_INFO);
            mTclFlow.sendRouteInfo(client.routeSSID, client.routePwd);

            Log.i(TAG, "开始回连路由。。。");

            client.dispatcher.postState(ConfigState.BACK_TO_ROUTE);
            mTclFlow.backToRouter(client.routeSSID, client.routePwd);

            Log.i(TAG, "开始搜索设备。。。");

            client.dispatcher.postState(ConfigState.FIND_DEVICE);
            mTclFlow.findDevice();

            Log.i(TAG, "开始绑定设备。。。");

            client.dispatcher.postState(ConfigState.BIND_DEVICE);
            mTclFlow.bindDevice();

            return ConfigResult.success(mTclFlow.getResult());
        } catch (ConfigException e) {
            e.printStackTrace();
            throw e;
        } finally {
            mTclFlow.dispose();
        }
    }

    @Override
    public void dispose() {
        mTclFlow.dispose();
    }
}
