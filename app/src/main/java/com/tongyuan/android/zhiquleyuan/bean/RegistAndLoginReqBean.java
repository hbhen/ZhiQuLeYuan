package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/11/811:59.
 */
public class RegistAndLoginReqBean {
    @Override
    public String toString() {
        return "RegistAndLoginReqBean{" +
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

    public RegistAndLoginReqBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
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
     * CMD : REG
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"PHONE":"18511043435","SMSCODE":"195201","IDX":"116","DEVCODE":"xxx","OS":"A","VERSION":"2.0"}
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
         * PHONE : 18511043435
         * SMSCODE : 195201
         * IDX : 116
         * DEVCODE : xxx
         * OS : A
         * VERSION : 2.0
         */

        private String PHONE;
        private String SMSCODE;
        private String IDX;
        private String DEVCODE;
        private String OS;
        private String VERSION;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PHONE='" + PHONE + '\'' +
                    ", SMSCODE='" + SMSCODE + '\'' +
                    ", IDX='" + IDX + '\'' +
                    ", DEVCODE='" + DEVCODE + '\'' +
                    ", OS='" + OS + '\'' +
                    ", VERSION='" + VERSION + '\'' +
                    '}';
        }

        public BODYBean(String PHONE, String SMSCODE, String IDX, String DEVCODE, String OS, String VERSION) {
            this.PHONE = PHONE;
            this.SMSCODE = SMSCODE;
            this.IDX = IDX;
            this.DEVCODE = DEVCODE;
            this.OS = OS;
            this.VERSION = VERSION;
        }

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }

        public String getSMSCODE() {
            return SMSCODE;
        }

        public void setSMSCODE(String SMSCODE) {
            this.SMSCODE = SMSCODE;
        }

        public String getIDX() {
            return IDX;
        }

        public void setIDX(String IDX) {
            this.IDX = IDX;
        }

        public String getDEVCODE() {
            return DEVCODE;
        }

        public void setDEVCODE(String DEVCODE) {
            this.DEVCODE = DEVCODE;
        }

        public String getOS() {
            return OS;
        }

        public void setOS(String OS) {
            this.OS = OS;
        }

        public String getVERSION() {
            return VERSION;
        }

        public void setVERSION(String VERSION) {
            this.VERSION = VERSION;
        }
    }
}
