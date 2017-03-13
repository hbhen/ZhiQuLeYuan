package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2016/12/25.
 */

public class SendPhoneBean {



    private String TYPE;
    private String CMD;
    private String ACCT;
    private String TIME;
    private String BODY;
    private String VERI;
    private String TOKEN;
    private String SEQ;

    public SendPhoneBean(String TYPE, String CMD, String ACCT, String TIME, String PHONE, String VERI, String TOKEN, String SEQ) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.BODY = PHONE;
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

//    public BODYBean getBODY() {
//        return BODY;
//    }
//
//    public void setBODY(BODYBean BODY) {
//        this.BODY = BODY;
//    }

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
         * PHONE : XXXXX
         */

        private String PHONE;
        public BODYBean(String PHONE) {
            this.PHONE = PHONE;
        }

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }
    }
}
