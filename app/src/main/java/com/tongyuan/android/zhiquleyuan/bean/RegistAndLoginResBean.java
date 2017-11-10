package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/11/811:59.
 */
public class RegistAndLoginResBean {
    @Override
    public String toString() {
        return "RegistAndLoginResBean{" +
                "CODE='" + CODE + '\'' +
                ", MSG='" + MSG + '\'' +
                ", TYPE='" + TYPE + '\'' +
                ", TIME='" + TIME + '\'' +
                ", BODY=" + BODY +
                ", TOKEN='" + TOKEN + '\'' +
                ", CMD='" + CMD + '\'' +
                ", ACCT='" + ACCT + '\'' +
                ", VERI='" + VERI + '\'' +
                ", SEQ='" + SEQ + '\'' +
                '}';
    }

    public RegistAndLoginResBean(String CODE, String MSG, String TYPE, String TIME, BODYBean BODY, String TOKEN, String CMD, String ACCT, String
            VERI, String SEQ) {
        this.CODE = CODE;
        this.MSG = MSG;
        this.TYPE = TYPE;
        this.TIME = TIME;
        this.BODY = BODY;
        this.TOKEN = TOKEN;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.VERI = VERI;
        this.SEQ = SEQ;
    }

    /**
     * CODE : 0
     * MSG :
     * TYPE : RES
     * TIME : 20171108120311925
     * BODY : {"ID":"201701221329241016563038","PHONE":"18511043435"}
     * TOKEN : 4d3d0ba1-2490-4e16-97a8-94072fd5b563
     * CMD : REG
     * ACCT : XXXX
     * VERI :
     * SEQ : 1
     */

    private String CODE;
    private String MSG;
    private String TYPE;
    private String TIME;
    private BODYBean BODY;
    private String TOKEN;
    private String CMD;
    private String ACCT;
    private String VERI;
    private String SEQ;

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

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
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

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
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

    public String getVERI() {
        return VERI;
    }

    public void setVERI(String VERI) {
        this.VERI = VERI;
    }

    public String getSEQ() {
        return SEQ;
    }

    public void setSEQ(String SEQ) {
        this.SEQ = SEQ;
    }

    public static class BODYBean {
        /**
         * ID : 201701221329241016563038
         * PHONE : 18511043435
         */

        private String ID;
        private String PHONE;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "ID='" + ID + '\'' +
                    ", PHONE='" + PHONE + '\'' +
                    '}';
        }

        public BODYBean(String ID, String PHONE) {
            this.ID = ID;
            this.PHONE = PHONE;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }
    }
}
