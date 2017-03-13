package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2016/12/20.
 */

public class LoginBean {

    /**
     * TYPE : REQ
     * CMD : REG
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"PHONE":"18511025309","SMSCODE":"xxxxxx","IDX":"1","DEVCODE":"xxx"}
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
        /**
         * PHONE : 18511025309
         * SMSCODE : xxxxxx
         * IDX : 1
         * DEVCODE : xxx
         */

        private String PHONE;
        private String SMSCODE;
        private String IDX;
        private String DEVCODE;

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }

        public String getSMSCODE() {
            return SMSCODE;
        }

        public void setSMSCODE(String SMSCODE) {
            this.SMSCODE = SMSCODE;
        }

        public String getIDX() {
            return IDX;
        }

        public void setIDX(String IDX) {
            this.IDX = IDX;
        }

        public String getDEVCODE() {
            return DEVCODE;
        }

        public void setDEVCODE(String DEVCODE) {
            this.DEVCODE = DEVCODE;
        }
    }
}
