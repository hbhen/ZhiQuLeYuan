package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DTC on 2017/4/1.
 */

public class QueryToyMemberReSBean implements Parcelable {
    /**
     * TYPE : RES
     * CMD : QTOYU
     * ACCT : 13628327181
     * TIME : 20170401104057289
     * BODY : {"LST":[{"IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","SEX":"男",
     * "STATE":"激活","NAME":"小苹果","ID":"201612291546141016532953","ACCT":"13628327181","BIRTHDAY":"20161229000000","NICK":"大天才","ONLINE":"1"}],
     * "NC":"0","PS":"10","CNT":"1","PN":"1"}
     * VERI :
     * TOKEN : baaaf110-2f0e-425f-b39f-936619d54019
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
        return "QueryToyMemberReSBean{" +
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

    public QueryToyMemberReSBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String
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
         * LST : [{"IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","SEX":"男","STATE":"激活",
         * "NAME":"小苹果","ID":"201612291546141016532953","ACCT":"13628327181","BIRTHDAY":"20161229000000","NICK":"大天才","ONLINE":"1"}]
         * NC : 0
         * PS : 10
         * CNT : 1
         * PN : 1
         */

        private String NC;
        private String PS;
        private String CNT;
        private String PN;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "NC='" + NC + '\'' +
                    ", PS='" + PS + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", PN='" + PN + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String NC, String PS, String CNT, String PN, List<LSTBean> LST) {
            this.NC = NC;
            this.PS = PS;
            this.CNT = CNT;
            this.PN = PN;
            this.LST = LST;
        }

        public String getNC() {
            return NC;
        }

        public void setNC(String NC) {
            this.NC = NC;
        }

        public String getPS() {
            return PS;
        }

        public void setPS(String PS) {
            this.PS = PS;
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

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean implements Parcelable {
            /**
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png
             * SEX : 男
             * STATE : 激活
             * NAME : 小苹果
             * ID : 201612291546141016532953
             * ACCT : 13628327181
             * BIRTHDAY : 20161229000000
             * NICK : 大天才
             * ONLINE : 1
             */

            private String IMG;
            private String SEX;
            private String STATE;
            private String NAME;
            private String ID;
            private String ACCT;
            private String BIRTHDAY;
            private String NICK;
            private String ONLINE;
            private boolean isSHOWADD=false;
            private boolean isSHOWADEL=false;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "IMG='" + IMG + '\'' +
                        ", SEX='" + SEX + '\'' +
                        ", STATE='" + STATE + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", ID='" + ID + '\'' +
                        ", ACCT='" + ACCT + '\'' +
                        ", BIRTHDAY='" + BIRTHDAY + '\'' +
                        ", NICK='" + NICK + '\'' +
                        ", ONLINE='" + ONLINE + '\'' +
                        '}';
            }

            public LSTBean(String IMG, String SEX, String STATE, String NAME, String ID, String ACCT, String BIRTHDAY, String NICK, String ONLINE,
                           boolean SHOWADDPIC,boolean SHOWADEL) {
                this.IMG = IMG;
                this.SEX = SEX;
                this.STATE = STATE;
                this.NAME = NAME;
                this.ID = ID;
                this.ACCT = ACCT;
                this.BIRTHDAY = BIRTHDAY;
                this.NICK = NICK;
                this.ONLINE = ONLINE;
                this.isSHOWADD=SHOWADDPIC;
                this.isSHOWADEL=SHOWADEL;
            }

            public String getIMG() {
                return IMG;
            }

            public void setIMG(String IMG) {
                this.IMG = IMG;
            }

            public String getSEX() {
                return SEX;
            }

            public void setSEX(String SEX) {
                this.SEX = SEX;
            }

            public String getSTATE() {
                return STATE;
            }

            public void setSTATE(String STATE) {
                this.STATE = STATE;
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

            public String getBIRTHDAY() {
                return BIRTHDAY;
            }

            public void setBIRTHDAY(String BIRTHDAY) {
                this.BIRTHDAY = BIRTHDAY;
            }

            public String getNICK() {
                return NICK;
            }

            public void setNICK(String NICK) {
                this.NICK = NICK;
            }

            public String getONLINE() {
                return ONLINE;
            }

            public void setONLINE(String ONLINE) {
                this.ONLINE = ONLINE;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.IMG);
                dest.writeString(this.SEX);
                dest.writeString(this.STATE);
                dest.writeString(this.NAME);
                dest.writeString(this.ID);
                dest.writeString(this.ACCT);
                dest.writeString(this.BIRTHDAY);
                dest.writeString(this.NICK);
                dest.writeString(this.ONLINE);
                dest.writeByte(this.isSHOWADD ? (byte) 1 : (byte) 0);
                dest.writeByte(this.isSHOWADEL ? (byte) 1 : (byte) 0);
            }

            protected LSTBean(Parcel in) {
                this.IMG = in.readString();
                this.SEX = in.readString();
                this.STATE = in.readString();
                this.NAME = in.readString();
                this.ID = in.readString();
                this.ACCT = in.readString();
                this.BIRTHDAY = in.readString();
                this.NICK = in.readString();
                this.ONLINE = in.readString();
                this.isSHOWADD = in.readByte() != 0;
                this.isSHOWADEL = in.readByte() != 0;
            }

            public static final Creator<LSTBean> CREATOR = new Creator<LSTBean>() {
                @Override
                public LSTBean createFromParcel(Parcel source) {
                    return new LSTBean(source);
                }

                @Override
                public LSTBean[] newArray(int size) {
                    return new LSTBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.NC);
            dest.writeString(this.PS);
            dest.writeString(this.CNT);
            dest.writeString(this.PN);
            dest.writeList(this.LST);
        }

        protected BODYBean(Parcel in) {
            this.NC = in.readString();
            this.PS = in.readString();
            this.CNT = in.readString();
            this.PN = in.readString();
            this.LST = new ArrayList<LSTBean>();
            in.readList(this.LST, LSTBean.class.getClassLoader());
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

    protected QueryToyMemberReSBean(Parcel in) {
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

    public static final Parcelable.Creator<QueryToyMemberReSBean> CREATOR = new Parcelable.Creator<QueryToyMemberReSBean>() {
        @Override
        public QueryToyMemberReSBean createFromParcel(Parcel source) {
            return new QueryToyMemberReSBean(source);
        }

        @Override
        public QueryToyMemberReSBean[] newArray(int size) {
            return new QueryToyMemberReSBean[size];
        }
    };
}
