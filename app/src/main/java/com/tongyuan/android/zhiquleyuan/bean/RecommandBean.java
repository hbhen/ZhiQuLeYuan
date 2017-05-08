package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DTC on 2017/3/23.
 */
public class RecommandBean implements Parcelable {
    private String name;
    private String time;
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static Creator<RecommandBean> getCREATOR() {
        return CREATOR;
    }

    public RecommandBean(String name, String time, String img) {

        this.name = name;
        this.time = time;
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.time);
        dest.writeString(this.img);
    }

    public RecommandBean() {
    }

    protected RecommandBean(Parcel in) {
        this.name = in.readString();
        this.time = in.readString();
        this.img = in.readString();
    }

    public static final Parcelable.Creator<RecommandBean> CREATOR = new Parcelable.Creator<RecommandBean>() {
        @Override
        public RecommandBean createFromParcel(Parcel source) {
            return new RecommandBean(source);
        }

        @Override
        public RecommandBean[] newArray(int size) {
            return new RecommandBean[size];
        }
    };
}
