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

### ApkTool
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

### ProcessTool
    //usage examples:
    Tools.process().getAllBackgroundProcesses(); // 获取后台服务进程
    
<table>
    <tr>
        <th>getAllBackgroundProcesses</th>
        <th>获取后台服务进程</th>
    </tr>
</table>
