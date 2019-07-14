package com.tcl.smartconfig.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Description ResultDispatcher
 * <p>
 * Date 2019-07-13
 *
 * @author wenjianes@163.com
 */
public class ResultDispatcher implements Handler.Callback {

    private Handler mHandler;
    private ConfigStateListener mStateListener;

    ResultDispatcher(ConfigStateListener listener) {
        this.mStateListener = listener;
        mHandler = new Handler(Looper.getMainLooper(), this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean handleMessage(Message msg) {
        final int what = msg.what;
        if (what > 0) {
            mStateListener.onConfigStateChanged((ConfigState) msg.obj);
        } else {
            mStateListener.onConfigFinish((ConfigResult) msg.obj);
        }
        return true;
    }

    void postState(ConfigState state) {
        mHandler.obtainMessage(1, state).sendToTarget();
    }

    void postResult(ConfigResult result) {
        mHandler.obtainMessage(0, result).sendToTarget();
    }
}
