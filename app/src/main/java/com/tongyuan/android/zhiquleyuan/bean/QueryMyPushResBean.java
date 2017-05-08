package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by DTC on 2017/4/12.
 */

public class QueryMyPushResBean {
    /**
     * TYPE : RES
     * CMD : MYPUSH
     * ACCT : 13628327181
     * TIME : 20170412164226237
     * BODY : {"LST":[],"NC":"0","CNT":"0","PS":"10","PN":"1"}
     * VERI :
     * TOKEN : bc7eebde-ad97-417c-8d3b-872889d21c4f
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
        return "QueryMyPushResBean{" +
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

    public QueryMyPushResBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE,
                              String MSG) {
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
         * LST : []
         * NC : 0
         * CNT : 0
         * PS : 10
         * PN : 1
         */

        private String NC;
        private String CNT;
        private String PS;
        private String PN;
        private List<?> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "NC='" + NC + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", PS='" + PS + '\'' +
                    ", PN='" + PN + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String NC, String CNT, String PS, String PN, List<?> LST) {
            this.NC = NC;
            this.CNT = CNT;
            this.PS = PS;
            this.PN = PN;
            this.LST = LST;
        }

        public String getNC() {
            return NC;
        }

        public void setNC(String NC) {
            this.NC = NC;
        }

        public String getCNT() {
            return CNT;
        }

        public void setCNT(String CNT) {
            this.CNT = CNT;
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

        public List<?> getLST() {
            return LST;
        }

        public void setLST(List<?> LST) {
            this.LST = LST;
        }
    }

}
