package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by zgg on 2017/7/27.
 */

public class MusicPlayerBean implements Parcelable {

    public String ID;
    public String NAME;
    public String IMG;
    public boolean isPlaying = false;

    public String getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getIMG() {
        return IMG;
    }

    public MusicPlayerBean(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(NAME);
        dest.writeString(IMG);
    }

    public MusicPlayerBean(Parcel in) {
        ID = in.readString();
        NAME = in.readString();
        IMG = in.readString();
    }

}
