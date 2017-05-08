package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by android on 2017/3/9.
 */

public class QueryToyResultBean implements Parcelable{

    /**
     * TYPE : RES
     * CMD : QRYTOYS
     * ACCT : 13628327181
     * TIME : 20170309133346113
     * BODY : {"PN":"1","NC":"0","CNT":"1","LST":[{"BABYIMG":"","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","ID":"201612271518281016503209","VOL":"","CODE":"dog001","OWNERNAME":"小苹果","NICK":"","STATE":"激活","NAME":"小狗","ELEC":"","ACTTIME":"20170220141353","ONLINE":"0","OWNERID":"201612291546141016532953"}],"PS":"10"}
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

    protected QueryToyResultBean(Parcel in) {
        TYPE = in.readString();
        CMD = in.readString();
        ACCT = in.readString();
        TIME = in.readString();
        VERI = in.readString();
        TOKEN = in.readString();
        SEQ = in.readString();
        CODE = in.readString();
        MSG = in.readString();
    }

    public static final Creator<QueryToyResultBean> CREATOR = new Creator<QueryToyResultBean>() {
        @Override
        public QueryToyResultBean createFromParcel(Parcel in) {
            return new QueryToyResultBean(in);
        }

        @Override
        public QueryToyResultBean[] newArray(int size) {
            return new QueryToyResultBean[size];
        }
    };

    @Override
    public String toString() {
        return "QueryToyResultBean{" +
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

    public QueryToyResultBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(BODY);
    }

    public static class BODYBean {
        @Override
        public String toString() {
            return "BODYBean{" +
                    "PN='" + PN + '\'' +
                    ", NC='" + NC + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", PS='" + PS + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PN, String NC, String CNT, String PS, List<LSTBean> LST) {
            this.PN = PN;
            this.NC = NC;
            this.CNT = CNT;
            this.PS = PS;
            this.LST = LST;
        }

        /**
         * PN : 1
         * NC : 0
         * CNT : 1
         * LST : [{"BABYIMG":"","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","ID":"201612271518281016503209","VOL":"","CODE":"dog001","OWNERNAME":"小苹果","NICK":"","STATE":"激活","NAME":"小狗","ELEC":"","ACTTIME":"20170220141353","ONLINE":"0","OWNERID":"201612291546141016532953"}]
         * PS : 10
         */

        private String PN;
        private String NC;
        private String CNT;
        private String PS;
        private List<LSTBean> LST;

        public String getPN() {
            return PN;
        }

        public void setPN(String PN) {
            this.PN = PN;
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

        public String getPS() {
            return PS;
        }

        public void setPS(String PS) {
            this.PS = PS;
        }

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean implements Parcelable{
            protected LSTBean(Parcel in) {
                BABYIMG = in.readString();
                IMG = in.readString();
                ID = in.readString();
                VOL = in.readString();
                CODE = in.readString();
                OWNERNAME = in.readString();
                NICK = in.readString();
                STATE = in.readString();
                NAME = in.readString();
                ELEC = in.readString();
                ACTTIME = in.readString();
                ONLINE = in.readString();
                OWNERID = in.readString();
            }

            public static final Creator<LSTBean> CREATOR = new Creator<LSTBean>() {
                @Override
                public LSTBean createFromParcel(Parcel in) {
                    return new LSTBean(in);
                }

                @Override
                public LSTBean[] newArray(int size) {
                    return new LSTBean[size];
                }
            };

            @Override
            public String toString() {
                return "LSTBean{" +
                        "BABYIMG='" + BABYIMG + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", ID='" + ID + '\'' +
                        ", VOL='" + VOL + '\'' +
                        ", CODE='" + CODE + '\'' +
                        ", OWNERNAME='" + OWNERNAME + '\'' +
                        ", NICK='" + NICK + '\'' +
                        ", STATE='" + STATE + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", ELEC='" + ELEC + '\'' +
                        ", ACTTIME='" + ACTTIME + '\'' +
                        ", ONLINE='" + ONLINE + '\'' +
                        ", OWNERID='" + OWNERID + '\'' +
                        '}';
            }

            public LSTBean() {
                super();
            }

            public LSTBean(String BABYIMG, String IMG, String ID, String VOL, String CODE, String OWNERNAME, String NICK, String STATE, String NAME, String ELEC, String ACTTIME, String ONLINE, String OWNERID) {
                this.BABYIMG = BABYIMG;
                this.IMG = IMG;
                this.ID = ID;
                this.VOL = VOL;
                this.CODE = CODE;
                this.OWNERNAME = OWNERNAME;
                this.NICK = NICK;
                this.STATE = STATE;
                this.NAME = NAME;
                this.ELEC = ELEC;
                this.ACTTIME = ACTTIME;
                this.ONLINE = ONLINE;
                this.OWNERID = OWNERID;
            }

            /**
             * BABYIMG :
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg
             * ID : 201612271518281016503209
             * VOL :
             * CODE : dog001
             * OWNERNAME : 小苹果
             * NICK :
             * STATE : 激活
             * NAME : 小狗
             * ELEC :
             * ACTTIME : 20170220141353
             * ONLINE : 0
             * OWNERID : 201612291546141016532953
             */

            private String BABYIMG;
            private String IMG;
            private String ID;
            private String VOL;
            private String CODE;
            private String OWNERNAME;
            private String NICK;
            private String STATE;
            private String NAME;
            private String ELEC;
            private String ACTTIME;
            private String ONLINE;
            private String OWNERID;

            public String getBABYIMG() {
                return BABYIMG;
            }

            public void setBABYIMG(String BABYIMG) {
                this.BABYIMG = BABYIMG;
            }

            public String getIMG() {
                return IMG;
            }

            public void setIMG(String IMG) {
                this.IMG = IMG;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getVOL() {
                return VOL;
            }

            public void setVOL(String VOL) {
                this.VOL = VOL;
            }

            public String getCODE() {
                return CODE;
            }

            public void setCODE(String CODE) {
                this.CODE = CODE;
            }

            public String getOWNERNAME() {
                return OWNERNAME;
            }

            public void setOWNERNAME(String OWNERNAME) {
                this.OWNERNAME = OWNERNAME;
            }

            public String getNICK() {
                return NICK;
            }

            public void setNICK(String NICK) {
                this.NICK = NICK;
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

            public String getELEC() {
                return ELEC;
            }

            public void setELEC(String ELEC) {
                this.ELEC = ELEC;
            }

            public String getACTTIME() {
                return ACTTIME;
            }

            public void setACTTIME(String ACTTIME) {
                this.ACTTIME = ACTTIME;
            }

            public String getONLINE() {
                return ONLINE;
            }

            public void setONLINE(String ONLINE) {
                this.ONLINE = ONLINE;
            }

            public String getOWNERID() {
                return OWNERID;
            }

            public void setOWNERID(String OWNERID) {
                this.OWNERID = OWNERID;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(BABYIMG);
                dest.writeString(IMG);
                dest.writeString(ID);
                dest.writeString(VOL);
                dest.writeString(CODE);
                dest.writeString(OWNERNAME);
                dest.writeString(NICK);
                dest.writeString(STATE);
                dest.writeString(NAME);
                dest.writeString(ELEC);
                dest.writeString(ACTTIME);
                dest.writeString(ONLINE);
                dest.writeString(OWNERID);
            }
        }
    }
}