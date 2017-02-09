package com.mazouri.tools.sample.model;

import java.util.List;

/**
 * Created by wangdongdong on 17-2-7.
 */

public class Category {
    private String category;
    List<Info> infos;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }
}
