package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/6/14.
 */

public class QueryMyPlayResBean {
    /**
     * TYPE : RES
     * CMD : MYPLAY
     * ACCT : 13628327181
     * TIME : 20170614144140536
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"PN":"1","NC":"257","CNT":"267","LST":[{"COLID":"201702091130511016563465 ","BEGINTIME":"20170614094053",
     * "RCDID":"201706140940531016824448","REMARK":"","ENDTIME":"","IMG":"","TYPE":"音频文件","COLNAME":"英语启蒙 ","ID":"201706041509200000008485",
     * "DUR":"0:00:38","SIZE":"602.90KB","TIMES":"5","NAME":"剑桥幼儿英语35"},{"COLID":"201702091131341016563469 ","DUR":"0:10:33","TYPE":"音频文件",
     * "RCDID":"201706131802041016824447","SIZE":"9.68MB","REMARK":"","COLNAME":"绘本故事 ","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","ID":"201705151824191016803202","NAME":"猪八戒吃西瓜",
     * "BEGINTIME":"20170613180204","ENDTIME":"","TIMES":"112"},{"ENDTIME":"","SIZE":"9.68MB","BEGINTIME":"20170612154615","REMARK":"",
     * "COLNAME":"绘本故事 ","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg",
     * "RCDID":"201706121546141016824229","COLID":"201702091131341016563469 ","ID":"201705151824191016803202","TYPE":"音频文件","DUR":"0:10:33",
     * "NAME":"猪八戒吃西瓜","TIMES":"112"},{"RCDID":"201706121123071016824228","SIZE":"4.25MB","TIMES":"3","NAME":"渔童","BEGINTIME":"20170612112307",
     * "TYPE":"音频文件","ENDTIME":"","IMG":"","COLID":"201702091131241016563468 ","DUR":"0:04:38","REMARK":"","COLNAME":"睡前故事 ",
     * "ID":"201706041509200000012260"},{"NAME":"花木兰替父从军","ENDTIME":"","IMG":"","COLID":"201702091131241016563468 ","BEGINTIME":"20170612112305",
     * "DUR":"0:06:00","RCDID":"201706121123041016824227","REMARK":"","TYPE":"音频文件","SIZE":"2.76MB","ID":"201706041509200000012262","TIMES":"1",
     * "COLNAME":"睡前故事 "},{"ENDTIME":"","NAME":"脏嘴巴的小白兔","RCDID":"201706121122571016824226","COLNAME":"睡前故事 ","ID":"201706041509200000012264",
     * "REMARK":"","DUR":"0:03:17","IMG":"","TIMES":"1","BEGINTIME":"20170612112257","TYPE":"音频文件","COLID":"201702091131241016563468 ","SIZE":"1
     * .51MB"},{"COLID":"201702091131241016563468 ","NAME":"小斑马过河","TIMES":"21","SIZE":"2.05MB","ID":"201705151810031016803167","IMG":"http://120
     * .27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151810031016803168.jpg","BEGINTIME":"20170612092428","TYPE":"音频文件",
     * "COLNAME":"睡前故事 ","DUR":"0:04:28","RCDID":"201706120924281016824213","REMARK":"","ENDTIME":""},{"TYPE":"音频文件","ENDTIME":"","COLNAME":"绘本故事
     * ","DUR":"0:10:33","BEGINTIME":"20170610164731","REMARK":"","RCDID":"201706101647301016824143","TIMES":"112","ID":"201705151824191016803202",
     * "SIZE":"9.68MB","COLID":"201702091131341016563469 ","NAME":"猪八戒吃西瓜","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg"},{"DUR":"0:10:33","TYPE":"音频文件",
     * "COLID":"201702091131341016563469 ","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206
     * .jpg","SIZE":"9.68MB","ID":"201705151824191016803202","REMARK":"","ENDTIME":"","RCDID":"201706101647161016824142","TIMES":"112",
     * "BEGINTIME":"20170610164717","COLNAME":"绘本故事 ","NAME":"猪八戒吃西瓜"},{"BEGINTIME":"20170610164646","NAME":"猪八戒吃西瓜","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","ENDTIME":"","TIMES":"112",
     * "ID":"201705151824191016803202","TYPE":"音频文件","DUR":"0:10:33","SIZE":"9.68MB","COLID":"201702091131341016563469 ","COLNAME":"绘本故事 ",
     * "RCDID":"201706101646461016824141","REMARK":""}],"PS":"10"}
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
        return "QueryMyPlayResBean{" +
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

    public QueryMyPlayResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean BODY,
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
         * PN : 1
         * NC : 257
         * CNT : 267
         * LST : [{"COLID":"201702091130511016563465 ","BEGINTIME":"20170614094053","RCDID":"201706140940531016824448","REMARK":"","ENDTIME":"",
         * "IMG":"","TYPE":"音频文件","COLNAME":"英语启蒙 ","ID":"201706041509200000008485","DUR":"0:00:38","SIZE":"602.90KB","TIMES":"5",
         * "NAME":"剑桥幼儿英语35"},{"COLID":"201702091131341016563469 ","DUR":"0:10:33","TYPE":"音频文件","RCDID":"201706131802041016824447","SIZE":"9
         * .68MB","REMARK":"","COLNAME":"绘本故事 ","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206
         * .jpg","ID":"201705151824191016803202","NAME":"猪八戒吃西瓜","BEGINTIME":"20170613180204","ENDTIME":"","TIMES":"112"},{"ENDTIME":"","SIZE":"9
         * .68MB","BEGINTIME":"20170612154615","REMARK":"","COLNAME":"绘本故事 ","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","RCDID":"201706121546141016824229",
         * "COLID":"201702091131341016563469 ","ID":"201705151824191016803202","TYPE":"音频文件","DUR":"0:10:33","NAME":"猪八戒吃西瓜","TIMES":"112"},
         * {"RCDID":"201706121123071016824228","SIZE":"4.25MB","TIMES":"3","NAME":"渔童","BEGINTIME":"20170612112307","TYPE":"音频文件","ENDTIME":"",
         * "IMG":"","COLID":"201702091131241016563468 ","DUR":"0:04:38","REMARK":"","COLNAME":"睡前故事 ","ID":"201706041509200000012260"},
         * {"NAME":"花木兰替父从军","ENDTIME":"","IMG":"","COLID":"201702091131241016563468 ","BEGINTIME":"20170612112305","DUR":"0:06:00",
         * "RCDID":"201706121123041016824227","REMARK":"","TYPE":"音频文件","SIZE":"2.76MB","ID":"201706041509200000012262","TIMES":"1","COLNAME":"睡前故事
         * "},{"ENDTIME":"","NAME":"脏嘴巴的小白兔","RCDID":"201706121122571016824226","COLNAME":"睡前故事 ","ID":"201706041509200000012264","REMARK":"",
         * "DUR":"0:03:17","IMG":"","TIMES":"1","BEGINTIME":"20170612112257","TYPE":"音频文件","COLID":"201702091131241016563468 ","SIZE":"1.51MB"},
         * {"COLID":"201702091131241016563468 ","NAME":"小斑马过河","TIMES":"21","SIZE":"2.05MB","ID":"201705151810031016803167","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151810031016803168.jpg","BEGINTIME":"20170612092428","TYPE":"音频文件",
         * "COLNAME":"睡前故事 ","DUR":"0:04:28","RCDID":"201706120924281016824213","REMARK":"","ENDTIME":""},{"TYPE":"音频文件","ENDTIME":"",
         * "COLNAME":"绘本故事 ","DUR":"0:10:33","BEGINTIME":"20170610164731","REMARK":"","RCDID":"201706101647301016824143","TIMES":"112",
         * "ID":"201705151824191016803202","SIZE":"9.68MB","COLID":"201702091131341016563469 ","NAME":"猪八戒吃西瓜","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg"},{"DUR":"0:10:33","TYPE":"音频文件",
         * "COLID":"201702091131341016563469 ","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206
         * .jpg","SIZE":"9.68MB","ID":"201705151824191016803202","REMARK":"","ENDTIME":"","RCDID":"201706101647161016824142","TIMES":"112",
         * "BEGINTIME":"20170610164717","COLNAME":"绘本故事 ","NAME":"猪八戒吃西瓜"},{"BEGINTIME":"20170610164646","NAME":"猪八戒吃西瓜","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","ENDTIME":"","TIMES":"112",
         * "ID":"201705151824191016803202","TYPE":"音频文件","DUR":"0:10:33","SIZE":"9.68MB","COLID":"201702091131341016563469 ","COLNAME":"绘本故事 ",
         * "RCDID":"201706101646461016824141","REMARK":""}]
         * PS : 10
         */

