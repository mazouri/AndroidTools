package com.mazouri.tools;

import android.app.Application;
import android.content.Context;

import com.mazouri.tools.apk.ApkImpl;
import com.mazouri.tools.regex.RegexImpl;
import com.mazouri.tools.string.StringImpl;

import java.lang.reflect.Method;

/**
 * Created by wangdongdong on 17-1-20.
 */

public class AndroidTools {

    private static boolean mDebug;
    private static Application mApp;
    private static IApk mApk;
    private static IString mString;
    private static IRegex mRegex;

    public AndroidTools() {
    }

    public static void init(Application app) {
        if (mApp == null) {
            mApp = app;
        }
    }

    public static void setDebug(boolean debug) {
        mDebug = debug;
    }

    public static boolean isDebug() {
        return mDebug;
    }

    public static Application app() {
        if (mApp == null) {
            throw new RuntimeException("please invoke AndroidTools.init(app) on Application#onCreate()"
                    + " and register your Application in manifest.");
        }
        return mApp;
    }

    public static IApk apk() {
        if (mApk == null) {
            mApk = ApkImpl.instance();
        }
        return mApk;
    }

    public static IString string() {
        if (mString == null) {
            mString = StringImpl.instance();
        }
        return mString;
    }

    public static IRegex regex() {
        if (mRegex == null) {
            mRegex = RegexImpl.instance();
        }
        return mRegex;
    }
}
