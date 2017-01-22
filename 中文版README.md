# AndroidTools

[![](https://jitpack.io/v/mazouri/AndroidTools.svg)](https://jitpack.io/#mazouri/AndroidTools)

[English README]

## 关于AndroidTools

This library contains the most of tools that we use on Android development. Just add this to your module, you could simplify　your code, save your valuable time, and make developing app easier.

## 如何使用AndroidTools

### 1.在根目录的build.gradle文件中repositories的末尾添加如下：

    allprojects {
		  repositories {
			  ...
			  maven { url 'https://jitpack.io' }
		  }
	  }
    
### 2.在你的app/build.gradle文件中添加依赖：

    dependencies {
	        compile 'com.github.mazouri:AndroidTools:0.1'
	  }
    
### 3.在你的application类中初始化AndroidTools:

    public class YourApplication extends Application {

      @Override
      public void onCreate() {
          super.onCreate();

	        //必须调用init方法
          Tools.init(this); 
          
          //可选 如果你想看到AndroidTools内部打印的log,　要加上这一行
          Tools.openToolsLog(true); 
      }
    }
    

### 4.然后你可以像下面这样使用【Tools.工具类型.具体方法】:

    // 日志工具，此方法为指定tag打印log
    Tools.log().tag(App.class.getSimpleName()).d("print log with tag()"); 
    
    // 字符串工具，此方法为 判断字符串是否为空
    Tools.string().isEmpty(App.class.getSimpleName()); 
    
    // 网络工具类，此方法为　判断是否连接wifi
    Tools.network().isWifiConnected(this);  
    
## Screenshots [tobeupdate]

## 归类总结，方便大家查阅 [tobeupdate]

**你也可以通过**docs**进行查阅**.
