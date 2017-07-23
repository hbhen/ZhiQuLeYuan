package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/7/20.
 */
public class NodisturbTimeResBean {
    /**
     * TYPE : RES
     * CMD : QTOYSP
     * ACCT : XXXX
     * TIME : 20170720103518169
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"LST":[{"PSTATE":"0","ID":"201707191628281016841261","TOYID":"201706251204441016838782","ETIME":"144000","BTIME":"142900"},
     * {"TOYID":"201706251204441016838782","ID":"201707191727101016841289","BTIME":"012700","PSTATE":"0","ETIME":"173000"},{"ETIME":"090000",
     * "PSTATE":"1","TOYID":"201706251204441016838782","ID":"201707201029251016841379","BTIME":"210000"}],"NC":"0","CNT":"3","PN":"1","PS":"10"}
     * TOKEN : 3785c5db-8739-446b-92b7-69692ceca4ea
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
        return "NodisturbTimeResBean{" +
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

    public NodisturbTimeResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean BODY,
                                String TOKEN) {
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
         * LST : [{"PSTATE":"0","ID":"201707191628281016841261","TOYID":"201706251204441016838782","ETIME":"144000","BTIME":"142900"},
         * {"TOYID":"201706251204441016838782","ID":"201707191727101016841289","BTIME":"012700","PSTATE":"0","ETIME":"173000"},{"ETIME":"090000",
         * "PSTATE":"1","TOYID":"201706251204441016838782","ID":"201707201029251016841379","BTIME":"210000"}]
         * NC : 0
         * CNT : 3
         * PN : 1
         * PS : 10
         */

        private String NC;
        private String CNT;
        private String PN;
        private String PS;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "NC='" + NC + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", PN='" + PN + '\'' +
                    ", PS='" + PS + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String NC, String CNT, String PN, String PS, List<LSTBean> LST) {
            this.NC = NC;
            this.CNT = CNT;
            this.PN = PN;
            this.PS = PS;
            this.LST = LST;
        }

        public String getNC() {
            return NC;
        }

        public void setNC(String NC) {
            this.NC = NC;
        }

        public String getCNT() {
            return CNT;
        }

        public void setCNT(String CNT) {
            this.CNT = CNT;
        }

        public String getPN() {
            return PN;
        }

        public void setPN(String PN) {
            this.PN = PN;
        }

        public String getPS() {
            return PS;
        }

        public void setPS(String PS) {
            this.PS = PS;
        }

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean {
            /**
             * PSTATE : 0
             * ID : 201707191628281016841261
             * TOYID : 201706251204441016838782
             * ETIME : 144000
             * BTIME : 142900
             */

            private String PSTATE;
            private String ID;
            private String TOYID;
            private String ETIME;
            private String BTIME;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "PSTATE='" + PSTATE + '\'' +
                        ", ID='" + ID + '\'' +
                        ", TOYID='" + TOYID + '\'' +
                        ", ETIME='" + ETIME + '\'' +
                        ", BTIME='" + BTIME + '\'' +
                        '}';
            }

            public LSTBean(String PSTATE, String ID, String TOYID, String ETIME, String BTIME) {
                this.PSTATE = PSTATE;
                this.ID = ID;
                this.TOYID = TOYID;
                this.ETIME = ETIME;
                this.BTIME = BTIME;
            }

            public String getPSTATE() {
                return PSTATE;
            }

            public void setPSTATE(String PSTATE) {
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

            public String getETIME() {
                return ETIME;
            }

            public void setETIME(String ETIME) {
                this.ETIME = ETIME;
            }

            public String getBTIME() {
                return BTIME;
            }

            public void setBTIME(String BTIME) {
                this.BTIME = BTIME;
            }
        }
    }
}
