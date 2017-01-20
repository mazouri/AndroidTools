package com.mazouri.tools;

import android.app.Application;

import com.mazouri.tools.apk.ApkTool;
import com.mazouri.tools.app.AppTool;
import com.mazouri.tools.app.InputMethodTool;
import com.mazouri.tools.app.ToastTool;
import com.mazouri.tools.device.DeviceTool;
import com.mazouri.tools.device.NetworkTool;
import com.mazouri.tools.image.BitmapTool;
import com.mazouri.tools.io.ExternalStorageTool;
import com.mazouri.tools.log.LogTool;
import com.mazouri.tools.regex.RegexTool;
import com.mazouri.tools.secure.SecureAESTool;
import com.mazouri.tools.secure.SecureBase64Tool;
import com.mazouri.tools.secure.SecureDES3Tool;
import com.mazouri.tools.secure.SecureDESTool;
import com.mazouri.tools.secure.SecureHexTool;
import com.mazouri.tools.secure.SecureMD5Tool;
import com.mazouri.tools.secure.SecureRSATool;
import com.mazouri.tools.string.StringTool;

/**
 * Created by wangdongdong on 17-1-20.
 */

public class AndroidTools {

    private static boolean mOpenLog;
    private static Application mApp;
    private static ApkTool mApk;
    private static StringTool mString;
    private static RegexTool mRegex;
    private static LogTool mLog;
    private static SecureAESTool mAES;
    private static SecureBase64Tool mBase64;
    private static SecureDES3Tool mDES3;
    private static SecureDESTool mDES;
    private static SecureHexTool mHex;
    private static SecureMD5Tool mMD5;
    private static SecureRSATool mRSA;
    private static ExternalStorageTool mExternalStorage;
    private static AppTool mAppTool;
    private static DeviceTool mDevice;
    private static BitmapTool mBitmap;
    private static InputMethodTool mInputMethodTool;
    private static ToastTool mToast;
    private static NetworkTool mNetwork;

    public AndroidTools() {
    }

    public static void init(Application app) {
        if (mApp == null) {
            mApp = app;
        }
    }

    /**
     * if open the AndroidTools log msg.
     * @param isOpenLog
     */
    public static void openToolsLog(boolean isOpenLog) {
        mOpenLog = isOpenLog;
    }

    public static boolean isToolsLogging() {
        return mOpenLog;
    }

    public static Application app() {
        if (mApp == null) {
            throw new RuntimeException("please invoke AndroidTools.init(app) on Application#onCreate()"
                    + " and register your Application in manifest.");
        }
        return mApp;
    }

    public static ApkTool apk() {
        if (mApk == null) {
            mApk = ApkTool.instance();
        }
        return mApk;
    }

    public static StringTool string() {
        if (mString == null) {
            mString = StringTool.instance();
        }
        return mString;
    }

    public static RegexTool regex() {
        if (mRegex == null) {
            mRegex = RegexTool.instance();
        }
        return mRegex;
    }

    public static LogTool log() {
        if (mLog == null) {
            mLog = LogTool.instance();
        }
        return mLog;
    }

    public static SecureAESTool secureAES() {
        if (mAES == null) {
            mAES = SecureAESTool.instance();
        }
        return mAES;
    }

    public static SecureBase64Tool secureBase64() {
        if (mBase64 == null) {
            mBase64 = SecureBase64Tool.instance();
        }
        return mBase64;
    }

    public static SecureDES3Tool secureDES3() {
        if (mDES3 == null) {
            mDES3 = SecureDES3Tool.instance();
        }
        return mDES3;
    }

    public static SecureDESTool secureDES() {
        if (mDES == null) {
            mDES = SecureDESTool.instance();
        }
        return mDES;
    }

    public static SecureHexTool secureHex() {
        if (mHex == null) {
            mHex = SecureHexTool.instance();
        }
        return mHex;
    }

    public static SecureMD5Tool secureMD5() {
        if (mMD5 == null) {
            mMD5 = SecureMD5Tool.instance();
        }
        return mMD5;
    }

    public static SecureRSATool secureRSA() {
        if (mRSA == null) {
            mRSA = SecureRSATool.instance();
        }
        return mRSA;
    }

    public static ExternalStorageTool externalStorage() {
        if (mExternalStorage == null) {
            mExternalStorage = ExternalStorageTool.instance();
        }
        return mExternalStorage;
    }

    public static AppTool appTool() {
        if (mAppTool == null) {
            mAppTool = AppTool.instance();
        }
        return mAppTool;
    }

    public static DeviceTool device() {
        if (mDevice == null) {
            mDevice = DeviceTool.instance();
        }
        return mDevice;
    }

    public static BitmapTool bitmap() {
        if (mBitmap == null) {
            mBitmap = BitmapTool.instance();
        }
        return mBitmap;
    }

    public static InputMethodTool inputMethod() {
        if (mInputMethodTool == null) {
            mInputMethodTool = InputMethodTool.instance();
        }
        return mInputMethodTool;
    }

    public static ToastTool toast() {
        if (mToast == null) {
            mToast = ToastTool.instance();
        }
        return mToast;
    }

    public static NetworkTool network() {
        if (mNetwork == null) {
            mNetwork = NetworkTool.instance();
        }
        return mNetwork;
    }
}
