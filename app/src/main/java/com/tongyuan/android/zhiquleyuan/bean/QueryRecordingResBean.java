package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/6/13.
 */

public class QueryRecordingResBean {
    /**
     * TYPE : RES
     * CMD : MYREC
     * ACCT : 13628327181
     * TIME : 20170613144800404
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"PS":"10","PN":"1","NC":"415","LST":[{"RESOLUTION":"","NAME":"12356","SIZE":"","ID":"201706131447251016824343","TIMES":"0",
     * "REMARK":"","IMG":"","DUR":""},{"DUR":"","RESOLUTION":"","NAME":"1235","REMARK":"","IMG":"","ID":"201706131447101016824342","SIZE":"",
     * "TIMES":"0"},{"NAME":"1234","ID":"201706131446521016824341","SIZE":"","RESOLUTION":"","DUR":"","TIMES":"0","IMG":"","REMARK":""},
     * {"TIMES":"0","ID":"201705061925181016643812","IMG":"","SIZE":"7.78KB","DUR":"0:00:02","NAME":"2017年05月06日07:25","REMARK":""},{"TIMES":"0",
     * "DUR":"0:00:02","SIZE":"7.78KB","REMARK":"","ID":"201705061925181016643809","NAME":"2017年05月06日07:25","IMG":""},{"DUR":"0:00:02",
     * "TIMES":"0","SIZE":"7.78KB","REMARK":"","NAME":"2017年05月06日07:25","IMG":"","ID":"201705061925181016643806"},
     * {"ID":"201705061925171016643803","NAME":"2017年05月06日07:25","REMARK":"","SIZE":"7.78KB","IMG":"","TIMES":"0","DUR":"0:00:02"},{"TIMES":"0",
     * "ID":"201705061925161016643800","DUR":"0:00:02","SIZE":"7.78KB","NAME":"2017年05月06日07:25","REMARK":"","IMG":""},{"SIZE":"7.78KB",
     * "TIMES":"0","REMARK":"","ID":"201705061925151016643797","IMG":"","NAME":"2017年05月06日07:25","DUR":"0:00:02"},{"NAME":"2017-03-01 11时03分00秒
     * .amr","ID":"201703011103061016583004","TIMES":"0","RESOLUTION":"","IMG":"","DUR":"","REMARK":"","SIZE":""}],"CNT":"425"}
     * TOKEN : c19d5a15-592a-4b78-863e-846e789163be
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
        return "QueryRecordingResBean{" +
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

    public QueryRecordingResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean
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
         * PS : 10
         * PN : 1
         * NC : 415
         * LST : [{"RESOLUTION":"","NAME":"12356","SIZE":"","ID":"201706131447251016824343","TIMES":"0","REMARK":"","IMG":"","DUR":""},{"DUR":"",
         * "RESOLUTION":"","NAME":"1235","REMARK":"","IMG":"","ID":"201706131447101016824342","SIZE":"","TIMES":"0"},{"NAME":"1234",
         * "ID":"201706131446521016824341","SIZE":"","RESOLUTION":"","DUR":"","TIMES":"0","IMG":"","REMARK":""},{"TIMES":"0",
         * "ID":"201705061925181016643812","IMG":"","SIZE":"7.78KB","DUR":"0:00:02","NAME":"2017年05月06日07:25","REMARK":""},{"TIMES":"0",
         * "DUR":"0:00:02","SIZE":"7.78KB","REMARK":"","ID":"201705061925181016643809","NAME":"2017年05月06日07:25","IMG":""},{"DUR":"0:00:02",
         * "TIMES":"0","SIZE":"7.78KB","REMARK":"","NAME":"2017年05月06日07:25","IMG":"","ID":"201705061925181016643806"},
         * {"ID":"201705061925171016643803","NAME":"2017年05月06日07:25","REMARK":"","SIZE":"7.78KB","IMG":"","TIMES":"0","DUR":"0:00:02"},
         * {"TIMES":"0","ID":"201705061925161016643800","DUR":"0:00:02","SIZE":"7.78KB","NAME":"2017年05月06日07:25","REMARK":"","IMG":""},{"SIZE":"7
         * .78KB","TIMES":"0","REMARK":"","ID":"201705061925151016643797","IMG":"","NAME":"2017年05月06日07:25","DUR":"0:00:02"},{"NAME":"2017-03-01
         * 11时03分00秒.amr","ID":"201703011103061016583004","TIMES":"0","RESOLUTION":"","IMG":"","DUR":"","REMARK":"","SIZE":""}]
         * CNT : 425
         */

        private String PS;
        private String PN;
        private String NC;
        private String CNT;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PS='" + PS + '\'' +
                    ", PN='" + PN + '\'' +
                    ", NC='" + NC + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PS, String PN, String NC, String CNT, List<LSTBean> LST) {
            this.PS = PS;
            this.PN = PN;
            this.NC = NC;
            this.CNT = CNT;
            this.LST = LST;
        }

        public String getPS() {
            return PS;
        }

        public void setPS(String PS) {
            this.PS = PS;
        }

        public String getPN() {
            return PN;
        }

        public void setPN(String PN) {
            this.PN = PN;
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

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean {
            /**
             * RESOLUTION :
             * NAME : 12356
             * SIZE :
             * ID : 201706131447251016824343
             * TIMES : 0
             * REMARK :
             * IMG :
             * DUR :
             */

            private String RESOLUTION;
            private String NAME;
            private String SIZE;
            private String ID;
            private String TIMES;
            private String REMARK;
            private String IMG;
            private String DUR;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "RESOLUTION='" + RESOLUTION + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", SIZE='" + SIZE + '\'' +
                        ", ID='" + ID + '\'' +
                        ", TIMES='" + TIMES + '\'' +
                        ", REMARK='" + REMARK + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", DUR='" + DUR + '\'' +
                        '}';
            }

            public LSTBean(String RESOLUTION, String NAME, String SIZE, String ID, String TIMES, String REMARK, String IMG, String DUR) {
                this.RESOLUTION = RESOLUTION;
                this.NAME = NAME;
                this.SIZE = SIZE;
                this.ID = ID;
                this.TIMES = TIMES;
                this.REMARK = REMARK;
                this.IMG = IMG;
                this.DUR = DUR;
            }

            public String getRESOLUTION() {
                return RESOLUTION;
            }

            public void setRESOLUTION(String RESOLUTION) {
                this.RESOLUTION = RESOLUTION;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getSIZE() {
                return SIZE;
            }

            public void setSIZE(String SIZE) {
                this.SIZE = SIZE;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getTIMES() {
                return TIMES;
            }

            public void setTIMES(String TIMES) {
                this.TIMES = TIMES;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getIMG() {
                return IMG;
            }

            public void setIMG(String IMG) {
                this.IMG = IMG;
            }

            public String getDUR() {
                return DUR;
            }

            public void setDUR(String DUR) {
                this.DUR = DUR;
            }
        }
    }
}
