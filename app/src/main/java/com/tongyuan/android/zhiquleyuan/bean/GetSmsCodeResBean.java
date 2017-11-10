package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/11/811:13.
 */
public class GetSmsCodeResBean {

    /**
     * CODE : 0
     * MSG :
     * TYPE : RES
     * TIME : 20171108111826038
     * BODY : {"tmp":"813a5bded567b7895426cfd740bda6a6","AGREENO":"813aa4ded5984889ab26cf28404259a6"}
     * TOKEN :
     * CMD : SMSA
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

    @Override
    public String toString() {
        return "GetSmsCodeResBean{" +
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

    public GetSmsCodeResBean(String CODE, String MSG, String TYPE, String TIME, BODYBean BODY, String TOKEN, String CMD, String ACCT, String VERI,
                             String SEQ) {
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
         * tmp : 813a5bded567b7895426cfd740bda6a6
         * AGREENO : 813aa4ded5984889ab26cf28404259a6
         */

        private String tmp;
        private String AGREENO;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "tmp='" + tmp + '\'' +
                    ", AGREENO='" + AGREENO + '\'' +
                    '}';
        }

        public BODYBean(String tmp, String AGREENO) {
            this.tmp = tmp;
            this.AGREENO = AGREENO;
        }

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
