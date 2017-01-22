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
