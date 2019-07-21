package com.tcl.smartconfig.sdk;

import com.tcl.smartconfig.sdk.action.BackToRouteAction;
import com.tcl.smartconfig.sdk.action.BindDeviceAction;
import com.tcl.smartconfig.sdk.action.ConnectApAction;
import com.tcl.smartconfig.sdk.action.FindDeviceAction;
import com.tcl.smartconfig.sdk.action.GetBindCodeAction;
import com.tcl.smartconfig.sdk.action.SendRouteInfoAction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Description TclConfigEngine
 * <p>
 * Date 2019-07-14
 *
 * @author wenjianes@163.com
 */
public class TclConfigEngine implements ConfigEngine {

    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    private final List<Future> mFutureList = new ArrayList<>();

    private static final List<ConfigAction> ACTIONS = new ArrayList<>();

    static {
        ACTIONS.add(new GetBindCodeAction());
        ACTIONS.add(new ConnectApAction());
        ACTIONS.add(new SendRouteInfoAction());
        ACTIONS.add(new BackToRouteAction());
        ACTIONS.add(new FindDeviceAction());
        ACTIONS.add(new BindDeviceAction());
    }


    @Override
    public ConfigResult performConfig(ConfigContext context) throws ConfigException {
        try {
            for (ConfigAction action : ACTIONS) {
                action.attachContext(context);
                executeAction(action);
            }
            return ConfigResult.success(context.result);
        } catch (ConfigException e) {
            e.printStackTrace();
            throw e;
        } finally {
            dispose();
        }
    }

    @Override
    public void dispose() {
        for (Future future : mFutureList) {
            if (!future.isCancelled()) {
                future.cancel(true);
            }
        }
        mFutureList.clear();
    }

    private void executeAction(ConfigAction action) throws ConfigException {
        final Future future = mExecutor.submit(action);
        mFutureList.add(future);
        try {
            future.get(action.timeout(), TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw ConfigException.error(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw ConfigException.interrupt(e.getMessage());
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw ConfigException.timeout(e.getMessage());
        } catch (CancellationException e) {
            e.printStackTrace();
            throw ConfigException.cancel(e.getMessage());
        } finally {
            action.dispose();
        }
    }

}
