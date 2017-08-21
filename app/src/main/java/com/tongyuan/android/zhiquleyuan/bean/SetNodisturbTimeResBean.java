package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/8/17.
 */
public class SetNodisturbTimeResBean {
    /**
     * TYPE : RES
     * CMD : TOYSP
     * ACCT : XXXX
     * TIME : 20170817114831986
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"CNT":1,"LST":[{"PSTATE":"0","TOYID":"201706251204441016838782","ID":"201708171148311016847403","BTIME":"210000","ETIME":"090000"}]}
     * TOKEN : d5578821-8c14-4a7f-b728-619ab9cbeb6c
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
        return "SetNodisturbTimeResBean{" +
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

    public SetNodisturbTimeResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean
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
         * CNT : 1
         * LST : [{"PSTATE":"0","TOYID":"201706251204441016838782","ID":"201708171148311016847403","BTIME":"210000","ETIME":"090000"}]
         */

        private int CNT;
        private List<LSTBean> LST;

        public BODYBean(int CNT, List<LSTBean> LST) {
            this.CNT = CNT;
            this.LST = LST;
        }

        public int getCNT() {
            return CNT;
        }

        public void setCNT(int CNT) {
            this.CNT = CNT;
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
             * TOYID : 201706251204441016838782
             * ID : 201708171148311016847403
             * BTIME : 210000
             * ETIME : 090000
             */

            private String PSTATE;
            private String TOYID;
            private String ID;
            private String BTIME;
            private String ETIME;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "PSTATE='" + PSTATE + '\'' +
                        ", TOYID='" + TOYID + '\'' +
                        ", ID='" + ID + '\'' +
                        ", BTIME='" + BTIME + '\'' +
                        ", ETIME='" + ETIME + '\'' +
                        '}';
            }

            public LSTBean(String PSTATE, String TOYID, String ID, String BTIME, String ETIME) {
                this.PSTATE = PSTATE;
                this.TOYID = TOYID;
                this.ID = ID;
                this.BTIME = BTIME;
                this.ETIME = ETIME;
            }

            public String getPSTATE() {
                return PSTATE;
            }

            public void setPSTATE(String PSTATE) {
                this.PSTATE = PSTATE;
            }

            public String getTOYID() {
                return TOYID;
            }

            public void setTOYID(String TOYID) {
                this.TOYID = TOYID;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
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
        }
    }
}
