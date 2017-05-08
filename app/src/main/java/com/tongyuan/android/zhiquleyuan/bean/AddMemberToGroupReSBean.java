package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DTC on 2017/4/18.
 */
public class AddMemberToGroupReSBean implements Parcelable {

    /**
     * TYPE : RES
     * CMD : QRYUSER
     * ACCT : 13628327181
     * TIME : 20170418234547701
     * BODY : {"SEX":"男","NAME":"小苹果","ID":"201612291546141016532953","ACCT":"13628327181","STATE":"激活","ONLINE":"1","NICK":"大天才","IMG":"http://120
     * .27.41.179:8081/zqpland/resource/thumbnail/1/png/20170418/201704181616381016584932.png","BIRTHDAY":"20161229000000"}
     * VERI :
     * TOKEN : 6f3fd608-b4b4-4e62-a2be-aa45187ab708
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
        return "AddMemberToGroupReSBean{" +
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

    public AddMemberToGroupReSBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String
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
         * SEX : 男
         * NAME : 小苹果
         * ID : 201612291546141016532953
         * ACCT : 13628327181
         * STATE : 激活
         * ONLINE : 1
         * NICK : 大天才
         * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170418/201704181616381016584932.png
         * BIRTHDAY : 20161229000000
         */

        private String SEX;
        private String NAME;
        private String ID;
        private String ACCT;
        private String STATE;
        private String ONLINE;
        private String NICK;
        private String IMG;
        private String BIRTHDAY;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "SEX='" + SEX + '\'' +
                    ", NAME='" + NAME + '\'' +
                    ", ID='" + ID + '\'' +
                    ", ACCT='" + ACCT + '\'' +
                    ", STATE='" + STATE + '\'' +
                    ", ONLINE='" + ONLINE + '\'' +
                    ", NICK='" + NICK + '\'' +
                    ", IMG='" + IMG + '\'' +
                    ", BIRTHDAY='" + BIRTHDAY + '\'' +
                    '}';
        }

        public BODYBean(String SEX, String NAME, String ID, String ACCT, String STATE, String ONLINE, String NICK, String IMG, String BIRTHDAY) {
            this.SEX = SEX;
            this.NAME = NAME;
            this.ID = ID;
            this.ACCT = ACCT;
            this.STATE = STATE;
            this.ONLINE = ONLINE;
            this.NICK = NICK;
            this.IMG = IMG;
            this.BIRTHDAY = BIRTHDAY;
        }

        public String getSEX() {
            return SEX;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getACCT() {
            return ACCT;
        }

        public void setACCT(String ACCT) {
            this.ACCT = ACCT;
        }

        public String getSTATE() {
            return STATE;
        }

        public void setSTATE(String STATE) {
            this.STATE = STATE;
        }

        public String getONLINE() {
            return ONLINE;
        }

        public void setONLINE(String ONLINE) {
            this.ONLINE = ONLINE;
        }

        public String getNICK() {
            return NICK;
        }

        public void setNICK(String NICK) {
            this.NICK = NICK;
        }

        public String getIMG() {
            return IMG;
        }

        public void setIMG(String IMG) {
            this.IMG = IMG;
        }

        public String getBIRTHDAY() {
            return BIRTHDAY;
        }

        public void setBIRTHDAY(String BIRTHDAY) {
            this.BIRTHDAY = BIRTHDAY;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.SEX);
            dest.writeString(this.NAME);
            dest.writeString(this.ID);
            dest.writeString(this.ACCT);
            dest.writeString(this.STATE);
            dest.writeString(this.ONLINE);
            dest.writeString(this.NICK);
            dest.writeString(this.IMG);
            dest.writeString(this.BIRTHDAY);
        }

        protected BODYBean(Parcel in) {
            this.SEX = in.readString();
            this.NAME = in.readString();
            this.ID = in.readString();
            this.ACCT = in.readString();
            this.STATE = in.readString();
            this.ONLINE = in.readString();
            this.NICK = in.readString();
            this.IMG = in.readString();
            this.BIRTHDAY = in.readString();
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

    protected AddMemberToGroupReSBean(Parcel in) {
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

    public static final Parcelable.Creator<AddMemberToGroupReSBean> CREATOR = new Parcelable.Creator<AddMemberToGroupReSBean>() {
        @Override
        public AddMemberToGroupReSBean createFromParcel(Parcel source) {
            return new AddMemberToGroupReSBean(source);
        }

        @Override
        public AddMemberToGroupReSBean[] newArray(int size) {
            return new AddMemberToGroupReSBean[size];
        }
    };
}
