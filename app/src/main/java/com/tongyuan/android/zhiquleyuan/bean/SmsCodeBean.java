package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/1/17.
 */

public class SmsCodeBean {


    /**
     * TYPE : RES
     * CMD : SMS
     * ACCT : 13628327181
     * TIME : 20170117142548344
     * BODY : {"smscode":"127202","SENDTIME":"20170117142548","IDX":"327"}
     * VERI :
     * TOKEN :
     * SEQ : 1
     * CODE : 0
     * MSG : 成功
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
         * smscode : 127202
         * SENDTIME : 20170117142548
         * IDX : 327
         */

        private String smscode;
        private String SENDTIME;
        private String IDX;

        public String getSmscode() {
            return smscode;
        }

        public void setSmscode(String smscode) {
            this.smscode = smscode;
        }

        public String getSENDTIME() {
            return SENDTIME;
        }

        public void setSENDTIME(String SENDTIME) {
            this.SENDTIME = SENDTIME;
        }

        public String getIDX() {
            return IDX;
        }

        public void setIDX(String IDX) {
            this.IDX = IDX;
        }
    }
}
