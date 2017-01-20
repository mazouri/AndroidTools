package com.mazouri.tools.app;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘工具类
 *
 * Created by wangdongdong on 17-1-20.
 */

public final class InputMethodTool {

    private static final Object lock = new Object();
    private static volatile InputMethodTool instance;

    private InputMethodTool() {
    }

    public static InputMethodTool instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new InputMethodTool();
                }
            }
        }
        return instance;
    }

    /**
     * 隐藏键盘
     * ：强制隐藏
     * @param context
     */
    public void hideInputSoftFromWindowMethod(Context context, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示输入法
     * @param context
     * @param view
     */
    public void showInputSoftFromWindowMethod(Context context, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断输入负是否处于激活状态
     * @param context
     * @return
     */
    public boolean isActiveSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }
}
