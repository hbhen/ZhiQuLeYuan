package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/8/17.
 */

public class SetNodisturbTimeReqBean {
    /**
     * TYPE : REQ
     * CMD : TOYSP
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"LST":[{"ID":"1","TOYID":"123400000000228","CODE":"123400000000228","BTIME":"210000","ETIME":"090000","PSTATE":"0"}]}
     * VERI :
     * TOKEN : d5578821-8c14-4a7f-b728-619ab9cbeb6c
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
        return "SetNodisturbTimeReqBean{" +
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

    public SetNodisturbTimeReqBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
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
             * ID : 1
             * TOYID : 123400000000228
             * CODE : 123400000000228
             * BTIME : 210000
             * ETIME : 090000
             * PSTATE : 0
             */

            private String ID;
            private String TOYID;
            private String CODE;
            private String BTIME;
            private String ETIME;
            private String PSTATE;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "ID='" + ID + '\'' +
                        ", TOYID='" + TOYID + '\'' +
                        ", CODE='" + CODE + '\'' +
                        ", BTIME='" + BTIME + '\'' +
                        ", ETIME='" + ETIME + '\'' +
                        ", PSTATE='" + PSTATE + '\'' +
                        '}';
            }

            public LSTBean(String ID, String TOYID, String CODE, String BTIME, String ETIME, String PSTATE) {
                this.ID = ID;
                this.TOYID = TOYID;
                this.CODE = CODE;
                this.BTIME = BTIME;
                this.ETIME = ETIME;
                this.PSTATE = PSTATE;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getTOYID() {
                return TOYID;
            }

            public void setTOYID(String TOYID) {
                this.TOYID = TOYID;
            }

            public String getCODE() {
                return CODE;
            }

            public void setCODE(String CODE) {
                this.CODE = CODE;
            }

            public String getBTIME() {
                return BTIME;
            }

            public void setBTIME(String BTIME) {
                this.BTIME = BTIME;
            }

            public String getETIME() {
                return ETIME;
            }

            public void setETIME(String ETIME) {
                this.ETIME = ETIME;
            }

            public String getPSTATE() {
                return PSTATE;
            }

            public void setPSTATE(String PSTATE) {
                this.PSTATE = PSTATE;
            }
        }
    }
}