        private String PN;
        private String NC;
        private String CNT;
        private String PS;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PN='" + PN + '\'' +
                    ", NC='" + NC + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", PS='" + PS + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PN, String NC, String CNT, String PS, List<LSTBean> LST) {
            this.PN = PN;
            this.NC = NC;
            this.CNT = CNT;
            this.PS = PS;
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

        public String getCNT() {
            return CNT;
        }

        public void setCNT(String CNT) {
            this.CNT = CNT;
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
             * COLID : 201702091130511016563465
             * BEGINTIME : 20170614094053
             * RCDID : 201706140940531016824448
             * REMARK :
             * ENDTIME :
             * IMG :
             * TYPE : 音频文件
             * COLNAME : 英语启蒙
             * ID : 201706041509200000008485
             * DUR : 0:00:38
             * SIZE : 602.90KB
             * TIMES : 5
             * NAME : 剑桥幼儿英语35
             */

            private String COLID;
            private String BEGINTIME;
            private String RCDID;
            private String REMARK;
            private String ENDTIME;
            private String IMG;
            private String TYPE;
            private String COLNAME;
            private String ID;
            private String DUR;
            private String SIZE;
            private String TIMES;
            private String NAME;

            public String getCOLID() {
                return COLID;
            }

            public void setCOLID(String COLID) {
                this.COLID = COLID;
            }

            public String getBEGINTIME() {
                return BEGINTIME;
            }

            public void setBEGINTIME(String BEGINTIME) {
                this.BEGINTIME = BEGINTIME;
            }

            public String getRCDID() {
                return RCDID;
            }

            public void setRCDID(String RCDID) {
                this.RCDID = RCDID;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getENDTIME() {
                return ENDTIME;
            }

            public void setENDTIME(String ENDTIME) {
                this.ENDTIME = ENDTIME;
            }

            public String getIMG() {
                return IMG;
            }

            public void setIMG(String IMG) {
                this.IMG = IMG;
            }

            public String getTYPE() {
                return TYPE;
            }

            public void setTYPE(String TYPE) {
                this.TYPE = TYPE;
            }

            public String getCOLNAME() {
                return COLNAME;
            }

            public void setCOLNAME(String COLNAME) {
                this.COLNAME = COLNAME;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getDUR() {
                return DUR;
            }

            public void setDUR(String DUR) {
                this.DUR = DUR;
            }

            public String getSIZE() {
                return SIZE;
            }

            public void setSIZE(String SIZE) {
                this.SIZE = SIZE;
            }

            public String getTIMES() {
                return TIMES;
            }

            public void setTIMES(String TIMES) {
                this.TIMES = TIMES;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }
        }
    }
}
