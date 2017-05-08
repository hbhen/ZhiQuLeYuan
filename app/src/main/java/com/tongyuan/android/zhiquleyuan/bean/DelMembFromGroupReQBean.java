package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/4/18.
 */

public class DelMembFromGroupReQBean {
    /**
     * TYPE : REQ
     * CMD : DU2T
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"ID":"201703131827211016583653","CODE":"dog110","USERID":"201701221329241016563038"}
     * VERI :
     * TOKEN : 6f3fd608-b4b4-4e62-a2be-aa45187ab708
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

    @Override
    public String toString() {
        return "DelMembFromGroupReQBean{" +
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

    public DelMembFromGroupReQBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.BODY = BODY;
        this.VERI = VERI;
        this.TOKEN = TOKEN;
        this.SEQ = SEQ;
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

    public static class BODYBean {
        /**
         * ID : 201703131827211016583653
         * CODE : dog110
         * USERID : 201701221329241016563038
         */

        private String ID;
        private String CODE;
        private String USERID;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "ID='" + ID + '\'' +
                    ", CODE='" + CODE + '\'' +
                    ", USERID='" + USERID + '\'' +
                    '}';
        }

        public BODYBean(String ID, String CODE, String USERID) {
            this.ID = ID;
            this.CODE = CODE;
            this.USERID = USERID;
        }

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

        public String getUSERID() {
            return USERID;
        }

        public void setUSERID(String USERID) {
            this.USERID = USERID;
        }
    }
}
