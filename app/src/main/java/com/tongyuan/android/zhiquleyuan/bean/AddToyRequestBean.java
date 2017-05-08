package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/3/13.
 */

public class AddToyRequestBean {
    @Override
    public String toString() {
        return "AddToyRequestBean{" +
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

    public AddToyRequestBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
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
     * CMD : ATOY
     * ACCT : 13628327181
     * TIME : 20161127101010000
     * BODY : {"ID":"dog110","CODE":"dog110","NAME":"dog110","NICK":"dog1101"}
     * VERI :
     * TOKEN : 16dc5a82-413e-413a-a424-b0dde2842fe0
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
                    "ID='" + ID + '\'' +
                    ", CODE='" + CODE + '\'' +
                    ", NAME='" + NAME + '\'' +
                    ", NICK='" + NICK + '\'' +
                    '}';
        }

        public BODYBean(String ID, String CODE, String NAME, String NICK) {
            this.ID = ID;
            this.CODE = CODE;
            this.NAME = NAME;
            this.NICK = NICK;
        }

        /**
         * ID : dog110
         * CODE : dog110
         * NAME : dog110
         * NICK : dog1101
         */

        private String ID;
        private String CODE;
        private String NAME;
        private String NICK;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCODE() {
            return CODE;
        }

        public void setCODE(String CODE) {
            this.CODE = CODE;
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
    }
}