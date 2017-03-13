package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/3/12.
 */

public class AlumItems {
    private String item_type_one;
    private String item_type_two;

    public String getItem_type_one() {
        return item_type_one;
    }

    public void setItem_type_one(String item_type_one) {
        this.item_type_one = item_type_one;
    }

    public String getItem_type_two() {
        return item_type_two;
    }

    public void setItem_type_two(String item_type_two) {
        this.item_type_two = item_type_two;
    }

    @Override

    public String toString() {
        return "AlumItems{" +
                "item_type_one='" + item_type_one + '\'' +
                ", item_type_two='" + item_type_two + '\'' +
                '}';
    }

    public AlumItems(String item_type_one, String item_type_two) {
        this.item_type_one = item_type_one;
        this.item_type_two = item_type_two;
    }
}
