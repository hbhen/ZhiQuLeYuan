package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/3/2.
 */
public class BabyInfoRequestBean {
    @Override
    public String toString() {
        return "BabyInfoRequestBean{" +
                "TYPE='" + TYPE + '\'' +
                ", CMD='" + CMD + '\'' +
                ", ACCT='" + ACCT + '\'' +
                ", TIME='" + TIME + '\'' +
                ", BODY=" + BODY +
                ", VERI='" + VERI + '\'' +
                ", TOKEN='" + TOKEN + '\'' +
                ", SEQ='" + SEQ + '\'' +
                '}';
    }

    public BabyInfoRequestBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.BODY = BODY;
        this.VERI = VERI;
        this.TOKEN = TOKEN;
        this.SEQ = SEQ;
    }

    /**
     * TYPE : REQ
     * CMD : INFO
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"TYPE":"USER","ID":"","NAME":"","NICK":"","BIRTHDAY":"","SEX":"M"}
     * VERI :
     * TOKEN :
     * SEQ : 1
     */

    private String TYPE;
    private String CMD;
    private String ACCT;
    private String TIME;
    private BODYBean BODY;
    private String VERI;
    private String TOKEN;
    private String SEQ;

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

    public static class BODYBean {
        @Override
        public String toString() {
            return "BODYBean{" +
                    "TYPE='" + TYPE + '\'' +
                    ", ID='" + ID + '\'' +
                    ", NAME='" + NAME + '\'' +
                    ", NICK='" + NICK + '\'' +
                    ", BIRTHDAY='" + BIRTHDAY + '\'' +
                    ", SEX='" + SEX + '\'' +
                    '}';
        }

        public BODYBean(String TYPE, String ID, String NAME, String NICK, String BIRTHDAY, String SEX) {
            this.TYPE = TYPE;
            this.ID = ID;
            this.NAME = NAME;
            this.NICK = NICK;
            this.BIRTHDAY = BIRTHDAY;
            this.SEX = SEX;
        }

        /**
         * TYPE : USER
         * ID :
         * NAME :
         * NICK :
         * BIRTHDAY :
         * SEX : M
         */

        private String TYPE;
        private String ID;
        private String NAME;
        private String NICK;
        private String BIRTHDAY;
        private String SEX;

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

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

        public String getSEX() {
            return SEX;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
        }
    }
}
