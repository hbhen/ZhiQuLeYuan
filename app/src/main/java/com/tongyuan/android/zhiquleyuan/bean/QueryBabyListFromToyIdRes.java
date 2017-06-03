package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/5/16.
 */
//查询宝宝列表 (根据玩具ID) 3.4.24  Result

public class QueryBabyListFromToyIdRes {
    /**
     * TYPE : RES
     * CMD : QRYTOYB
     * ACCT : 13628327181
     * TIME : 20170516114615693
     * BODY : {"PN":"1","NC":"0","PS":"10","LST":[{"NICK":"冰儿","ID":"201705081527111016644121","NAME":"冰","SEX":"男","BIRTHDAY":"20170508000000",
     * "IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/3/jpg/20170508/201705081527111016644122.jpg"}],"CNT":"1"}
     * VERI :
     * TOKEN : b36f91cb-0e71-4698-ab91-0dc042297fdb
     * SEQ : 1
     * CODE : 0
     * MSG :
     */

    private String TYPE;
    private String CMD;
    private String ACCT;
    private String TIME;
    private BODYBean BODY;
    private String VERI;
    private String TOKEN;
    private String SEQ;
    private String CODE;
    private String MSG;

    public QueryBabyListFromToyIdRes(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ,
                                     String CODE, String MSG) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.BODY = BODY;
        this.VERI = VERI;
        this.TOKEN = TOKEN;
        this.SEQ = SEQ;
        this.CODE = CODE;
        this.MSG = MSG;
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

    @Override
    public String toString() {
        return "QueryBabyListFromToyIdRes{" +
                "TYPE='" + TYPE + '\'' +
                ", CMD='" + CMD + '\'' +
                ", ACCT='" + ACCT + '\'' +
                ", TIME='" + TIME + '\'' +
                ", BODY=" + BODY +
                ", VERI='" + VERI + '\'' +
                ", TOKEN='" + TOKEN + '\'' +
                ", SEQ='" + SEQ + '\'' +
                ", CODE='" + CODE + '\'' +
                ", MSG='" + MSG + '\'' +
                '}';
    }

    public static class BODYBean {
        /**
         * PN : 1
         * NC : 0
         * PS : 10
         * LST : [{"NICK":"冰儿","ID":"201705081527111016644121","NAME":"冰","SEX":"男","BIRTHDAY":"20170508000000","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/3/jpg/20170508/201705081527111016644122.jpg"}]
         * CNT : 1
         */

        private String PN;
        private String NC;
        private String PS;
        private String CNT;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PN='" + PN + '\'' +
                    ", NC='" + NC + '\'' +
                    ", PS='" + PS + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PN, String NC, String PS, String CNT, List<LSTBean> LST) {
            this.PN = PN;
            this.NC = NC;
            this.PS = PS;
            this.CNT = CNT;
            this.LST = LST;
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

        public String getPS() {
            return PS;
        }

        public void setPS(String PS) {
            this.PS = PS;
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
             * NICK : 冰儿
             * ID : 201705081527111016644121
             * NAME : 冰
             * SEX : 男
             * BIRTHDAY : 20170508000000
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/3/jpg/20170508/201705081527111016644122.jpg
             */

            private String NICK;
            private String ID;
            private String NAME;
            private String SEX;
            private String BIRTHDAY;
            private String IMG;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "NICK='" + NICK + '\'' +
                        ", ID='" + ID + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", SEX='" + SEX + '\'' +
                        ", BIRTHDAY='" + BIRTHDAY + '\'' +
                        ", IMG='" + IMG + '\'' +
                        '}';
            }

            public LSTBean(String NICK, String ID, String NAME, String SEX, String BIRTHDAY, String IMG) {
                this.NICK = NICK;
                this.ID = ID;
                this.NAME = NAME;
                this.SEX = SEX;
                this.BIRTHDAY = BIRTHDAY;
                this.IMG = IMG;
            }

            public String getNICK() {
                return NICK;
            }

            public void setNICK(String NICK) {
                this.NICK = NICK;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getSEX() {
                return SEX;
            }

            public void setSEX(String SEX) {
                this.SEX = SEX;
            }

            public String getBIRTHDAY() {
                return BIRTHDAY;
            }

            public void setBIRTHDAY(String BIRTHDAY) {
                this.BIRTHDAY = BIRTHDAY;
            }

            public String getIMG() {
                return IMG;
            }

            public void setIMG(String IMG) {
                this.IMG = IMG;
            }
        }
    }
}
