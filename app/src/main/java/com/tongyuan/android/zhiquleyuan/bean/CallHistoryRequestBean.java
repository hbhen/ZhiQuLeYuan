package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/3/17.
 */
public class CallHistoryRequestBean {
    @Override
    public String toString() {
        return "CallHistoryRequestBean{" +
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

    public CallHistoryRequestBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
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
     * CMD : MTALK
     * ACCT : 13628327181
     * TIME : 20161127101010000
     * BODY : {"TOYID":"","PS":"10","PN":"1"}
     * VERI :
     * TOKEN : ce6f6e66-9634-4727-84bd-dc90c79e3355
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
                    "TOYID='" + TOYID + '\'' +
                    ", PS='" + PS + '\'' +
                    ", PN='" + PN + '\'' +
                    '}';
        }

        public BODYBean(String TOYID, String PS, String PN) {
            this.TOYID = TOYID;
            this.PS = PS;
            this.PN = PN;
        }

        /**
         * TOYID :
         * PS : 10
         * PN : 1
         */

        private String TOYID;
        private String PS;
        private String PN;

        public String getTOYID() {
            return TOYID;
        }

        public void setTOYID(String TOYID) {
            this.TOYID = TOYID;
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
