package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by android on 2017/3/17.
 */
public class CallHistoryResultBean {
    @Override
    public String toString() {
        return "CallHistoryResultBean{" +
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

    public CallHistoryResultBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
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

    /**
     * TYPE : RES
     * CMD : MTALK
     * ACCT : 13628327181
     * TIME : 20170316234254164
     * BODY : {"PN":"1","NC":"52","CNT":"62","LST":[{"USERID":"201612291546141016532953","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","ENDTIME":"","TOYNICK":"","USERNAME":"小苹果","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","TOYID":"201611032024521016432810","USERNICK":"大天才","BEGINTIME":"20170220140348"},{"USERID":"201612291546141016532953","TOYNICK":"","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","USERNICK":"大天才","BEGINTIME":"20170220140150","TOYID":"201611032024521016432810","ENDTIME":"","USERNAME":"小苹果","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png"},{"USERID":"201612291546141016532953","TOYID":"201611032024521016432810","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","TOYNICK":"","USERNAME":"小苹果","ENDTIME":"","BEGINTIME":"20170220135933","USERNICK":"大天才","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg"},{"USERNAME":"小苹果","TOYID":"201611032024521016432810","TOYNICK":"","USERNICK":"大天才","USERID":"201612291546141016532953","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","BEGINTIME":"20170220135736","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","ENDTIME":""},{"USERID":"201612291546141016532953","BEGINTIME":"20170220135557","TOYNICK":"","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","USERNICK":"大天才","ENDTIME":"","USERNAME":"小苹果","TOYID":"201611032024521016432810","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png"},{"TOYID":"201611032024521016432810","USERNICK":"大天才","USERNAME":"小苹果","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","BEGINTIME":"20170220135429","USERID":"201612291546141016532953","TOYNICK":"","ENDTIME":""},{"TOYNICK":"","USERID":"201612291546141016532953","BEGINTIME":"20170220135320","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","USERNAME":"小苹果","USERNICK":"大天才","ENDTIME":"","TOYID":"201611032024521016432810"},{"BEGINTIME":"20170220135102","TOYID":"201611032024521016432810","ENDTIME":"","USERNICK":"大天才","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","USERNAME":"小苹果","USERID":"201612291546141016532953","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","TOYNICK":""},{"USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","BEGINTIME":"20170220134236","TOYID":"201611032024521016432810","USERID":"201612291546141016532953","TOYNICK":"","USERNAME":"小苹果","USERNICK":"大天才","ENDTIME":""},{"USERID":"201612291546141016532953","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","TOYNICK":"","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","TOYID":"201611032024521016432810","USERNAME":"小苹果","USERNICK":"大天才","ENDTIME":"","BEGINTIME":"20170209133720"}],"PS":"10"}
     * VERI :
     * TOKEN : ce6f6e66-9634-4727-84bd-dc90c79e3355
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

    public static class BODYBean {
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

        /**
         * PN : 1
         * NC : 52
         * CNT : 62
         * LST : [{"USERID":"201612291546141016532953","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","ENDTIME":"","TOYNICK":"","USERNAME":"小苹果","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","TOYID":"201611032024521016432810","USERNICK":"大天才","BEGINTIME":"20170220140348"},{"USERID":"201612291546141016532953","TOYNICK":"","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","USERNICK":"大天才","BEGINTIME":"20170220140150","TOYID":"201611032024521016432810","ENDTIME":"","USERNAME":"小苹果","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png"},{"USERID":"201612291546141016532953","TOYID":"201611032024521016432810","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","TOYNICK":"","USERNAME":"小苹果","ENDTIME":"","BEGINTIME":"20170220135933","USERNICK":"大天才","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg"},{"USERNAME":"小苹果","TOYID":"201611032024521016432810","TOYNICK":"","USERNICK":"大天才","USERID":"201612291546141016532953","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","BEGINTIME":"20170220135736","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","ENDTIME":""},{"USERID":"201612291546141016532953","BEGINTIME":"20170220135557","TOYNICK":"","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","USERNICK":"大天才","ENDTIME":"","USERNAME":"小苹果","TOYID":"201611032024521016432810","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png"},{"TOYID":"201611032024521016432810","USERNICK":"大天才","USERNAME":"小苹果","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","BEGINTIME":"20170220135429","USERID":"201612291546141016532953","TOYNICK":"","ENDTIME":""},{"TOYNICK":"","USERID":"201612291546141016532953","BEGINTIME":"20170220135320","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","USERNAME":"小苹果","USERNICK":"大天才","ENDTIME":"","TOYID":"201611032024521016432810"},{"BEGINTIME":"20170220135102","TOYID":"201611032024521016432810","ENDTIME":"","USERNICK":"大天才","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","USERNAME":"小苹果","USERID":"201612291546141016532953","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","TOYNICK":""},{"USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","BEGINTIME":"20170220134236","TOYID":"201611032024521016432810","USERID":"201612291546141016532953","TOYNICK":"","USERNAME":"小苹果","USERNICK":"大天才","ENDTIME":""},{"USERID":"201612291546141016532953","TOYIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","TOYNICK":"","USERIMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png","TOYID":"201611032024521016432810","USERNAME":"小苹果","USERNICK":"大天才","ENDTIME":"","BEGINTIME":"20170209133720"}]
         * PS : 10
         */

        private String PN;
        private String NC;
        private String CNT;
        private String PS;
        private List<LSTBean> LST;

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
            @Override
            public String toString() {
                return "LSTBean{" +
                        "USERID='" + USERID + '\'' +
                        ", USERIMG='" + USERIMG + '\'' +
                        ", ENDTIME='" + ENDTIME + '\'' +
                        ", TOYNICK='" + TOYNICK + '\'' +
                        ", USERNAME='" + USERNAME + '\'' +
                        ", TOYIMG='" + TOYIMG + '\'' +
                        ", TOYID='" + TOYID + '\'' +
                        ", USERNICK='" + USERNICK + '\'' +
                        ", BEGINTIME='" + BEGINTIME + '\'' +
                        '}';
            }

            public LSTBean(String USERID, String USERIMG, String ENDTIME, String TOYNICK, String USERNAME, String TOYIMG, String TOYID, String USERNICK, String BEGINTIME) {
                this.USERID = USERID;
                this.USERIMG = USERIMG;
                this.ENDTIME = ENDTIME;
                this.TOYNICK = TOYNICK;
                this.USERNAME = USERNAME;
                this.TOYIMG = TOYIMG;
                this.TOYID = TOYID;
                this.USERNICK = USERNICK;
                this.BEGINTIME = BEGINTIME;
            }

            /**
             * USERID : 201612291546141016532953
             * USERIMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/1/png/20170206/201702061357391016563212.png
             * ENDTIME :
             * TOYNICK :
             * USERNAME : 小苹果
             * TOYIMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg
             * TOYID : 201611032024521016432810
             * USERNICK : 大天才
             * BEGINTIME : 20170220140348
             */

            private String USERID;
            private String USERIMG;
            private String ENDTIME;
            private String TOYNICK;
            private String USERNAME;
            private String TOYIMG;
            private String TOYID;
            private String USERNICK;
            private String BEGINTIME;

            public String getUSERID() {
                return USERID;
            }

            public void setUSERID(String USERID) {
                this.USERID = USERID;
            }

            public String getUSERIMG() {
                return USERIMG;
            }

            public void setUSERIMG(String USERIMG) {
                this.USERIMG = USERIMG;
            }

            public String getENDTIME() {
                return ENDTIME;
            }

            public void setENDTIME(String ENDTIME) {
                this.ENDTIME = ENDTIME;
            }

            public String getTOYNICK() {
                return TOYNICK;
            }

            public void setTOYNICK(String TOYNICK) {
                this.TOYNICK = TOYNICK;
            }

            public String getUSERNAME() {
                return USERNAME;
            }

            public void setUSERNAME(String USERNAME) {
                this.USERNAME = USERNAME;
            }

            public String getTOYIMG() {
                return TOYIMG;
            }

            public void setTOYIMG(String TOYIMG) {
                this.TOYIMG = TOYIMG;
            }

            public String getTOYID() {
                return TOYID;
            }

            public void setTOYID(String TOYID) {
                this.TOYID = TOYID;
            }

            public String getUSERNICK() {
                return USERNICK;
            }

            public void setUSERNICK(String USERNICK) {
                this.USERNICK = USERNICK;
            }

            public String getBEGINTIME() {
                return BEGINTIME;
            }

            public void setBEGINTIME(String BEGINTIME) {
                this.BEGINTIME = BEGINTIME;
            }
        }
    }
}
