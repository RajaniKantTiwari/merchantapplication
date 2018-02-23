package com.app.merchant.utility;

import android.util.Log;

/**
 * Created by atul on 28/12/16.
 * To create and manage log.
 */

public class LogUtils {
    private LogUtils() {
    }

    public static void LOGD(final String tag, String message) {
        Log.d(tag, message);
    }


    public static void LOGV(final String tag, String message) {
        Log.v(tag, message);
    }


    public static void LOGE(final String tag, String message) {
        Log.e(tag, message);
    }

    public static void printStackTrace(Throwable e) {
        e.printStackTrace();
    }
}
