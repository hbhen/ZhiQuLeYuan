package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/1/15.
 */

public class SmsCodeApplyBean {

    /**
     * TYPE : RES
     * CMD : SMSA
     * ACCT : XXXX
     * TIME : 20170115200031495
     * BODY : {"tmp":"66cd813dce2db1f2512d24d23a3e8511","AGREENO":"66cd7e3dced24ef2ae2d242d3ac17a11"}
     * VERI :
     * TOKEN :
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
         * tmp : 66cd813dce2db1f2512d24d23a3e8511
         * AGREENO : 66cd7e3dced24ef2ae2d242d3ac17a11
         */

        private String tmp;
        private String AGREENO;

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getAGREENO() {
            return AGREENO;
        }

        public void setAGREENO(String AGREENO) {
            this.AGREENO = AGREENO;
        }
    }
}
