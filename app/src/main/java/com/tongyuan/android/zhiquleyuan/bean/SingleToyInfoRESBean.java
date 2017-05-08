package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DTC on 2017/3/22.
 */

public class SingleToyInfoRESBean implements Parcelable {
    public SingleToyInfoRESBean() {
    }

    @Override
    public String toString() {
        return "SingleToyInfoRESBean{" +
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

    public SingleToyInfoRESBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String
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

    /**
     * TYPE : RES
     * CMD : QTOY
     * ACCT : 13628327181
     * TIME : 20170322092250605
     * BODY : {"NAME":"小狗","ONLINE":"1","ID":"201611032024521016432810","STATE":"激活","ACTTIME":"20170206111215","CODE":"dog003","VOL":"42",
     * "IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","MIC":"2","NICK":"","ELEC":"27",
     * "OWNERNAME":"小苹果","WIFI":"-42","OWNERID":"201612291546141016532953","CAMERA":"2"}
     * VERI :
     * TOKEN : 3e398a7b-167b-4acd-a795-5b8036d9332b
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
                    "NAME='" + NAME + '\'' +
                    ", ONLINE='" + ONLINE + '\'' +
                    ", ID='" + ID + '\'' +
                    ", STATE='" + STATE + '\'' +
                    ", ACTTIME='" + ACTTIME + '\'' +
                    ", CODE='" + CODE + '\'' +
                    ", VOL='" + VOL + '\'' +
                    ", IMG='" + IMG + '\'' +
                    ", MIC='" + MIC + '\'' +
                    ", NICK='" + NICK + '\'' +
                    ", ELEC='" + ELEC + '\'' +
                    ", OWNERNAME='" + OWNERNAME + '\'' +
                    ", WIFI='" + WIFI + '\'' +
                    ", OWNERID='" + OWNERID + '\'' +
                    ", CAMERA='" + CAMERA + '\'' +
                    '}';
        }

        public BODYBean(String NAME, String ONLINE, String ID, String STATE, String ACTTIME, String CODE, String VOL, String IMG, String MIC,
                        String NICK, String ELEC, String OWNERNAME, String WIFI, String OWNERID, String CAMERA) {
            this.NAME = NAME;
            this.ONLINE = ONLINE;
            this.ID = ID;
            this.STATE = STATE;
            this.ACTTIME = ACTTIME;
            this.CODE = CODE;
            this.VOL = VOL;
            this.IMG = IMG;
            this.MIC = MIC;
            this.NICK = NICK;
            this.ELEC = ELEC;
            this.OWNERNAME = OWNERNAME;
            this.WIFI = WIFI;
            this.OWNERID = OWNERID;
            this.CAMERA = CAMERA;
        }

        /**
         * NAME : 小狗
         * ONLINE : 1
         * ID : 201611032024521016432810
         * STATE : 激活
         * ACTTIME : 20170206111215
         * CODE : dog003
         * VOL : 42
         * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg
         * MIC : 2
         * NICK :
         * ELEC : 27
         * OWNERNAME : 小苹果
         * WIFI : -42
         * OWNERID : 201612291546141016532953
         * CAMERA : 2
         */

        private String NAME;
        private String ONLINE;
        private String ID;
        private String STATE;
        private String ACTTIME;
        private String CODE;
        private String VOL;
        private String IMG;
        private String MIC;
        private String NICK;
        private String ELEC;
        private String OWNERNAME;
        private String WIFI;
        private String OWNERID;
        private String CAMERA;

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getONLINE() {
            return ONLINE;
        }

        public void setONLINE(String ONLINE) {
            this.ONLINE = ONLINE;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSTATE() {
            return STATE;
        }

        public void setSTATE(String STATE) {
            this.STATE = STATE;
        }

        public String getACTTIME() {
            return ACTTIME;
        }

        public void setACTTIME(String ACTTIME) {
            this.ACTTIME = ACTTIME;
        }

        public String getCODE() {
            return CODE;
        }

        public void setCODE(String CODE) {
            this.CODE = CODE;
        }

        public String getVOL() {
            return VOL;
        }

        public void setVOL(String VOL) {
            this.VOL = VOL;
        }

        public String getIMG() {
            return IMG;
        }

        public void setIMG(String IMG) {
            this.IMG = IMG;
        }

        public String getMIC() {
            return MIC;
        }

        public void setMIC(String MIC) {
            this.MIC = MIC;
        }

        public String getNICK() {
            return NICK;
        }

        public void setNICK(String NICK) {
            this.NICK = NICK;
        }

        public String getELEC() {
            return ELEC;
        }

        public void setELEC(String ELEC) {
            this.ELEC = ELEC;
        }

        public String getOWNERNAME() {
            return OWNERNAME;
        }

        public void setOWNERNAME(String OWNERNAME) {
            this.OWNERNAME = OWNERNAME;
        }

        public String getWIFI() {
            return WIFI;
        }

        public void setWIFI(String WIFI) {
            this.WIFI = WIFI;
        }

        public String getOWNERID() {
            return OWNERID;
        }

        public void setOWNERID(String OWNERID) {
            this.OWNERID = OWNERID;
        }

        public String getCAMERA() {
            return CAMERA;
        }

        public void setCAMERA(String CAMERA) {
            this.CAMERA = CAMERA;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.NAME);
            dest.writeString(this.ONLINE);
            dest.writeString(this.ID);
            dest.writeString(this.STATE);
            dest.writeString(this.ACTTIME);
            dest.writeString(this.CODE);
            dest.writeString(this.VOL);
            dest.writeString(this.IMG);
            dest.writeString(this.MIC);
            dest.writeString(this.NICK);
            dest.writeString(this.ELEC);
            dest.writeString(this.OWNERNAME);
            dest.writeString(this.WIFI);
            dest.writeString(this.OWNERID);
            dest.writeString(this.CAMERA);
        }

        protected BODYBean(Parcel in) {
            this.NAME = in.readString();
            this.ONLINE = in.readString();
            this.ID = in.readString();
            this.STATE = in.readString();
            this.ACTTIME = in.readString();
            this.CODE = in.readString();
            this.VOL = in.readString();
            this.IMG = in.readString();
            this.MIC = in.readString();
            this.NICK = in.readString();
            this.ELEC = in.readString();
            this.OWNERNAME = in.readString();
            this.WIFI = in.readString();
            this.OWNERID = in.readString();
            this.CAMERA = in.readString();
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

    protected SingleToyInfoRESBean(Parcel in) {
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

    public static final Parcelable.Creator<SingleToyInfoRESBean> CREATOR = new Parcelable.Creator<SingleToyInfoRESBean>() {
        @Override
        public SingleToyInfoRESBean createFromParcel(Parcel source) {
            return new SingleToyInfoRESBean(source);
        }

        @Override
        public SingleToyInfoRESBean[] newArray(int size) {
            return new SingleToyInfoRESBean[size];
        }
    };
}
