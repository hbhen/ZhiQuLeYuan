package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/5/16.
 */

public class BindBabyToToyReq {

    /**
     * TYPE : REQ
     * CMD : TOYB
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"LST":[{"ID":"201705081005211016644025","CODE":"861575032099128","BABYID":"201705081527111016644121"}]}
     * VERI :
     * TOKEN : 384b9f85-ac33-4877-bd88-94df9fd9ac71
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

    public BindBabyToToyReq(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
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

    @Override
    public String toString() {
        return "BindBabyToToyReq{" +
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

    public static class BODYBean {
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "LST=" + LST +
                    '}';
        }

        public BODYBean(List<LSTBean> LST) {
            this.LST = LST;
        }

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean {
            /**
             * ID : 201705081005211016644025
             * CODE : 861575032099128
             * BABYID : 201705081527111016644121
             */

            private String ID;
            private String CODE;
            private String BABYID;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "ID='" + ID + '\'' +
                        ", CODE='" + CODE + '\'' +
                        ", BABYID='" + BABYID + '\'' +
                        '}';
            }

            public LSTBean(String ID, String CODE, String BABYID) {
                this.ID = ID;
                this.CODE = CODE;
                this.BABYID = BABYID;
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

            public String getBABYID() {
                return BABYID;
            }

            public void setBABYID(String BABYID) {
                this.BABYID = BABYID;
            }
        }
    }
}
