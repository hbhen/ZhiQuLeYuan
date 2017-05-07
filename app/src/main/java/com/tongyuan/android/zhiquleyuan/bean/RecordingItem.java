package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DTC on 2017/4/25.
 */

public class RecordingItem implements Parcelable {
    private String mName; // file name
    private String mFilePath; //file path
    private int mId; //id in database
    private int mLength; // length of recording in seconds
    private long mTime;
    private int mTimes;
    public RecordingItem(){}

    public RecordingItem(String name, String filePath, int id, int length, long time, int times) {
        mName = name;
        mFilePath = filePath;
        mId = id;
        mLength = length;
        mTime = time;
        mTimes = times;
    }

    @Override
    public String toString() {
        return "RecordingItem{" +
                "mName='" + mName + '\'' +
                ", mFilePath='" + mFilePath + '\'' +
                ", mId=" + mId +
                ", mLength=" + mLength +
                ", mTime=" + mTime +
                ", mTimes=" + mTimes +
                '}';
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public void setFilePath(String filePath) {
        mFilePath = filePath;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int length) {
        mLength = length;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public int getTimes() {
        return mTimes;
    }

    public void setTimes(int times) {
        mTimes = times;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mFilePath);
        dest.writeInt(this.mId);
        dest.writeInt(this.mLength);
        dest.writeLong(this.mTime);
        dest.writeInt(this.mTimes);
    }

    protected RecordingItem(Parcel in) {
        this.mName = in.readString();
        this.mFilePath = in.readString();
        this.mId = in.readInt();
        this.mLength = in.readInt();
        this.mTime = in.readLong();
        this.mTimes = in.readInt();
    }

    public static final Parcelable.Creator<RecordingItem> CREATOR = new Parcelable.Creator<RecordingItem>() {
        @Override
        public RecordingItem createFromParcel(Parcel source) {
            return new RecordingItem(source);
        }

        @Override
        public RecordingItem[] newArray(int size) {
            return new RecordingItem[size];
        }
    };
}
