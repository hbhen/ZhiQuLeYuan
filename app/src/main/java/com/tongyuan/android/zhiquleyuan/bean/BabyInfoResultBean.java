package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/3/2.
 */
//添加,修改宝宝的信息
public class BabyInfoResultBean {
    @Override
    public String toString() {
        return "BabyInfoResultBean{" +
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

    public BabyInfoResultBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
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
     * CMD : INFO
     * ACCT : 13628327181
     * TIME : 20170302223625653
     * BODY : {"NICK":"","BIRTHDAY":"20170101000000","TYPE":"USER","NAME":"大头","STATE":"激活","ONLINE":"1","SEX":"男","IMG":"","ID":"201703022235071016583037"}
     * VERI :
     * TOKEN : 23ee52b2-b232-4e83-bfdb-32764d26b794
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

    public static class BODYBean {
        @Override
        public String toString() {
            return "BODYBean{" +
                    "NICK='" + NICK + '\'' +
                    ", BIRTHDAY='" + BIRTHDAY + '\'' +
                    ", TYPE='" + TYPE + '\'' +
                    ", NAME='" + NAME + '\'' +
                    ", STATE='" + STATE + '\'' +
                    ", ONLINE='" + ONLINE + '\'' +
                    ", SEX='" + SEX + '\'' +
                    ", IMG='" + IMG + '\'' +
                    ", ID='" + ID + '\'' +
                    '}';
        }

        public BODYBean(String NICK, String BIRTHDAY, String TYPE, String NAME, String STATE, String ONLINE, String SEX, String IMG, String ID) {
            this.NICK = NICK;
            this.BIRTHDAY = BIRTHDAY;
            this.TYPE = TYPE;
            this.NAME = NAME;
            this.STATE = STATE;
            this.ONLINE = ONLINE;
            this.SEX = SEX;
            this.IMG = IMG;
            this.ID = ID;
        }

        /**
         * NICK :
         * BIRTHDAY : 20170101000000
         * TYPE : USER
         * NAME : 大头
         * STATE : 激活
         * ONLINE : 1
         * SEX : 男
         * IMG :
         * ID : 201703022235071016583037
         */

        private String NICK;
        private String BIRTHDAY;
        private String TYPE;
        private String NAME;
        private String STATE;
        private String ONLINE;
        private String SEX;
        private String IMG;
        private String ID;

        public String getNICK() {
            return NICK;
        }

        public void setNICK(String NICK) {
            this.NICK = NICK;
        }

        public String getBIRTHDAY() {
            return BIRTHDAY;
        }

        public void setBIRTHDAY(String BIRTHDAY) {
            this.BIRTHDAY = BIRTHDAY;
        }

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
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

        public String getSEX() {
            return SEX;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
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
    }
}
