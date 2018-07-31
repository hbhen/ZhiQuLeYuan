package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2018/7/27.
 */

public class ShutDownRoomResBean {
    @Override
    public String toString() {
        return "ShutDownRoomResBean{" +
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

    public ShutDownRoomResBean(String CODE, String MSG, String TYPE, String TIME, BODYBean BODY, String TOKEN, String CMD, String ACCT, String VERI, String SEQ) {
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
     * TIME : 20180724194449458
     * BODY : {"ROOMID":"757972110"}
     * TOKEN : 27aa0371-4855-4ba8-9903-eb9c16849cef
     * CMD : CLOSEROOM
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
         * ROOMID : 757972110
         */

        private String ROOMID;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "ROOMID='" + ROOMID + '\'' +
                    '}';
        }

        public BODYBean(String ROOMID) {
            this.ROOMID = ROOMID;
        }

        public String getROOMID() {
            return ROOMID;
        }

        public void setROOMID(String ROOMID) {
            this.ROOMID = ROOMID;
        }
    }
}
