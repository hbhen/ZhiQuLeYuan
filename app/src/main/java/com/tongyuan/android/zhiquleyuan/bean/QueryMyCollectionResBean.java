package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/6/14.
 */
public class QueryMyCollectionResBean {
    /**
     * TYPE : RES
     * CMD : MYFAV
     * ACCT : 15239184643
     * TIME : 20170614104015357
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"PN":"1","PS":"10","NC":"0","LST":[{"COLID":"201610301737481016432787 ","TYPE":"音频文件","REMARK":"","ID":"201705151810551016803172",
     * "NAME":"丝路驼铃","SIZE":"9.78MB","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151812531016803177.jpg",
     * "DUR":"0:07:07","TIMES":"123","COLNAME":"儿童歌谣 "}],"CNT":"1"}
     * TOKEN : 8f57181e-47f6-4c81-8bee-df29b677df9c
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
        return "QueryMyCollectionResBean{" +
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

    public QueryMyCollectionResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean
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
         * PN : 1
         * PS : 10
         * NC : 0
         * LST : [{"COLID":"201610301737481016432787 ","TYPE":"音频文件","REMARK":"","ID":"201705151810551016803172","NAME":"丝路驼铃","SIZE":"9.78MB",
         * "IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151812531016803177.jpg","DUR":"0:07:07","TIMES":"123",
         * "COLNAME":"儿童歌谣 "}]
         * CNT : 1
         */

        private String PN;
        private String PS;
        private String NC;
        private String CNT;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PN='" + PN + '\'' +
                    ", PS='" + PS + '\'' +
                    ", NC='" + NC + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PN, String PS, String NC, String CNT, List<LSTBean> LST) {
            this.PN = PN;
            this.PS = PS;
            this.NC = NC;
            this.CNT = CNT;
            this.LST = LST;
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
             * COLID : 201610301737481016432787
             * TYPE : 音频文件
             * REMARK :
             * ID : 201705151810551016803172
             * NAME : 丝路驼铃
             * SIZE : 9.78MB
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151812531016803177.jpg
             * DUR : 0:07:07
             * TIMES : 123
             * COLNAME : 儿童歌谣
             */

            private String COLID;
            private String TYPE;
            private String REMARK;
            private String ID;
            private String NAME;
            private String SIZE;
            private String IMG;
            private String DUR;
            private String TIMES;
            private String COLNAME;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "COLID='" + COLID + '\'' +
                        ", TYPE='" + TYPE + '\'' +
                        ", REMARK='" + REMARK + '\'' +
                        ", ID='" + ID + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", SIZE='" + SIZE + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", DUR='" + DUR + '\'' +
                        ", TIMES='" + TIMES + '\'' +
                        ", COLNAME='" + COLNAME + '\'' +
                        '}';
            }

            public LSTBean(String COLID, String TYPE, String REMARK, String ID, String NAME, String SIZE, String IMG, String DUR, String TIMES,
                           String COLNAME) {
                this.COLID = COLID;
                this.TYPE = TYPE;
                this.REMARK = REMARK;
                this.ID = ID;
                this.NAME = NAME;
                this.SIZE = SIZE;
                this.IMG = IMG;
                this.DUR = DUR;
                this.TIMES = TIMES;
                this.COLNAME = COLNAME;
            }

            public String getCOLID() {
                return COLID;
            }

            public void setCOLID(String COLID) {
                this.COLID = COLID;
            }

            public String getTYPE() {
                return TYPE;
            }

            public void setTYPE(String TYPE) {
                this.TYPE = TYPE;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
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

            public String getSIZE() {
                return SIZE;
            }

            public void setSIZE(String SIZE) {
                this.SIZE = SIZE;
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

            public String getTIMES() {
                return TIMES;
            }

            public void setTIMES(String TIMES) {
                this.TIMES = TIMES;
            }

            public String getCOLNAME() {
                return COLNAME;
            }

            public void setCOLNAME(String COLNAME) {
                this.COLNAME = COLNAME;
            }
        }
    }
}
