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

        AndroidTools.log().d("print log with default generated tag");
        AndroidTools.log().tag(App.class.getSimpleName()).d("print log with tag()");
        AndroidTools.log().d(App.class.getSimpleName(), "print log with set tag");
    }
}
