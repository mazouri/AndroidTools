package com.mazouri.tools.device;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.mazouri.tools.AndroidTools;

import java.io.File;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * 设备相关工具类
 *
 * Created by wangdongdong on 17-1-20.
 */

public final class DeviceTool {

    private static final Object lock = new Object();
    private static volatile DeviceTool instance;

    private DeviceTool() {
    }

    public static DeviceTool instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DeviceTool();
                }
            }
        }
        return instance;
    }

    /**
     * 判断SDCard是否可用
     */
    public boolean existSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取本机IP地址
     * @return
     */
    public String getLocalIPAddress() {

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            return "0.0.0.0";
        }
        return "0.0.0.0";
    }

    /**
     * 多个SD卡时 取外置SD卡
     * @return
     */
    public String getExternalStorageDirectory() {
        // 参考文章
        // http://blog.csdn.net/bbmiku/article/details/7937745
        Map<String, String> map = System.getenv();
        String[] values = new String[map.values().size()];
        map.values().toArray(values);
        String path = values[values.length - 1];
        if (path.startsWith("/mnt/") && !Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                .equals(path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取可用空间大小
     * @return
     */
    public long getAvailaleSDCardSize() {
        if (!existSDCard()) {
            return 0l;
        }
        File path = Environment.getExternalStorageDirectory(); //取得sdcard文件路径
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取SD大小
     * @return
     */
    public long getAllSDCardSize() {
        if (!existSDCard()) {
            return 0l;
        }
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getBlockCount();
        return availableBlocks * blockSize;
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if(cm != null) {
            NetworkInfo network = cm.getActiveNetworkInfo();

            if (network != null) {
                return network.isAvailable();
            } else
                return false;
        }else {
            return false;
        }
    }

    /**
     * 获取IMEI
     * @param context
     * @return
     */
    public String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if (AndroidTools.string().isEmpty(imei)) {
            imei = "";
        }

        return imei;
    }

    /**
     * 获取MAC地址
     * @param context
     * @return
     */
    public String getMac(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();
        if (AndroidTools.string().isEmpty(mac)) {
            mac = "";
        }
        return mac;
    }

    /**
     * 获取UDID
     * @param context
     * @return
     */
    public String getUDID(Context context) {
        String udid = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if (AndroidTools.string().isEmpty(udid) || udid.equals("9774d56d682e549c")
                || udid.length() < 15) {
            SecureRandom random = new SecureRandom();
            udid = new BigInteger(64, random).toString(16);
        }

        if (AndroidTools.string().isEmpty(udid)) {
            udid = "";
        }

        return udid;
    }

    /**
     * 震动
     * @param context
     * @param duration
     */
    public void vibrate(Context context, long duration) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {
                0, duration
        };
        vibrator.vibrate(pattern, -1);
    }

    /**
     * 获取最后一次拍照的图片
     * @param context
     * @return
     */
    public String getLatestCameraPicture(Context context) {

        if (!existSDCard()) {
            return null;
        }

        String[] projection = new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.Images.ImageColumns.MIME_TYPE};
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null,
                MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");
        if (cursor.moveToFirst()) {
            String path = cursor.getString(1);
            return path;
        }
        return null;
    }

    /**
     * 获取手机大小（分辨率）
     *
     * @param activity
     * @return
     */
    public DisplayMetrics getScreenPix(Activity activity) {
        // DisplayMetrics 一个描述普通显示信息的结构，例如显示大小、密度、字体尺寸
        DisplayMetrics displaysMetrics = new DisplayMetrics();
        // 获取手机窗口的Display 来初始化DisplayMetrics 对象
        // getManager()获取显示定制窗口的管理器。
        // 获取默认显示Display对象
        // 通过Display 对象的数据来初始化一个DisplayMetrics 对象
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displaysMetrics);
        return displaysMetrics;
    }

    public static float getScreenDensity() {
        return AndroidTools.app().getResources().getDisplayMetrics().density;
    }

    public static int getScreenHeight() {
        return AndroidTools.app().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return AndroidTools.app().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 复制到剪切板
     * @param context
     * @param content
     */
    @TargetApi(11)
    public void coptyToClipBoard(Context context, String content) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", content);
            clipboard.setPrimaryClip(clip);
        } else {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(content);
        }
    }

    /**
     * 获取非系统应用包名
     * @param context
     * @return
     */
    public List<String> getAppPackageNamelist(Context context) {
        List<String> packList = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packinfos = pm.getInstalledPackages(0);
        for (PackageInfo packinfo : packinfos) {
            String packname = packinfo.packageName;
            packList.add(packname);
        }

        return packList;
    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 判断是否有软控制键（手机底部几个按钮）
     * @param activity
     * @return
     */
    public boolean isSoftKeyAvail(Activity activity) {
        final boolean[] isSoftkey = {false};
        final View activityRootView = (activity).getWindow().getDecorView().findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int rootViewHeight = activityRootView.getRootView().getHeight();
                int viewHeight = activityRootView.getHeight();
                int heightDiff = rootViewHeight - viewHeight;
                if (heightDiff > 100) { // 99% of the time the height diff will be due to a keyboard.
                    isSoftkey[0] = true;
                }
            }
        });
        return isSoftkey[0];
    }

    /**
     * 获取statusbar高度
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }

        return height;
    }

    /**
     * 获取navigationbar高度
     * @param context
     * @return
     */
    public int getNavigationBarHeight(Context context) {
        int height = 0;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        //获取NavigationBar的高度
        if (resourceId > 0) {
            height = resources.getDimensionPixelSize(resourceId);
        }
        return height;
    }

    /**
     * 获取状态栏高度＋标题栏(ActionBar)高度
     * (注意，如果没有ActionBar，那么获取的高度将和上面的是一样的，只有状态栏的高度)
     * @param activity
     * @return
     */
    public int getTopBarHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT)
                .getTop();
    }


    /**
     *
     * @Title: startActivityForPackage
     * @Description: TODO(通过)
     * @param @param context
     * @param @param packageName   包名
     * @return void    返回类型
     * @throws
     */
    @SuppressLint("NewApi")
    public boolean startActivityForPackage(Context context, String packageName) {
        PackageInfo pi = null;
        try {
            pi = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //		resolveIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        resolveIntent.setPackage(pi.packageName);

        List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(resolveIntent, 0);

        ResolveInfo ri = apps.iterator().next();
        if (ri != null) {
            String packageName1 = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

            ComponentName cn = new ComponentName(packageName1, className);

            intent.setComponent(cn);
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 主动回到Home，后台运行
     * @param context
     */
    public void goHome(Context context) {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }

    /**
     * 返回移动终端类型
     * PHONE_TYPE_NONE :0 手机制式未知
     * PHONE_TYPE_GSM :1 手机制式为GSM，移动和联通
     * PHONE_TYPE_CDMA :2 手机制式为CDMA，电信
     * PHONE_TYPE_SIP:3
     * @param context
     * @return
     */
    public int getPhoneType(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getPhoneType();
    }

    /**
     * 拨打电话
     * @param context
     * @param phoneNumber 电话号码
     */
    public void callPhone(Context context, String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
    }

    /**
     * 跳转至拨号界面
     * @param context
     * @param phoneNumber 电话号码电话号码
     */
    public void callDial(Context context, String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
    }

    /**
     * 发送短信
     * @param context
     * @param phoneNumber
     * @param content
     */
    public void sendSms(Context context, String phoneNumber,
                               String content) {
        Uri uri = Uri.parse("smsto:"
                + (TextUtils.isEmpty(phoneNumber) ? "" : phoneNumber));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", TextUtils.isEmpty(content) ? "" : content);
        context.startActivity(intent);
    }

    /**
     * 判断当前设备是否为手机
     * @param context
     * @return
     */
    public static boolean isPhone(Context context) {
        TelephonyManager telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (telephony.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
            return false;
        } else {
            return true;
        }
    }
}
