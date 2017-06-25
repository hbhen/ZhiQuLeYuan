package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by DTC on 2017/3/24.
 */
public class DiscoveryGridSecondaryResultBean implements Serializable{

    public String PN;
    public String CNT;
    public String PS;
    public String NC;
    public List<DiscoveryListResultBean.BODYBean.LSTBean> LST;

    /*public static class LSTBean implements Parcelable {

        public String TYPE;
        public String NAME;
        public String IMG;
        public String ID;
        public String COLNAME;
        public String SIZE;
        public String DUR;
        public String COLID;
        public String REMARK;
        public String TIMES;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.TYPE);
            dest.writeString(this.NAME);
            dest.writeString(this.IMG);
            dest.writeString(this.ID);
            dest.writeString(this.COLNAME);
            dest.writeString(this.SIZE);
            dest.writeString(this.DUR);
            dest.writeString(this.COLID);
            dest.writeString(this.REMARK);
            dest.writeString(this.TIMES);
        }

        protected LSTBean(Parcel in) {
            this.TYPE = in.readString();
            this.NAME = in.readString();
            this.IMG = in.readString();
            this.ID = in.readString();
            this.COLNAME = in.readString();
            this.SIZE = in.readString();
            this.DUR = in.readString();
            this.COLID = in.readString();
            this.REMARK = in.readString();
            this.TIMES = in.readString();
        }

        public static final Creator<DiscoveryGridSecondaryResultBean.LSTBean> CREATOR = new Creator<DiscoveryGridSecondaryResultBean.LSTBean>() {
            @Override
            public DiscoveryGridSecondaryResultBean.LSTBean createFromParcel(Parcel source) {
                return new DiscoveryGridSecondaryResultBean.LSTBean(source);
            }

            @Override
            public DiscoveryGridSecondaryResultBean.LSTBean[] newArray(int size) {
                return new DiscoveryGridSecondaryResultBean.LSTBean[size];
            }
        };
    }*/
}
