package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/6/14.
 */
public class QueryMyCollectionResBean {

    /**
     * TYPE : RES
     * CMD : MYFAV
     * ACCT : XXXX
     * TIME : 20170709182701564
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"PN":"1","PS":"10","CNT":"2","LST":[{"SIZE":"2.05MB","COLID":"201702091131241016563468 ","REMARK":"","COLNAME":"睡前故事 ",
     * "TYPE":"音频文件","NAME":"小斑马过河","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151810031016803168.jpg",
     * "ID":"201705151810031016803167","TIMES":"78","DUR":"0:04:28"},{"COLNAME":"睡前故事 ","REMARK":"","SIZE":"954.95KB","TYPE":"音频文件",
     * "DUR":"0:02:02","ID":"201706041509200000012266","COLID":"201702091131241016563468 ","TIMES":"23","NAME":"愚公移山","IMG":""}],"NC":"0"}
     * TOKEN : 2a9f39b9-489c-4f29-a4f4-3e8da79dc08e
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
         * CNT : 2
         * LST : [{"SIZE":"2.05MB","COLID":"201702091131241016563468 ","REMARK":"","COLNAME":"睡前故事 ","TYPE":"音频文件","NAME":"小斑马过河","IMG":"http://120
         * .27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151810031016803168.jpg","ID":"201705151810031016803167","TIMES":"78",
         * "DUR":"0:04:28"},{"COLNAME":"睡前故事 ","REMARK":"","SIZE":"954.95KB","TYPE":"音频文件","DUR":"0:02:02","ID":"201706041509200000012266",
         * "COLID":"201702091131241016563468 ","TIMES":"23","NAME":"愚公移山","IMG":""}]
         * NC : 0
         */

        private String PN;
        private String PS;
        private String CNT;
        private String NC;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PN='" + PN + '\'' +
                    ", PS='" + PS + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", NC='" + NC + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PN, String PS, String CNT, String NC, List<LSTBean> LST) {
            this.PN = PN;
            this.PS = PS;
            this.CNT = CNT;
            this.NC = NC;
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

        public String getCNT() {
            return CNT;
        }

        public void setCNT(String CNT) {
            this.CNT = CNT;
        }

        public String getNC() {
            return NC;
        }

        public void setNC(String NC) {
            this.NC = NC;
        }

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean {
            /**
             * SIZE : 2.05MB
             * COLID : 201702091131241016563468
             * REMARK :
             * COLNAME : 睡前故事
             * TYPE : 音频文件
             * NAME : 小斑马过河
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151810031016803168.jpg
             * ID : 201705151810031016803167
             * TIMES : 78
             * DUR : 0:04:28
             */

            private String SIZE;
            private String COLID;
            private String REMARK;
            private String COLNAME;
            private String TYPE;
            private String NAME;
            private String IMG;
            private String ID;
            private String TIMES;
            private String DUR;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "SIZE='" + SIZE + '\'' +
                        ", COLID='" + COLID + '\'' +
                        ", REMARK='" + REMARK + '\'' +
                        ", COLNAME='" + COLNAME + '\'' +
                        ", TYPE='" + TYPE + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", ID='" + ID + '\'' +
                        ", TIMES='" + TIMES + '\'' +
                        ", DUR='" + DUR + '\'' +
                        '}';
            }

            public LSTBean(String SIZE, String COLID, String REMARK, String COLNAME, String TYPE, String NAME, String IMG, String ID, String TIMES,
                           String DUR) {
                this.SIZE = SIZE;
                this.COLID = COLID;
                this.REMARK = REMARK;
                this.COLNAME = COLNAME;
                this.TYPE = TYPE;
                this.NAME = NAME;
                this.IMG = IMG;
                this.ID = ID;
                this.TIMES = TIMES;
                this.DUR = DUR;
            }

            public String getSIZE() {
                return SIZE;
            }

            public void setSIZE(String SIZE) {
                this.SIZE = SIZE;
            }

            public String getCOLID() {
                return COLID;
            }

            public void setCOLID(String COLID) {
                this.COLID = COLID;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getCOLNAME() {
                return COLNAME;
            }

            public void setCOLNAME(String COLNAME) {
                this.COLNAME = COLNAME;
            }

            public String getTYPE() {
                return TYPE;
            }

            public void setTYPE(String TYPE) {
                this.TYPE = TYPE;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getIMG() {
                return IMG;
            }

            public void setIMG(String IMG) {
                this.IMG = IMG;
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

            public String getDUR() {
                return DUR;
            }

            public void setDUR(String DUR) {
                this.DUR = DUR;
            }
        }
    }
}
