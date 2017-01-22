package com.mazouri.tools.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭IO工具
 *
 * Created by wangdongdong on 17-1-22.
 */

public final class CloseTool {

    private static final Object lock = new Object();
    private static volatile CloseTool instance;

    public static CloseTool instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new CloseTool();
                }
            }
        }
        return instance;
    }

    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 安静关闭IO
     *
     * @param closeables closeable
     */
    public void closeIOQuietly(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
