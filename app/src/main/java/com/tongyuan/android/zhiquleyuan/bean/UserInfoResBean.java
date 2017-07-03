package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/7/3.
 */
public class UserInfoResBean {
    @Override
    public String toString() {
        return "UserInfoResBean{" +
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

    public UserInfoResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean BODY,
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

    /**
     * TYPE : RES
     * CMD : INFO
     * ACCT : XXXX
     * TIME : 20170703113617234
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"TYPE":"USER","NAME":"大天才","ID":"201612291546141016532953","SEX":"男","ONLINE":"1","BIRTHDAY":"20161229000000","NICK":"大天才",
     * "IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/1/jpg/20170629/201706291609381016839324.jpg","STATE":"激活"}
     * TOKEN : 70d276f2-07d6-42ae-be07-cf562ee2ec67
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
         * TYPE : USER
         * NAME : 大天才
         * ID : 201612291546141016532953
         * SEX : 男
         * ONLINE : 1
         * BIRTHDAY : 20161229000000
         * NICK : 大天才
         * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/1/jpg/20170629/201706291609381016839324.jpg
         * STATE : 激活
         */

        private String TYPE;
        private String NAME;
        private String ID;
        private String SEX;
        private String ONLINE;
        private String BIRTHDAY;
        private String NICK;
        private String IMG;
        private String STATE;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "TYPE='" + TYPE + '\'' +
                    ", NAME='" + NAME + '\'' +
                    ", ID='" + ID + '\'' +
                    ", SEX='" + SEX + '\'' +
                    ", ONLINE='" + ONLINE + '\'' +
                    ", BIRTHDAY='" + BIRTHDAY + '\'' +
                    ", NICK='" + NICK + '\'' +
                    ", IMG='" + IMG + '\'' +
                    ", STATE='" + STATE + '\'' +
                    '}';
        }

        public BODYBean(String TYPE, String NAME, String ID, String SEX, String ONLINE, String BIRTHDAY, String NICK, String IMG, String STATE) {
            this.TYPE = TYPE;
            this.NAME = NAME;
            this.ID = ID;
            this.SEX = SEX;
            this.ONLINE = ONLINE;
            this.BIRTHDAY = BIRTHDAY;
            this.NICK = NICK;
            this.IMG = IMG;
            this.STATE = STATE;
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

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSEX() {
            return SEX;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
        }

        public String getONLINE() {
            return ONLINE;
        }

        public void setONLINE(String ONLINE) {
            this.ONLINE = ONLINE;
        }

        public String getBIRTHDAY() {
            return BIRTHDAY;
        }

        public void setBIRTHDAY(String BIRTHDAY) {
            this.BIRTHDAY = BIRTHDAY;
        }

        public String getNICK() {
            return NICK;
        }

        public void setNICK(String NICK) {
            this.NICK = NICK;
        }

        public String getIMG() {
            return IMG;
        }

        public void setIMG(String IMG) {
            this.IMG = IMG;
        }

        public String getSTATE() {
            return STATE;
        }

        public void setSTATE(String STATE) {
            this.STATE = STATE;
        }
    }
}
