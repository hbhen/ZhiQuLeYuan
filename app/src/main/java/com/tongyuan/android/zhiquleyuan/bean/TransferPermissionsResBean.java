package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/8/15.
 */
public class TransferPermissionsResBean {

    /**
     * TYPE : RES
     * CMD : TOYA
     * ACCT : XXXX
     * TIME : 20170815175253877
     * VERI :
     * SEQ : 1
     * CODE : -201
     * MSG : 指定的用户不存在
     * BODY : {"ID":"123400000000228","ATTRVAL":"","CODE":"123400000000228","ATTRID":"1"}
     * TOKEN : c708de92-1a1a-4b9e-9a5d-50ae4afb3e5d
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

    @Override
    public String toString() {
        return "TransferPermissionsResBean{" +
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

    public TransferPermissionsResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean
            BODY, String TOKEN) {
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

    public static class BODYBean {
        /**
         * ID : 123400000000228
         * ATTRVAL :
         * CODE : 123400000000228
         * ATTRID : 1
         */

        private String ID;
        private String ATTRVAL;
        private String CODE;
        private String ATTRID;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "ID='" + ID + '\'' +
                    ", ATTRVAL='" + ATTRVAL + '\'' +
                    ", CODE='" + CODE + '\'' +
                    ", ATTRID='" + ATTRID + '\'' +
                    '}';
        }

        public BODYBean(String ID, String ATTRVAL, String CODE, String ATTRID) {
            this.ID = ID;
            this.ATTRVAL = ATTRVAL;
            this.CODE = CODE;
            this.ATTRID = ATTRID;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getATTRVAL() {
            return ATTRVAL;
        }

        public void setATTRVAL(String ATTRVAL) {
            this.ATTRVAL = ATTRVAL;
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
    }
}
