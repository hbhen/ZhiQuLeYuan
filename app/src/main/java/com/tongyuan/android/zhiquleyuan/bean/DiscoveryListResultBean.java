package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2017/3/10.
 */

public class DiscoveryListResultBean implements Parcelable {

    /**
     * TYPE : RES
     * CMD : QRYREC
     * ACCT : 13628327181
     * TIME : 20170310142912090
     * BODY : {"LST":[],"PS":"10","NC":"0","CNT":"0","PN":"1"}
     * VERI :
     * TOKEN : adea76dd-4b9d-4dd0-81a7-c79b3bf94535
     * SEQ : 1
     * CODE : 0
     * MSG :
     */

    private String TYPE;
    private String CMD;
    private String ACCT;
    private String TIME;
    private BODYBean BODY;
    private String VERI;
    private String TOKEN;
    private String SEQ;
    private String CODE;
    private String MSG;

    @Override
    public String toString() {
        return "DiscoveryListResultBean{" +
                "TYPE='" + TYPE + '\'' +
                ", CMD='" + CMD + '\'' +
                ", ACCT='" + ACCT + '\'' +
                ", TIME='" + TIME + '\'' +
                ", BODY=" + BODY +
                ", VERI='" + VERI + '\'' +
                ", TOKEN='" + TOKEN + '\'' +
                ", SEQ='" + SEQ + '\'' +
                ", CODE='" + CODE + '\'' +
                ", MSG='" + MSG + '\'' +
                '}';
    }

    public DiscoveryListResultBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.BODY = BODY;
        this.VERI = VERI;
        this.TOKEN = TOKEN;
        this.SEQ = SEQ;
        this.CODE = CODE;
        this.MSG = MSG;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getCMD() {
        return CMD;
    }

    public void setCMD(String CMD) {
        this.CMD = CMD;
    }

    public String getACCT() {
        return ACCT;
    }

    public void setACCT(String ACCT) {
        this.ACCT = ACCT;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public BODYBean getBODY() {
        return BODY;
    }

    public void setBODY(BODYBean BODY) {
        this.BODY = BODY;
    }

    public String getVERI() {
        return VERI;
    }

    public void setVERI(String VERI) {
        this.VERI = VERI;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    public String getSEQ() {
        return SEQ;
    }

    public void setSEQ(String SEQ) {
        this.SEQ = SEQ;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public static class BODYBean implements Parcelable {
        @Override
        public String toString() {
            return "BODYBean{" +
                    "PS='" + PS + '\'' +
                    ", NC='" + NC + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", PN='" + PN + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PS, String NC, String CNT, String PN, List<?> LST) {
            this.PS = PS;
            this.NC = NC;
            this.CNT = CNT;
            this.PN = PN;
            this.LST = LST;
        }

        /**
         * LST : []
         * PS : 10
         * NC : 0
         * CNT : 0
         * PN : 1
         */

        private String PS;
        private String NC;
        private String CNT;
        private String PN;
        private List<?> LST;

        public String getPS() {
            return PS;
        }

        public void setPS(String PS) {
            this.PS = PS;
        }

        public String getNC() {
            return NC;
        }

        public void setNC(String NC) {
            this.NC = NC;
        }

        public String getCNT() {
            return CNT;
        }

        public void setCNT(String CNT) {
            this.CNT = CNT;
        }

        public String getPN() {
            return PN;
        }

        public void setPN(String PN) {
            this.PN = PN;
        }

        public List<?> getLST() {
            return LST;
        }

        public void setLST(List<?> LST) {
            this.LST = LST;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.PS);
            dest.writeString(this.NC);
            dest.writeString(this.CNT);
            dest.writeString(this.PN);
            dest.writeList(this.LST);
        }

        protected BODYBean(Parcel in) {
            this.PS = in.readString();
            this.NC = in.readString();
            this.CNT = in.readString();
            this.PN = in.readString();
            this.LST = new ArrayList();

        }

        public static final Creator<BODYBean> CREATOR = new Creator<BODYBean>() {
            @Override
            public BODYBean createFromParcel(Parcel source) {
                return new BODYBean(source);
            }

            @Override
            public BODYBean[] newArray(int size) {
                return new BODYBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TYPE);
        dest.writeString(this.CMD);
        dest.writeString(this.ACCT);
        dest.writeString(this.TIME);
        dest.writeParcelable(this.BODY, flags);
        dest.writeString(this.VERI);
        dest.writeString(this.TOKEN);
        dest.writeString(this.SEQ);
        dest.writeString(this.CODE);
        dest.writeString(this.MSG);
    }

    protected DiscoveryListResultBean(Parcel in) {
        this.TYPE = in.readString();
        this.CMD = in.readString();
        this.ACCT = in.readString();
        this.TIME = in.readString();
        this.BODY = in.readParcelable(BODYBean.class.getClassLoader());
        this.VERI = in.readString();
        this.TOKEN = in.readString();
        this.SEQ = in.readString();
        this.CODE = in.readString();
        this.MSG = in.readString();
    }

    public static final Parcelable.Creator<DiscoveryListResultBean> CREATOR = new Parcelable.Creator<DiscoveryListResultBean>() {
        @Override
        public DiscoveryListResultBean createFromParcel(Parcel source) {
            return new DiscoveryListResultBean(source);
        }

        @Override
        public DiscoveryListResultBean[] newArray(int size) {
            return new DiscoveryListResultBean[size];
        }
    };
}
