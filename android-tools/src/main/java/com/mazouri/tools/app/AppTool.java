package com.mazouri.tools.app;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.mazouri.tools.AndroidTools;

import java.util.List;

/**
 * Created by wangdongdong on 17-1-20.
 */

public final class AppTool {
    private static final String TAG = AppTool.class.getSimpleName();
    private static final Object lock = new Object();
    private static volatile AppTool instance;

    private AppTool() {
    }

    public static AppTool instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new AppTool();
                }
            }
        }
        return instance;
    }

    /**
     * 服务是否运行
     * @param mContext
     * @param className
     * @return
     */
    public boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
                mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (serviceList.size() == 0) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 进程是否运行
     */
    public boolean isProessRunning(Context context, String proessName) {
        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : lists) {
            if (info.processName.equals(proessName)) {
                isRunning = true;
                return isRunning;
            }
        }
        return isRunning;
    }

    /**
     * 获取应用版本号
     *
     * @param ctx
     * @return
     */
    public static int getAppVersionCode(Context ctx) {

        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            if (info != null) {
                return info.versionCode;
            }
        } catch (Exception e) {
            AndroidTools.log().tag(TAG).d("getAppVersionCodeFromPackage Exception ->: " + e.getMessage());
        }
        return -1;
    }

    /**
     * 获取应用版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionNameFromPackage(Context context){
        try {
            PackageManager packageManager=context.getPackageManager();
            PackageInfo packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            return  packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            AndroidTools.log().tag(TAG).d("getVersionNameFromPackage Exception ->: " + e.getMessage());
        }
        return null;
    }

    /**
     * 获取应用包名
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取顶层activity名称
     *
     * @param context
     * @return
     */
    public static String getTopActivityName(Context context) {
        String topActivityClassName = null;
        ActivityManager activityManager =
                (ActivityManager) (context.getSystemService(android.content.Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
        if (runningTaskInfos != null) {
            ComponentName f = runningTaskInfos.get(0).topActivity;
            topActivityClassName = f.getClassName();
        }
        return topActivityClassName;
    }

    /**
     * 判断应用是否在前台运行
     *
     * @param context
     * @return
     */
    public static boolean isRunningForeground(Context context) {
        String packageName = getPackageName(context);
        String topActivityClassName = getTopActivityName(context);
        if (packageName != null && topActivityClassName != null && topActivityClassName.startsWith(packageName)) {
            return true;
        } else {
            return false;
        }
    }

}
