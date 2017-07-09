package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/4/12.
 */

public class QueryMyPushReqBean {
    @Override
    public String toString() {
        return "QueryMyPushReqBean{" +
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

    public QueryMyPushReqBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
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
     * CMD : MYPUSH
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"WORD":"","TOYID":"","RESID":"","PS":"10","PN":"1"}
     * VERI :
     * TOKEN : 5e2d5649-6030-47c8-9efc-f02487df1b6a
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
        /**
         * WORD :
         * TOYID :
         * RESID :
         * PS : 10
         * PN : 1
         */

        private String WORD;
        private String TOYID;
        private String RESID;
        private String PS;
        private String PN;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "WORD='" + WORD + '\'' +
                    ", TOYID='" + TOYID + '\'' +
                    ", RESID='" + RESID + '\'' +
                    ", PS='" + PS + '\'' +
                    ", PN='" + PN + '\'' +
                    '}';
        }

        public BODYBean(String RESID, String PS, String PN, String WORD, String TOYID) {
            this.RESID = RESID;
            this.PS = PS;
            this.PN = PN;
            this.WORD = WORD;
            this.TOYID = TOYID;
        }

        public String getWORD() {
            return WORD;
        }

        public void setWORD(String WORD) {
            this.WORD = WORD;
        }

        public String getTOYID() {
            return TOYID;
        }

        public void setTOYID(String TOYID) {
            this.TOYID = TOYID;
        }

        public String getRESID() {
            return RESID;
        }

        public void setRESID(String RESID) {
            this.RESID = RESID;
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
