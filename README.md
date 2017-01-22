# AndroidTools

[![](https://jitpack.io/v/mazouri/AndroidTools.svg)](https://jitpack.io/#mazouri/AndroidTools)

[中文版README](https://github.com/mazouri/AndroidTools/blob/master/%E4%B8%AD%E6%96%87%E7%89%88README.md)

## About AndroidTools

This library contains the most of tools that we use on Android development. Just add this to your module, you could simplify　your code, save your valuable time, and make developing app easier.

## How to use AndroidTools

### 1.Add it in your root build.gradle at the end of repositories:

    allprojects {
		  repositories {
			  ...
			  maven { url 'https://jitpack.io' }
		  }
	  }
    
### 2.Add this to your module's build.gradle file:

    dependencies {
	        compile 'com.github.mazouri:AndroidTools:0.1'
	  }
    
### 3.Initiate AndroidTools in your application class:

    public class YourApplication extends Application {

      @Override
      public void onCreate() {
          super.onCreate();

          //[Required]
          Tools.init(this); 
          
          //[Optional] if u wanna see AndroidTools internal logs, add this line.
          Tools.openToolsLog(true); 
      }
    }
    

### 4.Then U could use tools that u want like this:

    // print log with tag.
    Tools.log().tag(App.class.getSimpleName()).d("print log with tag()"); 
    
    // is null or its length is 0. [will return false]
    Tools.string().isEmpty(App.class.getSimpleName()); 
    
    // is wifi connected? [will return true||false]
    Tools.network().isWifiConnected(this);  
    
## Screenshots [tobeupdate]

## Classify tools [tobeupdate]

**You can also query by **docs****.
### Tools

<table>
<tr>
    <th>Tools.app()</th>
    <th>获取Application, 即Context</th>
</tr>
</table>

### AppTool
    //usage examples:
    Tools.appTool().getAllRunningService(Tools.app()); //获取所有运行的服务
        Tools.appTool().startService(Tools.app(), MockService.class); //启动服务

<table>
    <tr>
        <th>getAllRunningService</th>
        <th>获取所有运行的服务</th>
    </tr>
    <tr>
        <th>startService</th>
        <th>启动服务</th>
    </tr>
    <tr>
        <th>stopService</th>
        <th>停止服务</th>
    </tr>
    <tr>
        <th>bindService</th>
        <th>绑定服务</th>
    </tr>
        <tr>
        <th>unbindService</th>
        <th>解绑服务</th>
    </tr>
    <tr>
        <th>getAppVersionCode</th>
        <th>获取应用版本号</th>
    </tr>
    <tr>
        <th>getLauncherActivity</th>
        <th>获取launcher activity</th>
    </tr>
    <tr>
        <th>getPackageName</th>
        <th>获取应用包名</th>
    </tr>
    <tr>
        <th>getTopActivityName</th>
        <th>获取顶层activity名称</th>
    </tr>
    <tr>
        <th>getVersionNameFromPackage</th>
        <th>获取应用版本名称</th>
    </tr>
    <tr>
        <th>isActivityExists</th>
        <th>判断是否存在Activity</th>
    </tr>
    <tr>
        <th>isAppForeground</th>
        <th>判断App是否处于前台</th>
    </tr>
    <tr>
        <th>isProessRunning</th>
        <th>进程是否运行</th>
    </tr>
    <tr>
        <th>isProessRunning</th>
        <th>进程是否运行</th>
    </tr>
    <tr>
        <th>isRunningForeground</th>
        <th>判断应用是否在前台运行</th>
    </tr>
    <tr>
        <th>isServiceRunning</th>
        <th>服务是否运行</th>
    </tr>
    <tr>
        <th>launchActivity</th>
        <th>打开Activity</th>
    </tr>
    
</table>

### ApkTool
    //usage examples:
    Tools.apk().getAppDetailsSettings(Tools.app()); // 获取App具体设置

<table>
    <tr>
        <th>getAppDetailsSettings</th>
        <th>获取App具体设置</th>
    </tr>
        <tr>
        <th>getAppIcon</th>
        <th>获取App图标</th>
    </tr>
        <tr>
        <th>getAppName</th>
        <th>获取App名称</th>
    </tr>
        <tr>
        <th>getAppPath</th>
        <th>获取App路径</th>
    </tr>
        <tr>
        <th>getAppSignature</th>
        <th>获取App签名</th>
    </tr>
        <tr>
        <th>getAppSignatureSHA1</th>
        <th>获取应用签名的的SHA1值</th>
    </tr>
        <tr>
        <th>getChannelFromApk</th>
        <th>从apk中获取版本信息</th>
    </tr>
        <tr>
        <th>installApp</th>
        <th>安装App</th>
    </tr>
        <tr>
        <th>installAppSilent</th>
        <th>静默安装App</th>
    </tr>
    </tr>
        <tr>
        <th>uninstallAppSilent</th>
        <th>静默卸载App</th>
    </tr>
     </tr>
        <tr>
        <th>isAppDebug</th>
        <th>判断App是否是Debug版本</th>
    </tr>
     </tr>
        <tr>
        <th>isAppInstall</th>
        <th>检查手机上是否安装了指定的软件</th>
    </tr>
     </tr>
        <tr>
        <th>isAppRoot</th>
        <th>判断App是否有root权限</th>
    </tr>
     </tr>
        <tr>
        <th>isSystemApp</th>
        <th>判断App是否是系统应用</th>
    </tr>
     </tr>
        <tr>
        <th>launchApp</th>
        <th>打开App</th>
    </tr>
     </tr>
        <tr>
        <th>uninstallApp</th>
        <th>卸载App</th>
    </tr>

</table>


### InputMethodTool
    //usage examples:
    Tools.inputMethod().isActiveSoftInput(Tools.app()); // 判断输入法是否处于激活状态
    
<table>
    <tr>
        <th>hideInputSoftFromWindowMethod</th>
        <th>隐藏键盘 ：强制隐藏</th>
    </tr>
    <tr>
        <th>isActiveSoftInput</th>
        <th>判断输入负是否处于激活状态</th>
    </tr>
    <tr>
        <th>showInputSoftFromWindowMethod</th>
        <th>显示输入法</th>
    </tr>
</table>

### ProcessTool
    //usage examples:
    Tools.process().getAllBackgroundProcesses(); // 获取后台服务进程
    
<table>
    <tr>
        <th>getAllBackgroundProcesses</th>
        <th>获取所有的后台服务进程</th>
    </tr>
    <tr>
        <th>getForegroundProcessName</th>
        <th>获取前台线程包名</th>
    </tr>
    <tr>
        <th>killAllBackgroundProcesses</th>
        <th>杀死所有的后台服务进程</th>
    </tr>
    <tr>
        <th>killBackgroundProcesses</th>
        <th>杀死后台服务进程</th>
    </tr>
</table>

### IntentTool
    //usage examples:
    Tools.intent().getInstallAppIntent("/sdcard/mock.apk"); // 获取安装App的意图
    
<table>
    <tr>
        <th>getAppDetailsSettingsIntent</th>
        <th>获取App具体设置的意图</th>
    </tr>
    <tr>
        <th>getCaptureIntent</th>
        <th>获取拍照的意图</th>
    </tr>
    <tr>
        <th>getComponentIntent</th>
        <th>获取其他应用组件的意图</th>
    </tr>
    <tr>
        <th>getInstallAppIntent</th>
        <th>获取安装App的意图</th>
    </tr>
    <tr>
        <th>getLaunchAppIntent</th>
        <th>获取打开App的意图</th>
    </tr>
    <tr>
        <th>getShareImageIntent</th>
        <th>获取分享图片的意图</th>
    </tr>
    <tr>
        <th>getShareTextIntent</th>
        <th>获取分享文本的意图</th>
    </tr>
    <tr>
        <th>getShutdownIntent</th>
        <th>获取分享文本的意图</th>
    </tr>
    <tr>
        <th>getUninstallAppIntent</th>
        <th>获取分享文本的意图</th>
    </tr>
</table>

### SnackbarTool
    //usage examples:
    Tools.snackbar().dismissSnackbar()(); // 取消snackbar显示
    
<table>
    <tr>
        <th>addView</th>
        <th>为snackbar添加布局 在show...Snackbar之后调用</th>
    </tr>
     <tr>
        <th>dismissSnackbar</th>
        <th>取消snackbar显示</th>
    </tr>
     <tr>
        <th>showIndefiniteSnackbar</th>
        <th>显示自定义时长snackbar</th>
    </tr>
     <tr>
        <th>showLongSnackbar</th>
        <th>显示长时snackbar</th>
    </tr>
    <tr>
        <th>showShortSnackbar</th>
        <th>显示短时snackbar</th>
    </tr>

</table>

### ProcessTool
    //usage examples:
    Tools.process().getAllBackgroundProcesses(); // 获取后台服务进程
    
<table>
    <tr>
        <th>getAllBackgroundProcesses</th>
        <th>获取后台服务进程</th>
    </tr>
</table>

### ToastTool
    //usage examples:
    Tools.toast().showToast(Toast.app, "show toast"); // 获取后台服务进程
    
<table>
    <tr>
        <th>showToast</th>
        <th>显示Short型Toast</th>
    </tr>
    <tr>
        <th>showToastLong</th>
        <th>显示Long型Toast</th>
    </tr>
</table>

### ConvertTool
    //usage examples:
    Tools.convert().dp2px(50f); // dp转px
    
<table>
    <tr>
        <th>bitmap2Bytes</th>
        <th>bitmap转byteArr</th>
    </tr>
    <tr>
        <th>bitmap2Drawable</th>
        <th>bitmap转drawable</th>
    </tr>
    <tr>
        <th>bits2Bytes</th>
        <th>bits转bytes</th>
    </tr>
    <tr>
        <th>byte2FitMemorySize</th>
        <th>字节数转合适内存大小 保留3位小数</th>
    </tr>
    <tr>
        <th>byte2MemorySize</th>
        <th>字节数转以unit为单位的内存大小</th>
    </tr>
    <tr>
        <th>bytes2Bitmap</th>
        <th>byteArr转bitmap</th>
    </tr>
    <tr>
        <th>bytes2Bits</th>
        <th>bytes转bits</th>
    </tr>
    <tr>
        <th>bytes2Chars</th>
        <th>byteArr转charArr</th>
    </tr>
    <tr>
        <th>bytes2Drawable</th>
        <th>byteArr转drawable</th>
    </tr>
    <tr>
        <th>bytes2HexString</th>
        <th>byteArr转hexString</th>
    </tr>
    <tr>
        <th>bytes2InputStream</th>
        <th>byteArr转inputStream</th>
    </tr>
    <tr>
        <th>bytes2OutputStream</th>
        <th>outputStream转byteArr</th>
    </tr>
    <tr>
        <th>dp2px</th>
        <th>dp转px</th>
    </tr>
    <tr>
        <th>px2dp</th>
        <th>px转dp</th>
    </tr>
    <tr>
        <th>px2sp</th>
        <th>px转sp</th>
    </tr>
    <tr>
        <th>sp2px</th>
        <th>sp转px</th>
    </tr>
    <tr>
        <th>drawable2Bitmap</th>
        <th>drawable转bitmap</th>
    </tr>
    <tr>
        <th>drawable2Bytes</th>
        <th>drawable转byteArr</th>
    </tr>
    <tr>
        <th>hexString2Bytes</th>
        <th>hexString转byteArr</th>
    </tr>
    <tr>
        <th>input2OutputStream</th>
        <th>inputStream转outputStream</th>
    </tr>
    <tr>
        <th>inputStream2Bytes</th>
        <th>inputStream转byteArr</th>
    </tr>
    <tr>
        <th>inputStream2String</th>
        <th>inputStream转string按编码</th>
    </tr>
    <tr>
        <th>memorySize2Byte</th>
        <th>以unit为单位的内存大小转字节数</th>
    </tr>
    <tr>
        <th>millis2FitTimeSpan</th>
        <th>毫秒时间戳转合适时间长度</th>
    </tr>
    <tr>
        <th>millis2TimeSpan</th>
        <th>毫秒时间戳转以unit为单位的时间长度</th>
    </tr>
    <tr>
        <th>output2InputStream</th>
        <th>outputStream转inputStream</th>
    </tr>
    <tr>
        <th>outputStream2Bytes</th>
        <th>outputStream转byteArr</th>
    </tr>
    <tr>
        <th>outputStream2String</th>
        <th>outputStream转string按编码</th>
    </tr>
    <tr>
        <th>string2InputStream</th>
        <th>string转inputStream按编码</th>
    </tr>
    <tr>
        <th>string2OutputStream</th>
        <th>string转outputStream按编码</th>
    </tr>
    <tr>
        <th>timeSpan2Millis</th>
        <th>以unit为单位的时间长度转毫秒时间戳</th>
    </tr>
    <tr>
        <th>view2Bitmap</th>
        <th>view转Bitmap</th>
    </tr>

</table>

### DeviceTool
    //usage examples:
    Tools.device().getAllSDCardSize(); // 获取SD大小
    
<table>
    <tr>
        <th>coptyToClipBoard</th>
        <th>复制到剪切板</th>
    </tr>
     <tr>
        <th>existSDCard</th>
        <th>判断SDCard是否可用</th>
    </tr>
     <tr>
        <th>getAllSDCardSize</th>
        <th>获取SD大小</th>
    </tr>
     <tr>
        <th>getAppPackageNamelist</th>
        <th>获取非系统应用包名</th>
    </tr>
     <tr>
        <th>getAvailaleSDCardSize</th>
        <th>获取可用空间大小</th>
    </tr>
        <tr>
        <th>getIMEI</th>
        <th>获取IMEI</th>
    </tr>
     <tr>
        <th>getLatestCameraPicture</th>
        <th>获取最后一次拍照的图片</th>
    </tr>
     <tr>
        <th>getLocalIPAddress</th>
        <th>获取本机IP地址</th>
    </tr>
     <tr>
        <th>getMac</th>
        <th>获取MAC地址</th>
    </tr>
     <tr>
        <th>getNavigationBarHeight</th>
        <th>获取navigationbar高度</th>
    </tr>
        <tr>
        <th>getScreenPix</th>
        <th>获取手机大小（分辨率）</th>
    </tr>
     <tr>
        <th>getStatusBarHeight</th>
        <th>获取statusbar高度</th>
    </tr>
     <tr>
        <th>getTopBarHeight</th>
        <th>获取状态栏高度＋标题栏(ActionBar)高度</th>
    </tr>
     <tr>
        <th>getUDID</th>
        <th>获取UDID</th>
    </tr>
     <tr>
        <th>goHome</th>
        <th>主动回到Home，后台运行</th>
    </tr>
         <tr>
        <th>isOnline</th>
        <th>是否有网络</th>
    </tr>
         <tr>
        <th>isSoftKeyAvail</th>
        <th>判断是否有软控制键</th>
    </tr>
             <tr>
        <th>isSoftKeyAvail</th>
        <th>判断是否有软控制键</th>
    </tr>
         <tr>
        <th>vibrate</th>
        <th>震动</th>
    </tr>
</table>

### ProcessTool
    //usage examples:
    Tools.process().getAllBackgroundProcesses(); // 获取后台服务进程
    
<table>
    <tr>
        <th>getAllBackgroundProcesses</th>
        <th>获取后台服务进程</th>
    </tr>
</table>

### ProcessTool
    //usage examples:
    Tools.process().getAllBackgroundProcesses(); // 获取后台服务进程
    
<table>
    <tr>
        <th>getAllBackgroundProcesses</th>
        <th>获取后台服务进程</th>
    </tr>
</table>

### ProcessTool
    //usage examples:
    Tools.process().getAllBackgroundProcesses(); // 获取后台服务进程
    
<table>
    <tr>
        <th>getAllBackgroundProcesses</th>
        <th>获取后台服务进程</th>
    </tr>
</table>
