package com.tongyuan.android.zhiquleyuan.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 2017/8/4.
 */

public class DeleteMyPushResBean {
    /**
     * TYPE : RES
     * CMD : DMYPUSH
     * ACCT : XXXX
     * TIME : 20170804102602237
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"1":"1"}
     * TOKEN : ff35777e-1f5f-4f7c-be03-359e4e752918
     */

    private String TYPE;
    private String CMD;
    private String ACCT;
    private String TIME;
    private String VERI;
    private String SEQ;
    private String CODE;
    private String MSG;
    private BODYBean BODY;
    private String TOKEN;

    public DeleteMyPushResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean BODY,
                               String TOKEN) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.VERI = VERI;
        this.SEQ = SEQ;
        this.CODE = CODE;
        this.MSG = MSG;
        this.BODY = BODY;
        this.TOKEN = TOKEN;
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

    @Override
    public String toString() {
        return "DeleteMyPushResBean{" +
                "TYPE='" + TYPE + '\'' +
                ", CMD='" + CMD + '\'' +
                ", ACCT='" + ACCT + '\'' +
                ", TIME='" + TIME + '\'' +
                ", VERI='" + VERI + '\'' +
                ", SEQ='" + SEQ + '\'' +
                ", CODE='" + CODE + '\'' +
                ", MSG='" + MSG + '\'' +
                ", BODY=" + BODY +
                ", TOKEN='" + TOKEN + '\'' +
                '}';
    }

    public static class BODYBean {
        /**
         * 1 : 1
         */

        @SerializedName("1")
        private String _$1;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "_$1='" + _$1 + '\'' +
                    '}';
        }

        public BODYBean(String _$1) {
            this._$1 = _$1;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }
    }
}
