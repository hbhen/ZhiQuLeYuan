package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/6/14.
 */

public class QueryMyPlayResBean {


    /**
     * TYPE : RES
     * CMD : MYPLAY
     * ACCT : XXXX
     * TIME : 20170709181923537
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"CNT":"373","PN":"1","PS":"10","LST":[{"ID":"201707080933031016840316","COLNAME":"","COLID":"","REMARK":"","TIMES":"11",
     * "RCDID":"201707091812101016840441","SIZE":"25.40KB","ENDTIME":"","IMG":"","NAME":"2017年07月08日09:32","BEGINTIME":"20170709181211",
     * "DUR":"0:00:06","TYPE":""},{"BEGINTIME":"20170709181208","COLID":"","DUR":"0:00:01","NAME":"2017年07月09日 11时50分50秒.mp3",
     * "ID":"201707091151021016840390","SIZE":"3.48KB","ENDTIME":"","RCDID":"201707091812071016840440","TIMES":"3","TYPE":"","IMG":"","REMARK":"",
     * "COLNAME":""},{"BEGINTIME":"20170709181204","IMG":"","COLNAME":"","TYPE":"","REMARK":"","TIMES":"12","ENDTIME":"","DUR":"0:00:08",
     * "NAME":"2017年07月09日 11时02分52秒.mp3","COLID":"","RCDID":"201707091812041016840439","SIZE":"13.52KB","ID":"201707091103161016840374"},
     * {"RCDID":"201707091812011016840438","SIZE":"9.68MB","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","DUR":"0:10:33","TYPE":"音频文件","NAME":"猪八戒吃西瓜",
     * "COLNAME":"绘本故事 ","REMARK":"","COLID":"201702091131341016563469 ","ID":"201705151824191016803202","TIMES":"305",
     * "BEGINTIME":"20170709181202","ENDTIME":""},{"REMARK":"","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","RCDID":"201707091738291016840433","SIZE":"9.68MB",
     * "COLNAME":"绘本故事 ","DUR":"0:10:33","TYPE":"音频文件","BEGINTIME":"20170709173830","COLID":"201702091131341016563469 ","NAME":"猪八戒吃西瓜",
     * "ID":"201705151824191016803202","TIMES":"305","ENDTIME":""},{"TIMES":"305","COLID":"201702091131341016563469 ","BEGINTIME":"20170709122233",
     * "NAME":"猪八戒吃西瓜","ENDTIME":"","SIZE":"9.68MB","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","TYPE":"音频文件","ID":"201705151824191016803202",
     * "COLNAME":"绘本故事 ","DUR":"0:10:33","RCDID":"201707091222321016840402","REMARK":""},{"TYPE":"","COLNAME":"",
     * "RCDID":"201707091222121016840401","NAME":"2017年07月09日 11时02分52秒.mp3","COLID":"","DUR":"0:00:08","ID":"201707091103161016840374",
     * "TIMES":"12","ENDTIME":"","IMG":"","REMARK":"","BEGINTIME":"20170709122212","SIZE":"13.52KB"},{"BEGINTIME":"20170709121650","IMG":"",
     * "ENDTIME":"","NAME":"2017年07月09日 11时50分50秒.mp3","TYPE":"","TIMES":"3","REMARK":"","COLNAME":"","ID":"201707091151021016840390",
     * "RCDID":"201707091216501016840400","COLID":"","DUR":"0:00:01","SIZE":"3.48KB"},{"COLID":"","NAME":"2017年07月09日 11时02分52秒.mp3","IMG":"",
     * "COLNAME":"","TYPE":"","ID":"201707091103161016840374","SIZE":"13.52KB","BEGINTIME":"20170709120916","TIMES":"12","DUR":"0:00:08",
     * "ENDTIME":"","REMARK":"","RCDID":"201707091209161016840399"},{"SIZE":"13.52KB","IMG":"","REMARK":"","TIMES":"12","DUR":"0:00:08",
     * "ENDTIME":"","COLID":"","NAME":"2017年07月09日 11时02分52秒.mp3","TYPE":"","BEGINTIME":"20170709120916","COLNAME":"",
     * "ID":"201707091103161016840374","RCDID":"201707091209161016840398"}],"NC":"363"}
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
         * CNT : 373
         * PN : 1
         * PS : 10
         * LST : [{"ID":"201707080933031016840316","COLNAME":"","COLID":"","REMARK":"","TIMES":"11","RCDID":"201707091812101016840441","SIZE":"25
         * .40KB","ENDTIME":"","IMG":"","NAME":"2017年07月08日09:32","BEGINTIME":"20170709181211","DUR":"0:00:06","TYPE":""},
         * {"BEGINTIME":"20170709181208","COLID":"","DUR":"0:00:01","NAME":"2017年07月09日 11时50分50秒.mp3","ID":"201707091151021016840390","SIZE":"3
         * .48KB","ENDTIME":"","RCDID":"201707091812071016840440","TIMES":"3","TYPE":"","IMG":"","REMARK":"","COLNAME":""},
         * {"BEGINTIME":"20170709181204","IMG":"","COLNAME":"","TYPE":"","REMARK":"","TIMES":"12","ENDTIME":"","DUR":"0:00:08","NAME":"2017年07月09日
         * 11时02分52秒.mp3","COLID":"","RCDID":"201707091812041016840439","SIZE":"13.52KB","ID":"201707091103161016840374"},
         * {"RCDID":"201707091812011016840438","SIZE":"9.68MB","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","DUR":"0:10:33","TYPE":"音频文件","NAME":"猪八戒吃西瓜",
         * "COLNAME":"绘本故事 ","REMARK":"","COLID":"201702091131341016563469 ","ID":"201705151824191016803202","TIMES":"305",
         * "BEGINTIME":"20170709181202","ENDTIME":""},{"REMARK":"","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","RCDID":"201707091738291016840433","SIZE":"9.68MB",
         * "COLNAME":"绘本故事 ","DUR":"0:10:33","TYPE":"音频文件","BEGINTIME":"20170709173830","COLID":"201702091131341016563469 ","NAME":"猪八戒吃西瓜",
         * "ID":"201705151824191016803202","TIMES":"305","ENDTIME":""},{"TIMES":"305","COLID":"201702091131341016563469 ",
         * "BEGINTIME":"20170709122233","NAME":"猪八戒吃西瓜","ENDTIME":"","SIZE":"9.68MB","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","TYPE":"音频文件","ID":"201705151824191016803202",
         * "COLNAME":"绘本故事 ","DUR":"0:10:33","RCDID":"201707091222321016840402","REMARK":""},{"TYPE":"","COLNAME":"",
         * "RCDID":"201707091222121016840401","NAME":"2017年07月09日 11时02分52秒.mp3","COLID":"","DUR":"0:00:08","ID":"201707091103161016840374",
         * "TIMES":"12","ENDTIME":"","IMG":"","REMARK":"","BEGINTIME":"20170709122212","SIZE":"13.52KB"},{"BEGINTIME":"20170709121650","IMG":"",
         * "ENDTIME":"","NAME":"2017年07月09日 11时50分50秒.mp3","TYPE":"","TIMES":"3","REMARK":"","COLNAME":"","ID":"201707091151021016840390",
         * "RCDID":"201707091216501016840400","COLID":"","DUR":"0:00:01","SIZE":"3.48KB"},{"COLID":"","NAME":"2017年07月09日 11时02分52秒.mp3","IMG":"",
         * "COLNAME":"","TYPE":"","ID":"201707091103161016840374","SIZE":"13.52KB","BEGINTIME":"20170709120916","TIMES":"12","DUR":"0:00:08",
         * "ENDTIME":"","REMARK":"","RCDID":"201707091209161016840399"},{"SIZE":"13.52KB","IMG":"","REMARK":"","TIMES":"12","DUR":"0:00:08",
         * "ENDTIME":"","COLID":"","NAME":"2017年07月09日 11时02分52秒.mp3","TYPE":"","BEGINTIME":"20170709120916","COLNAME":"",
         * "ID":"201707091103161016840374","RCDID":"201707091209161016840398"}]
         * NC : 363
         */

