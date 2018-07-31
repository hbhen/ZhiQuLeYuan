package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by DTC on 2018/7/28.
 */

public class QueryAllToyListResBean {
    @Override
    public String toString() {
        return "QueryAllToyListResBean{" +
                "CODE='" + CODE + '\'' +
                ", MSG='" + MSG + '\'' +
                ", TYPE='" + TYPE + '\'' +
                ", TIME='" + TIME + '\'' +
                ", BODY=" + BODY +
                ", TOKEN='" + TOKEN + '\'' +
                ", CMD='" + CMD + '\'' +
                ", ACCT='" + ACCT + '\'' +
                ", VERI='" + VERI + '\'' +
                ", SEQ='" + SEQ + '\'' +
                '}';
    }

    public QueryAllToyListResBean(String CODE, String MSG, String TYPE, String TIME, BODYBean BODY, String TOKEN, String CMD, String ACCT, String VERI, String SEQ) {
        this.CODE = CODE;
        this.MSG = MSG;
        this.TYPE = TYPE;
        this.TIME = TIME;
        this.BODY = BODY;
        this.TOKEN = TOKEN;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.VERI = VERI;
        this.SEQ = SEQ;
    }

    /**
     * CODE : 0
     * MSG :
     * TYPE : RES
     * TIME : 20180728135407274
     * BODY : {"PN":"1","CNT":"2","LST":[{"STATE":"激活","CODE":"123400000000020","OWNERID":"201704011032301016584534","NICK":"123400000000020","ACTTIME":"20180720191210","IMG":"http://www.kinder-mate
     * .cn:8082/zqpland/resource/thumbnail/0/jpg/20170421/201704210943361016585040.jpg","OWNERIMG":"http://www.kinder-mate.cn:8082/zqpland/resource/thumbnail/1/jpg/20170618/201706182339461016836862.jpg","REMARK":"毛绒玩具","VOL":"8","BABYIMG":"",
     * "ONLINE":"1","NAME":"小狗白白","ELEC":"100","ID":"201705080954151016644003","OWNERNAME":"各个"},{"ID":"201611032024521016432810","OWNERNAME":"我","CODE":"869634020310101","NAME":"小狗白白","REMARK":"毛绒玩具","ONLINE":"1","ACTTIME":"20170622113208",
     * "STATE":"激活","OWNERIMG":"http://www.kinder-mate.cn:8082/zqpland/resource/thumbnail/1/jpg/20171213/201712131429491016917398.jpg","ELEC":"21","IMG":"http://www.kinder-mate
     * .cn:8082/zqpland/resource/thumbnail/0/jpg/20170421/201704210943361016585040.jpg","VOL":"10","OWNERID":"201705100943231016652953","NICK":"869634020310101","BABYIMG":""}],"PS":"10","NC":"0"}
     * TOKEN : 6d819da1-e2a6-47ab-af59-5a595286c5db
     * CMD : QRYTOYS
     * ACCT : XXXX
     * VERI :
     * SEQ : 1
     */

    private String CODE;
    private String MSG;
    private String TYPE;
    private String TIME;
    private BODYBean BODY;
    private String TOKEN;
    private String CMD;
    private String ACCT;
    private String VERI;
    private String SEQ;

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

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
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

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
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

    public static class BODYBean {
        /**
         * PN : 1
         * CNT : 2
         * LST : [{"STATE":"激活","CODE":"123400000000020","OWNERID":"201704011032301016584534","NICK":"123400000000020","ACTTIME":"20180720191210","IMG":"http://www.kinder-mate
         * .cn:8082/zqpland/resource/thumbnail/0/jpg/20170421/201704210943361016585040.jpg","OWNERIMG":"http://www.kinder-mate.cn:8082/zqpland/resource/thumbnail/1/jpg/20170618/201706182339461016836862.jpg","REMARK":"毛绒玩具","VOL":"8","BABYIMG":"",
         * "ONLINE":"1","NAME":"小狗白白","ELEC":"100","ID":"201705080954151016644003","OWNERNAME":"各个"},{"ID":"201611032024521016432810","OWNERNAME":"我","CODE":"869634020310101","NAME":"小狗白白","REMARK":"毛绒玩具","ONLINE":"1","ACTTIME":"20170622113208",
         * "STATE":"激活","OWNERIMG":"http://www.kinder-mate.cn:8082/zqpland/resource/thumbnail/1/jpg/20171213/201712131429491016917398.jpg","ELEC":"21","IMG":"http://www.kinder-mate
         * .cn:8082/zqpland/resource/thumbnail/0/jpg/20170421/201704210943361016585040.jpg","VOL":"10","OWNERID":"201705100943231016652953","NICK":"869634020310101","BABYIMG":""}]
         * PS : 10
         * NC : 0
         */

