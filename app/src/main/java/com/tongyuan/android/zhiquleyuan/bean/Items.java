package com.tongyuan.android.zhiquleyuan.bean;

//给不同的布局设置字段

/**
 * discoverylistview的两种布局字段
 */

/**
 * Created by android on 2017/3/10.
 */
public class Items {
    private String item1_str;
    private String item2_str;

    @Override
    public String toString() {
        return "Items{" +
                "item1_str='" + item1_str + '\'' +
                ", item2_str='" + item2_str + '\'' +
                '}';
    }

    public Items(String item1_str, String item2_str) {
        this.item1_str = item1_str;
        this.item2_str = item2_str;
    }

    public String getItem1_str() {

        return item1_str;
    }

    public void setItem1_str(String item1_str) {
        this.item1_str = item1_str;
    }

    public String getItem2_str() {
        return item2_str;
    }

    public void setItem2_str(String item2_str) {
        this.item2_str = item2_str;
    }
}
