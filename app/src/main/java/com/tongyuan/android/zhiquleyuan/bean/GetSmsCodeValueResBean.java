package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/11/811:28.
 */
public class GetSmsCodeValueResBean {
    @Override
    public String toString() {
        return "GetSmsCodeValueResBean{" +
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

    public GetSmsCodeValueResBean(String CODE, String MSG, String TYPE, String TIME, BODYBean BODY, String TOKEN, String CMD, String ACCT, String
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
     * MSG : 成功
     * TYPE : RES
     * TIME : 20171108113133645
     * BODY : {"smscode":"118756","SENDTIME":"20171108113133","IDX":"115"}
     * TOKEN :
     * CMD : SMS
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
         * smscode : 118756
         * SENDTIME : 20171108113133
         * IDX : 115
         */

        private String smscode;
        private String SENDTIME;
        private String IDX;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "smscode='" + smscode + '\'' +
                    ", SENDTIME='" + SENDTIME + '\'' +
                    ", IDX='" + IDX + '\'' +
                    '}';
        }

        public BODYBean(String smscode, String SENDTIME, String IDX) {
            this.smscode = smscode;
            this.SENDTIME = SENDTIME;
            this.IDX = IDX;
        }

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
