package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/4/20.
 */

public class QueryPlayingMusicReqBean {
    /**
     * TYPE : REQ
     * CMD : QPRES
     * ACCT : 13628327181
     * TIME : 20161127101010000
     * BODY : {"TOYID":"201703131827211016583653"}
     * VERI :
     * TOKEN : 0794d43d-c4b8-4609-8086-d72a069834ae
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

    public QueryPlayingMusicReqBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
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

    @Override
    public String toString() {
        return "QueryPlayingMusicReqBean{" +
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

    public static class BODYBean {
        /**
         * TOYID : 201703131827211016583653
         */

        private String TOYID;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "TOYID='" + TOYID + '\'' +
                    '}';
        }

        public BODYBean(String TOYID) {
            this.TOYID = TOYID;
        }

        public String getTOYID() {
            return TOYID;
        }

        public void setTOYID(String TOYID) {
            this.TOYID = TOYID;
        }
    }
}
