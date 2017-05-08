package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/4/10.
 */

public class QuerySingleUserInfoReSBean {
    /**
     * TYPE : RES
     * CMD : QRYUSER
     * ACCT : 13628327181
     * TIME : 20170410153849513
     * BODY : {"ACCT":"13628327181","STATE":"激活","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","BIRTHDAY":"20161229000000","NICK":"大天才","NAME":"小苹果",
     * "ONLINE":"1","SEX":"男","ID":"201612291546141016532953"}
     * VERI :
     * TOKEN : 236975d9-4a0c-496c-af23-19f213c48633
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
        return "QuerySingleUserInfoReSBean{" +
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

    public QuerySingleUserInfoReSBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ,
                                      String CODE, String MSG) {
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

    public static class BODYBean {
        /**
         * ACCT : 13628327181
         * STATE : 激活
         * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png
         * BIRTHDAY : 20161229000000
         * NICK : 大天才
         * NAME : 小苹果
         * ONLINE : 1
         * SEX : 男
         * ID : 201612291546141016532953
         */

        private String ACCT;
        private String STATE;
        private String IMG;
        private String BIRTHDAY;
        private String NICK;
        private String NAME;
        private String ONLINE;
        private String SEX;
        private String ID;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "ACCT='" + ACCT + '\'' +
                    ", STATE='" + STATE + '\'' +
                    ", IMG='" + IMG + '\'' +
                    ", BIRTHDAY='" + BIRTHDAY + '\'' +
                    ", NICK='" + NICK + '\'' +
                    ", NAME='" + NAME + '\'' +
                    ", ONLINE='" + ONLINE + '\'' +
                    ", SEX='" + SEX + '\'' +
                    ", ID='" + ID + '\'' +
                    '}';
        }

        public BODYBean(String ACCT, String STATE, String IMG, String BIRTHDAY, String NICK, String NAME, String ONLINE, String SEX, String ID) {
            this.ACCT = ACCT;
            this.STATE = STATE;
            this.IMG = IMG;
            this.BIRTHDAY = BIRTHDAY;
            this.NICK = NICK;
            this.NAME = NAME;
            this.ONLINE = ONLINE;
            this.SEX = SEX;
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

        public String getNICK() {
            return NICK;
        }

        public void setNICK(String NICK) {
            this.NICK = NICK;
        }

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

        public String getSEX() {
            return SEX;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }
}
