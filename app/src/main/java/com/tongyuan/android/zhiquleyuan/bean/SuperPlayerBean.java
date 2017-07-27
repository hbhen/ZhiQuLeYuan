package com.tongyuan.android.zhiquleyuan.bean;

import java.util.ArrayList;

/**
 * Created by Android on 2017/7/27.
 */

public class SuperPlayerBean {
    private ArrayList mList;
    private String id;
    private String name;
    private String img;

    private ArrayList getList() {
        return mList;
    }

    private void setList() {
        this.mList = mList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "SuperPlayerBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }


}
