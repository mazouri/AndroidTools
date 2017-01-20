package com.mazouri.tools.sample;

import android.app.Application;

import com.mazouri.tools.AndroidTools;

/**
 * Created by wangdongdong on 17-1-20.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidTools.init(this);
        AndroidTools.openToolsLog(true);
        AndroidTools.log().d("use Android log tool");
    }
}
