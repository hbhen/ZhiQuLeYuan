package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/8/15.
 */

public class TransferPermissionsReqBean {
    /**
     * TYPE : REQ
     * CMD : TOYA
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"ID":"123400000000228","CODE":"123400000000228","ATTRID":"1","ATTRVAL":""}
     * VERI :
     * TOKEN : c708de92-1a1a-4b9e-9a5d-50ae4afb3e5d
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

    @Override
    public String toString() {
        return "TransferPermissionsResBean{" +
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

    public TransferPermissionsReqBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.BODY = BODY;
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
         * ID : 123400000000228
         * CODE : 123400000000228
         * ATTRID : 1
         * ATTRVAL :
         */

        private String ID;
        private String CODE;
        private String ATTRID;
        private String ATTRVAL;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "ID='" + ID + '\'' +
                    ", CODE='" + CODE + '\'' +
                    ", ATTRID='" + ATTRID + '\'' +
                    ", ATTRVAL='" + ATTRVAL + '\'' +
                    '}';
        }

        public BODYBean(String ID, String CODE, String ATTRID, String ATTRVAL) {
            this.ID = ID;
            this.CODE = CODE;
            this.ATTRID = ATTRID;
            this.ATTRVAL = ATTRVAL;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCODE() {
            return CODE;
        }

        public void setCODE(String CODE) {
            this.CODE = CODE;
        }

        public String getATTRID() {
            return ATTRID;
        }

        public void setATTRID(String ATTRID) {
            this.ATTRID = ATTRID;
        }

        public String getATTRVAL() {
            return ATTRVAL;
        }

        public void setATTRVAL(String ATTRVAL) {
            this.ATTRVAL = ATTRVAL;
        }
    }
}
