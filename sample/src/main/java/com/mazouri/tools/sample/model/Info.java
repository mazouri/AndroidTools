package com.mazouri.tools.sample.model;

/**
 * Created by wangdongdong on 17-2-7.
 */

public class Info {
    private String title;
    private int resourceId;

    public Info(String title, int resourceId) {
        this.title = title;
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
