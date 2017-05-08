package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/3/9.
 */

public class DiscoveryGridRequestBean {
    /**
     * TYPE : REQ
     * CMD : QRYCOL
     * ACCT : 13623827181
     * TIME : 20161127101010000
     * BODY : {"ID":"0","PS":"10","PN":"1"}
     * VERI :
     * TOKEN : dfcd0a15-9b77-486f-b4cd-0635897faea4
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
        return "DiscoveryGridRequestBean{" +
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

    public DiscoveryGridRequestBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
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
        @Override
        public String toString() {
            return "BODYBean{" +
                    "ID='" + ID + '\'' +
                    ", PS='" + PS + '\'' +
                    ", PN='" + PN + '\'' +
                    '}';
        }

        public BODYBean(String ID, String PS, String PN) {
            this.ID = ID;
            this.PS = PS;
            this.PN = PN;
        }

        /**
         * ID : 0
         * PS : 10
         * PN : 1
         */

        private String ID;
        private String PS;
        private String PN;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPS() {
            return PS;
        }

        public void setPS(String PS) {
            this.PS = PS;
        }

        public String getPN() {
            return PN;
        }

        public void setPN(String PN) {
            this.PN = PN;
        }
    }
}
