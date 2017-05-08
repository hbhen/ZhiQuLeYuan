package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by android on 2017/3/11.
 */

public class GridSecondListResultBean {

    /**
     * TYPE : RES
     * CMD : QRYRES
     * ACCT : 13628327181
     * TIME : 20170311221425841
     * BODY : {"NC":"0","PN":"1","CNT":"0","PS":"-1","LST":[]}
     * VERI :
     * TOKEN : 99d0ba26-ce7f-482d-b8ac-456a50f5ea0a
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
        return "GridSecondListResultBean{" +
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

    public GridSecondListResultBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
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
        @Override
        public String toString() {
            return "BODYBean{" +
                    "NC='" + NC + '\'' +
                    ", PN='" + PN + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", PS='" + PS + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String NC, String PN, String CNT, String PS, List<?> LST) {
            this.NC = NC;
            this.PN = PN;
            this.CNT = CNT;
            this.PS = PS;
            this.LST = LST;
        }

        /**
         * NC : 0
         * PN : 1
         * CNT : 0
         * PS : -1
         * LST : []
         */

        private String NC;
        private String PN;
        private String CNT;
        private String PS;
        private List<?> LST;

        public String getNC() {
            return NC;
        }

        public void setNC(String NC) {
            this.NC = NC;
        }

        public String getPN() {
            return PN;
        }

        public void setPN(String PN) {
            this.PN = PN;
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

        public List<?> getLST() {
            return LST;
        }

        public void setLST(List<?> LST) {
            this.LST = LST;
        }
    }
}