        private String PN;
        private String CNT;
        private String PS;
        private String NC;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PN='" + PN + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", PS='" + PS + '\'' +
                    ", NC='" + NC + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PN, String CNT, String PS, String NC, List<LSTBean> LST) {

            this.PN = PN;
            this.CNT = CNT;
            this.PS = PS;
            this.NC = NC;
            this.LST = LST;
        }

        public String getPN() {
            return PN;
        }

        public void setPN(String PN) {
            this.PN = PN;
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
             * STATE : 激活
             * CODE : 123400000000020
             * OWNERID : 201704011032301016584534
             * NICK : 123400000000020
             * ACTTIME : 20180720191210
             * IMG : http://www.kinder-mate.cn:8082/zqpland/resource/thumbnail/0/jpg/20170421/201704210943361016585040.jpg
             * OWNERIMG : http://www.kinder-mate.cn:8082/zqpland/resource/thumbnail/1/jpg/20170618/201706182339461016836862.jpg
             * REMARK : 毛绒玩具
             * VOL : 8
             * BABYIMG :
             * ONLINE : 1
             * NAME : 小狗白白
             * ELEC : 100
             * ID : 201705080954151016644003
             * OWNERNAME : 各个
             */

            private String STATE;
            private String CODE;
            private String OWNERID;
            private String NICK;
            private String ACTTIME;
            private String IMG;
            private String OWNERIMG;
            private String REMARK;
            private String VOL;
            private String BABYIMG;
            private String ONLINE;
            private String NAME;
            private String ELEC;
            private String ID;
            private String OWNERNAME;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "STATE='" + STATE + '\'' +
                        ", CODE='" + CODE + '\'' +
                        ", OWNERID='" + OWNERID + '\'' +
                        ", NICK='" + NICK + '\'' +
                        ", ACTTIME='" + ACTTIME + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", OWNERIMG='" + OWNERIMG + '\'' +
                        ", REMARK='" + REMARK + '\'' +
                        ", VOL='" + VOL + '\'' +
                        ", BABYIMG='" + BABYIMG + '\'' +
                        ", ONLINE='" + ONLINE + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", ELEC='" + ELEC + '\'' +
                        ", ID='" + ID + '\'' +
                        ", OWNERNAME='" + OWNERNAME + '\'' +
                        '}';
            }

            public LSTBean(String STATE, String CODE, String OWNERID, String NICK, String ACTTIME, String IMG, String OWNERIMG, String REMARK, String VOL, String BABYIMG, String ONLINE, String NAME, String ELEC, String ID, String OWNERNAME) {
                this.STATE = STATE;
                this.CODE = CODE;
                this.OWNERID = OWNERID;
                this.NICK = NICK;
                this.ACTTIME = ACTTIME;
                this.IMG = IMG;
                this.OWNERIMG = OWNERIMG;
                this.REMARK = REMARK;
                this.VOL = VOL;
                this.BABYIMG = BABYIMG;
                this.ONLINE = ONLINE;
                this.NAME = NAME;
                this.ELEC = ELEC;
                this.ID = ID;
                this.OWNERNAME = OWNERNAME;
            }

            public String getSTATE() {
                return STATE;
            }

            public void setSTATE(String STATE) {
                this.STATE = STATE;
            }

            public String getCODE() {
                return CODE;
            }

            public void setCODE(String CODE) {
                this.CODE = CODE;
            }

            public String getOWNERID() {
                return OWNERID;
            }

            public void setOWNERID(String OWNERID) {
                this.OWNERID = OWNERID;
            }

            public String getNICK() {
                return NICK;
            }

            public void setNICK(String NICK) {
                this.NICK = NICK;
            }

            public String getACTTIME() {
                return ACTTIME;
            }

            public void setACTTIME(String ACTTIME) {
                this.ACTTIME = ACTTIME;
            }

            public String getIMG() {
                return IMG;
            }

            public void setIMG(String IMG) {
                this.IMG = IMG;
            }

            public String getOWNERIMG() {
                return OWNERIMG;
            }

            public void setOWNERIMG(String OWNERIMG) {
                this.OWNERIMG = OWNERIMG;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getVOL() {
                return VOL;
            }

            public void setVOL(String VOL) {
                this.VOL = VOL;
            }

            public String getBABYIMG() {
                return BABYIMG;
            }

            public void setBABYIMG(String BABYIMG) {
                this.BABYIMG = BABYIMG;
            }

            public String getONLINE() {
                return ONLINE;
            }

            public void setONLINE(String ONLINE) {
                this.ONLINE = ONLINE;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getELEC() {
                return ELEC;
            }

            public void setELEC(String ELEC) {
                this.ELEC = ELEC;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getOWNERNAME() {
                return OWNERNAME;
            }

            public void setOWNERNAME(String OWNERNAME) {
                this.OWNERNAME = OWNERNAME;
            }
        }
    }
}
