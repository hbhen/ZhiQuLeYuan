package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/11/615:40.
 */
public class GetNewestVersionResBean {
    @Override
    public String toString() {
        return "GetNewestVersionResBean{" +
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

    public GetNewestVersionResBean(String CODE, String MSG, String TYPE, String TIME, BODYBean BODY, String TOKEN, String CMD, String ACCT, String
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
     * TIME : 20171107093746997
     * BODY : {"ISFORCE":"0","VERSION":"1.0","URL":"http://abc/abc.apk"}
     * TOKEN :
     * CMD : LASTV
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
         * ISFORCE : 0
         * VERSION : 1.0
         * URL : http://abc/abc.apk
         */

        private String ISFORCE;
        private String VERSION;
        private String URL;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "ISFORCE='" + ISFORCE + '\'' +
                    ", VERSION='" + VERSION + '\'' +
                    ", URL='" + URL + '\'' +
                    '}';
        }

        public BODYBean(String ISFORCE, String VERSION, String URL) {
            this.ISFORCE = ISFORCE;
            this.VERSION = VERSION;
            this.URL = URL;
        }

        public String getISFORCE() {
            return ISFORCE;
        }

        public void setISFORCE(String ISFORCE) {
            this.ISFORCE = ISFORCE;
        }

        public String getVERSION() {
            return VERSION;
        }

        public void setVERSION(String VERSION) {
            this.VERSION = VERSION;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }
    }
}
