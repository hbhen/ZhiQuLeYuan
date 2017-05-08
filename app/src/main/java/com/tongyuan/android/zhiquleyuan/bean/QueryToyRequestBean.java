package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/3/9.
 */

public class QueryToyRequestBean {
    @Override
    public String toString() {
        return "QueryToyRequestBean{" +
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

    public QueryToyRequestBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
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
     * CMD : QRYTOYS
     * ACCT : 18511043435
     * TIME : 20161127101010000
     * BODY : {"TYPE":"0","TOYID":"","BABYID":"","PS":"-1","PN":"1"}
     * VERI :
     * TOKEN : c093adc3-300d-4fb3-8af1-1bf5a476ff9a
     * SEQ : 1
     */

    public String TYPE;
    public String CMD;
    public String ACCT;
    public String TIME;
    public BODYBean BODY;
    public String VERI;
    public String TOKEN;
    public String SEQ;

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
                    ", TOYID='" + TOYID + '\'' +
                    ", BABYID='" + BABYID + '\'' +
                    ", PS='" + PS + '\'' +
                    ", PN='" + PN + '\'' +
                    '}';
        }

        public BODYBean(String TYPE, String TOYID, String BABYID, String PS, String PN) {
            this.TYPE = TYPE;
            this.TOYID = TOYID;
            this.BABYID = BABYID;
            this.PS = PS;
            this.PN = PN;
        }

        /**
         * TYPE : 0
         * TOYID :
         * BABYID :
         * PS : -1
         * PN : 1
         */

        public String TYPE;
        public String TOYID;
        public String BABYID;
        public String PS;
        public String PN;

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }

        public String getTOYID() {
            return TOYID;
        }

        public void setTOYID(String TOYID) {
            this.TOYID = TOYID;
        }

        public String getBABYID() {
            return BABYID;
        }

        public void setBABYID(String BABYID) {
            this.BABYID = BABYID;
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