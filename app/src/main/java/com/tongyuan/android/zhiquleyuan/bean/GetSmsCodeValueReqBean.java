package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/11/811:28.
 */
public class GetSmsCodeValueReqBean {
    @Override
    public String toString() {
        return "GetSmsCodeValueReqBean{" +
                "TYPE='" + TYPE + '\'' +
                ", CMD='" + CMD + '\'' +
                ", ACCT='" + ACCT + '\'' +
                ", TIME='" + TIME + '\'' +
                ", BODY=" + BODY +
                ", VERI='" + VERI + '\'' +
                ", TOKEN='" + TOKEN + '\'' +
                ", SEQ='" + SEQ + '\'' +
                '}';
    }

    public GetSmsCodeValueReqBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.BODY = BODY;
        this.VERI = VERI;
        this.TOKEN = TOKEN;
        this.SEQ = SEQ;
    }

    /**
     * TYPE : REQ
     * CMD : SMS
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"CODETYPE":"REG","PHONENO":"18511043435","AGREENO":"908ef852964fbef773aea526e7fe9004","test":""}
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
         * CODETYPE : REG
         * PHONENO : 18511043435
         * AGREENO : 908ef852964fbef773aea526e7fe9004
         * test :
         */

        /*
        * 返回的数据
        * 195201
        * 116
        *
        * */
        private String CODETYPE;
        private String PHONENO;
        private String AGREENO;
        private String test;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "CODETYPE='" + CODETYPE + '\'' +
                    ", PHONENO='" + PHONENO + '\'' +
                    ", AGREENO='" + AGREENO + '\'' +
                    ", test='" + test + '\'' +
                    '}';
        }

        public BODYBean(String CODETYPE, String PHONENO, String AGREENO, String test) {
            this.CODETYPE = CODETYPE;
            this.PHONENO = PHONENO;
            this.AGREENO = AGREENO;
            this.test = test;
        }

        public String getCODETYPE() {
            return CODETYPE;
        }

        public void setCODETYPE(String CODETYPE) {
            this.CODETYPE = CODETYPE;
        }

        public String getPHONENO() {
            return PHONENO;
        }

        public void setPHONENO(String PHONENO) {
            this.PHONENO = PHONENO;
        }

        public String getAGREENO() {
            return AGREENO;
        }

        public void setAGREENO(String AGREENO) {
            this.AGREENO = AGREENO;
        }

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }
    }
}
