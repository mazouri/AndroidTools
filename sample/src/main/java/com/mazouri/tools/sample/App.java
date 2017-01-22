package com.mazouri.tools.sample;

import android.app.Application;

import com.mazouri.tools.Tools;

/**
 * Created by wangdongdong on 17-1-20.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Tools.init(this); //[Required]
        Tools.openToolsLog(true); //[Optional] if u wanna see AndroidTools internal logs, add this

        Tools.log().d("print log with default generated tag");
        Tools.log().tag(App.class.getSimpleName()).d("print log with tag()");
        Tools.log().d(App.class.getSimpleName(), "print log with set tag");

        Tools.string().isEmpty(App.class.getSimpleName());
        Tools.network().isWifiConnected(this);
    }
}
