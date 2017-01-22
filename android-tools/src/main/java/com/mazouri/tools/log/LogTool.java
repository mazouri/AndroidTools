package com.mazouri.tools.log;

import android.util.Log;

import com.mazouri.tools.Tools;

/**
 * Created by wangdongdong on 17-1-20.
 */

public final class LogTool {

    private boolean DEBUG = true;
    private String mTag;
    private static final String AndroidToolsTAG = "AndroidTools_TAG";

    private static final Object lock = new Object();
    private static volatile LogTool instance;

    public static LogTool instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new LogTool();
                }
            }
        }
        return instance;
    }

    private void generateTag() {
        if (!Tools.string().isEmpty(mTag)) {
            return;
        }

        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(line:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        mTag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
    }

    public void setDebug(boolean debug) {
        this.DEBUG = debug;
    }

    public LogTool tag(String tag) {
        mTag = tag;
        return this;
    }

    public void i(String msg) {
        if (DEBUG) {
            generateTag();
            Log.i(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + mTag + ":] ==========> " + msg);
        }
    }

    public void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + tag + ":] ==========> " + msg);
        }

    }

    public void i(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.i(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + tag + ":] ==========> " + msg, tr);
        }
    }

    public void d(String msg) {
        if (DEBUG) {
            generateTag();
            Log.d(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + mTag + ":] ==========> " + msg);
        }
    }

    public void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + tag + ":] ==========> " + msg);
        }

    }

    public void d(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.d(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + tag + ":] ==========> " + msg, tr);
        }
    }

    public void v(String msg) {
        if (DEBUG) {
            generateTag();
            Log.v(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + mTag + ":] ==========> " + msg);
        }

    }

    public void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + tag + ":] ==========> " + msg);
        }

    }

    public void e(String msg) {
        if (DEBUG) {
            generateTag();
            Log.e(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + mTag + ":] ==========> " + msg);
        }
    }

    public void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + tag + ":] ==========> " + msg);
        }

    }

    public void e(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.e(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + tag + ":] ==========> " + msg, tr);
        }
    }

    public void w(String msg) {
        if (DEBUG) {
            generateTag();
            Log.w(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + mTag + ":] ==========> " + msg);
        }
    }

    public void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(AndroidToolsTAG, "{Thread:" + Thread.currentThread().getName() + "}" + "[tag=>" + tag + ":] ==========> " + msg);
        }
    }
}