        private String CNT;
        private String PN;
        private String PS;
        private String NC;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "CNT='" + CNT + '\'' +
                    ", PN='" + PN + '\'' +
                    ", PS='" + PS + '\'' +
                    ", NC='" + NC + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String CNT, String PN, String PS, String NC, List<LSTBean> LST) {
            this.CNT = CNT;
            this.PN = PN;
            this.PS = PS;
            this.NC = NC;
            this.LST = LST;
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
             * ID : 201707080933031016840316
             * COLNAME :
             * COLID :
             * REMARK :
             * TIMES : 11
             * RCDID : 201707091812101016840441
             * SIZE : 25.40KB
             * ENDTIME :
             * IMG :
             * NAME : 2017年07月08日09:32
             * BEGINTIME : 20170709181211
             * DUR : 0:00:06
             * TYPE :
             */

            private String ID;
            private String COLNAME;
            private String COLID;
            private String REMARK;
            private String TIMES;
            private String RCDID;
            private String SIZE;
            private String ENDTIME;
            private String IMG;
            private String NAME;
            private String BEGINTIME;
            private String DUR;
            private String TYPE;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getCOLNAME() {
                return COLNAME;
            }

            public void setCOLNAME(String COLNAME) {
                this.COLNAME = COLNAME;
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

            public String getTIMES() {
                return TIMES;
            }

            public void setTIMES(String TIMES) {
                this.TIMES = TIMES;
            }

            public String getRCDID() {
                return RCDID;
            }

            public void setRCDID(String RCDID) {
                this.RCDID = RCDID;
            }

            public String getSIZE() {
                return SIZE;
            }

            public void setSIZE(String SIZE) {
                this.SIZE = SIZE;
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

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getBEGINTIME() {
                return BEGINTIME;
            }

            public void setBEGINTIME(String BEGINTIME) {
                this.BEGINTIME = BEGINTIME;
            }

            public String getDUR() {
                return DUR;
            }

            public void setDUR(String DUR) {
                this.DUR = DUR;
            }

            public String getTYPE() {
                return TYPE;
            }

            public void setTYPE(String TYPE) {
                this.TYPE = TYPE;
            }
        }
    }
}
