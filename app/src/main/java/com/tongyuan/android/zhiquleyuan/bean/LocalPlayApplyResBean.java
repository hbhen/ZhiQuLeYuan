package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DTC on 2017/5/2.
 */

public class LocalPlayApplyResBean implements Parcelable {

    /**
     * URL : http://120.27.41.179:8081/zqpland/resource/file/mp3/201701131710501016547126/20170420/201704201921411016585030
     * .mp3?term=U&termId=201612291546141016532953&resId=201701131710501016547126&playId=201705021341151016642956&playType=R
     * DUR :
     */

    private String URL;
    private String DUR;

    @Override
    public String toString() {
        return "BODYBean{" +
                "URL='" + URL + '\'' +
                ", DUR='" + DUR + '\'' +
                '}';
    }

    public LocalPlayApplyResBean(String URL, String DUR) {
        this.URL = URL;
        this.DUR = DUR;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDUR() {
        return DUR;
    }

    public void setDUR(String DUR) {
        this.DUR = DUR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.URL);
        dest.writeString(this.DUR);
    }

    protected LocalPlayApplyResBean(Parcel in) {
        this.URL = in.readString();
        this.DUR = in.readString();
    }

    public static final Creator<LocalPlayApplyResBean> CREATOR = new Creator<LocalPlayApplyResBean>() {
        @Override
        public LocalPlayApplyResBean createFromParcel(Parcel source) {
            return new LocalPlayApplyResBean(source);
        }

        @Override
        public LocalPlayApplyResBean[] newArray(int size) {
            return new LocalPlayApplyResBean[size];
        }
    };
}

