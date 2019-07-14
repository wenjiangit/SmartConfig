package com.tcl.smartconfig.sdk;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Description ConfigHelper
 * <p>
 * Date 2019-07-12
 *
 * @author wenjianes@163.com
 */
class ConfigHelper {

    @NonNull
    static <T extends CharSequence> T checkStringNotEmpty(T string, Object errorMessage) {
        if (TextUtils.isEmpty(string)) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        } else {
            return string;
        }
    }


    @NonNull
    static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }
}
