package com.tcl.smartconfig.sdk;

import android.content.Context;
import android.util.Log;

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

import static com.tcl.smartconfig.sdk.Const.TAG;

/**
 * Description TclConfigFlowImpl
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
public class TclConfigFlowImpl implements TclConfigFlow {


    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    private final List<Future> mFutureList = new ArrayList<>();
    private String mDevId;
    private String mBindCode;

    private Context mContext;

    private long start;
    private long remain;

    @Override
    public void init(Context context, long timeout) {
        this.mContext = context;
        start = System.currentTimeMillis();
        remain = timeout;
    }

    @Override
    public void getBindCode() throws ConfigException {
        mBindCode = executeAction(new GetBindCodeAction(remain));
        computeRemain(false);
    }

    @Override
    public void connectAp(String ssid, String pwd) throws ConfigException {
        executeAction(new ConnectApAction(ssid, pwd, remain));
        computeRemain(false);
    }

    @Override
    public void sendRouteInfo(String ssid, String pwd) throws ConfigException {
        executeAction(new SendRouteInfoAction(remain));
        computeRemain(false);
    }

    @Override
    public void backToRouter(String ssid, String pwd) throws ConfigException {
        executeAction(new BackToRouteAction(remain));
        computeRemain(false);
    }

    @Override
    public void findDevice() throws ConfigException {
        mDevId = executeAction(new FindDeviceAction(remain));
        computeRemain(false);
    }

    @Override
    public void bindDevice() throws ConfigException {
        executeAction(new BindDeviceAction(remain));
        computeRemain(true);
    }

    @Override
    public String getResult() {
        return null;
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


    @SuppressWarnings("unchecked")
    private <T> T executeAction(ConfigAction<T> action) throws ConfigException {
        final Future future = mExecutor.submit(action);
        mFutureList.add(future);
        try {
            return (T) future.get(action.timeout(), TimeUnit.MILLISECONDS);
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


    /**
     * 计算剩余时间
     *
     * @param lastStep 是否是最后一步
     * @throws ConfigException
     */
    private void computeRemain(boolean lastStep) throws ConfigException {
        final long spend = System.currentTimeMillis() - start;
        Log.i(TAG, " 已耗时 : " + spend);
        remain = remain - spend;
        if (lastStep) {
            return;
        }
        if (remain <= 0) {
            throw ConfigException.timeout("超时");
        }
    }

}
