package com.nds.pmc.util;

/**
 * Created by Namrata Shah on 12/12/2017.
 */

public final class LogUtil {
    private static boolean DEBUG;

    public LogUtil() {
    }

    public static void enableDebug(boolean b) {
        DEBUG = b;
    }

    public static void init() {
    }

    public static void e(String tag, String msg) {
        if(DEBUG && msg != null) {
            android.util.Log.e(tag, msg);
        }

    }

    public static void d(String tag, String msg, Throwable tr) {
        if(DEBUG && msg != null) {
            android.util.Log.d(tag, msg, tr);
        }

    }

    public static void i(String tag, String msg, Throwable tr) {
        if(DEBUG && msg != null) {
            android.util.Log.i(tag, msg, tr);
        }

    }

    public static void w(String tag, String msg, Throwable tr) {
        if(DEBUG && msg != null) {
            android.util.Log.w(tag, msg, tr);
        }

    }

    public static void e(String tag, String msg, Throwable tr) {
        if(DEBUG && msg != null) {
            android.util.Log.e(tag, msg, tr);
        }

    }

    public static void i(String tag, String msg) {
        if(DEBUG && msg != null) {
            android.util.Log.d(tag, msg);
        }

    }

    public static void d(String tag, String msg) {
        if(DEBUG && msg != null) {
            android.util.Log.d(tag, msg);
        }

    }

    public static void v(String tag, String msg) {
        if(DEBUG && msg != null) {
            android.util.Log.v(tag, msg);
        }

    }

    public static void pst(Throwable e) {
        if(DEBUG) {
            e.printStackTrace();
        }

    }

    public static void wtf(String tag, String msg) {
        if(DEBUG && msg != null) {
            android.util.Log.wtf(tag, msg);
        }

    }

    public static void wtf(String tag, String msg, Throwable t) {
        if(DEBUG && msg != null) {
            android.util.Log.wtf(tag, msg, t);
        }

    }

    public static void w(String tag, String msg) {
        if(DEBUG && msg != null) {
            android.util.Log.w(tag, msg);
        }

    }

}
