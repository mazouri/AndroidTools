package com.mazouri.tools;

import android.content.Context;

import java.io.File;

/**
 * Created by wangdongdong on 17-1-20.
 */

public interface IApk {
    /**
     * 安装一个apk文件
     *
     * @param context
     * @param uriFile
     */
    void install(Context context, File uriFile);

    /**
     * 卸载一个app
     *
     * @param context
     * @param packageName
     */
    void uninstall(Context context, String packageName);

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName
     * @return
     */
    boolean isAvailable(Context context, String packageName);

    /**
     * 从apk中获取版本信息
     *
     * @param context
     * @param channelPrefix
     * @return
     */
    String getChannelFromApk(Context context, String channelPrefix);
}
