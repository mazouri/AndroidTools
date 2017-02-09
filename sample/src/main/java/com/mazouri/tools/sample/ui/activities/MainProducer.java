package com.mazouri.tools.sample.ui.activities;

import android.content.Context;

import com.mazouri.tools.Tools;

/**
 * Created by wangdongdong on 17-2-8.
 */

public class MainProducer {
    private Context mContent;

    public MainProducer(Context context) {
        this.mContent = context;
    }

    public String buildSysInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("手机型号:  " + Tools.device().getPhoneModel() + "\n")
                .append("SDK版本:  " + Tools.device().getSDKVersion() + "\n")
                .append("系统版本:  " + Tools.device().getSysVersion() + "\n");
        return sb.toString();
    }

    public String buildHardwareInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("手机型号:  " + Tools.device().getPhoneModel() + "\n")
                .append("品牌:  " + Tools.device().getBrand() + "\n")
                .append("产品:  " + Tools.device().getProduct() + "\n")
                .append("主板:  " + Tools.device().getBoard() + "\n")
                .append("序列号:  " + Tools.device().getSerialNumber() + "\n")
                .append("开机时间:  " + Tools.device().getTimes() + "\n");
        return sb.toString();
    }

    public String buildNetInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("网络是否连接:  " + Tools.network().isNetworkConnected(mContent) + "\n")
                .append("网络类型:  " + Tools.network().getNetTypeName(mContent) + "\n")
                .append("WIFI是否连接:  " + Tools.network().isWifiConnected(mContent) + "\n")
                .append("移动数据是否打开:  " + Tools.network().getDataEnabled() + "\n")
                .append("网络是否是4G:  " + Tools.network().is4G() + "\n")
                .append("网络运营商:  " + Tools.network().getNetworkOperatorName() + "\n")
                .append("IP地址:  " + Tools.network().getIPAddress(true) + "\n")
                .append("域名ip地址:\n     www.baidu.com -> " + Tools.network().getDomainAddress("www.baidu.com") + "\n");
        return sb.toString();
    }

    public String buildSimInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("SIM卡是否可用:  " + Tools.device().isSimCardReady() + "\n")
                .append("Sim卡运营商:  " + Tools.device().getSimOperatorName() + "\n")
                .append("IMEI:  " + Tools.device().getIMEI() + "\n")
                .append("IMSI:  " + Tools.device().getIMSI() + "\n")
                .append("当前设备是否为手机:  " + Tools.device().isPhone(mContent) + "\n\n")
                .append("手机状态信息:\n" + Tools.device().getPhoneStatus() + "\n");
        return sb.toString();
    }

    public String buildCpuInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("CPU名称:  " + Tools.device().getCpuName() + "\n")
                .append("最大频率:  " + Tools.device().getCpuMaxFreq() + " KHZ" + "\n")
                .append("最小频率:  " + Tools.device().getCpuMinFreq() + " KHZ" + "\n")
                .append("当前频率:  " + Tools.device().getCpuCurFreq() + " KHZ" + "\n");
        return sb.toString();
    }

    public String buildSDCardInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("是否可用：  " + Tools.externalStorage().isAvailable() + "\n")
                .append("是否可写：  " + Tools.externalStorage().isWritable() + "\n")
                .append("路径：  " + Tools.externalStorage().getSdCardPath() + "\n")
                .append("总存储空间： " + Tools.convert().byte2FitMemorySize(Tools.externalStorage().getAllSDCardSize()) + "\n")
                .append("可用空间：  " + Tools.convert().byte2FitMemorySize(Tools.externalStorage().getAvailaleSDCardSize()) + "\n");
        return sb.toString();
    }
}
