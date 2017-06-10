package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DTC on 2017/5/2.
 */

public class LocalPlayApplyResBean implements Parcelable {

    /**
     * TYPE : RES
     * CMD : PLAY
     * ACCT : 13628327181
     * TIME : 20170502134115388
     * BODY : {"URL":"http://120.27.41.179:8081/zqpland/resource/file/mp3/201701131710501016547126/20170420/201704201921411016585030
     * .mp3?term=U&termId=201612291546141016532953&resId=201701131710501016547126&playId=201705021341151016642956&playType=R","DUR":""}
     * VERI :
     * TOKEN : 7428bc8f-43ea-4bb7-8e2b-ac305ce382ed
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
        return "LocalPlayApplyResBean{" +
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

    public LocalPlayApplyResBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String
            CODE, String MSG) {
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

        public BODYBean(String URL, String DUR) {
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

        protected BODYBean(Parcel in) {
            this.URL = in.readString();
            this.DUR = in.readString();
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

    protected LocalPlayApplyResBean(Parcel in) {
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

    public static final Parcelable.Creator<LocalPlayApplyResBean> CREATOR = new Parcelable.Creator<LocalPlayApplyResBean>() {
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
