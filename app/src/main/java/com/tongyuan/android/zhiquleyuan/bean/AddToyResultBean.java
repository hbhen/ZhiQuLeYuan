package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by android on 2017/3/13.
 */

public class AddToyResultBean {
    @Override
    public String toString() {
        return "AddToyResultBean{" +
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

    public AddToyResultBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
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
     * CMD : ATOY
     * ACCT : 13628327181
     * TIME : 20170313182810594
     * BODY : {"CODE":"dog110","MIC":"","OWNERID":"201612291546141016532953","OWNERNAME":"小苹果","ID":"201703131827211016583653","WIFI":"","ONLINE":"0","CAMERA":"","ELEC":"","NICK":"dog1101","STATE":"激活","VOL":"","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg","NAME":"小狗","ACTTIME":""}
     * VERI :
     * TOKEN : 16dc5a82-413e-413a-a424-b0dde2842fe0
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
                    "CODE='" + CODE + '\'' +
                    ", MIC='" + MIC + '\'' +
                    ", OWNERID='" + OWNERID + '\'' +
                    ", OWNERNAME='" + OWNERNAME + '\'' +
                    ", ID='" + ID + '\'' +
                    ", WIFI='" + WIFI + '\'' +
                    ", ONLINE='" + ONLINE + '\'' +
                    ", CAMERA='" + CAMERA + '\'' +
                    ", ELEC='" + ELEC + '\'' +
                    ", NICK='" + NICK + '\'' +
                    ", STATE='" + STATE + '\'' +
                    ", VOL='" + VOL + '\'' +
                    ", IMG='" + IMG + '\'' +
                    ", NAME='" + NAME + '\'' +
                    ", ACTTIME='" + ACTTIME + '\'' +
                    '}';
        }

        public BODYBean(String CODE, String MIC, String OWNERID, String OWNERNAME, String ID, String WIFI, String ONLINE, String CAMERA, String ELEC, String NICK, String STATE, String VOL, String IMG, String NAME, String ACTTIME) {
            this.CODE = CODE;
            this.MIC = MIC;
            this.OWNERID = OWNERID;
            this.OWNERNAME = OWNERNAME;
            this.ID = ID;
            this.WIFI = WIFI;
            this.ONLINE = ONLINE;
            this.CAMERA = CAMERA;
            this.ELEC = ELEC;
            this.NICK = NICK;
            this.STATE = STATE;
            this.VOL = VOL;
            this.IMG = IMG;
            this.NAME = NAME;
            this.ACTTIME = ACTTIME;
        }

        /**
         * CODE : dog110
         * MIC :
         * OWNERID : 201612291546141016532953
         * OWNERNAME : 小苹果
         * ID : 201703131827211016583653
         * WIFI :
         * ONLINE : 0
         * CAMERA :
         * ELEC :
         * NICK : dog1101
         * STATE : 激活
         * VOL :
         * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/0/jpg/20170227/201702272210561016582945.jpg
         * NAME : 小狗
         * ACTTIME :
         */

        private String CODE;
        private String MIC;
        private String OWNERID;
        private String OWNERNAME;
        private String ID;
        private String WIFI;
        private String ONLINE;
        private String CAMERA;
        private String ELEC;
        private String NICK;
        private String STATE;
        private String VOL;
        private String IMG;
        private String NAME;
        private String ACTTIME;

        public String getCODE() {
            return CODE;
        }

        public void setCODE(String CODE) {
            this.CODE = CODE;
        }

        public String getMIC() {
            return MIC;
        }

        public void setMIC(String MIC) {
            this.MIC = MIC;
        }

        public String getOWNERID() {
            return OWNERID;
        }

        public void setOWNERID(String OWNERID) {
            this.OWNERID = OWNERID;
        }

        public String getOWNERNAME() {
            return OWNERNAME;
        }

        public void setOWNERNAME(String OWNERNAME) {
            this.OWNERNAME = OWNERNAME;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getWIFI() {
            return WIFI;
        }

        public void setWIFI(String WIFI) {
            this.WIFI = WIFI;
        }

        public String getONLINE() {
            return ONLINE;
        }

        public void setONLINE(String ONLINE) {
            this.ONLINE = ONLINE;
        }

        public String getCAMERA() {
            return CAMERA;
        }

        public void setCAMERA(String CAMERA) {
            this.CAMERA = CAMERA;
        }

        public String getELEC() {
            return ELEC;
        }

        public void setELEC(String ELEC) {
            this.ELEC = ELEC;
        }

        public String getNICK() {
            return NICK;
        }

        public void setNICK(String NICK) {
            this.NICK = NICK;
        }

        public String getSTATE() {
            return STATE;
        }

        public void setSTATE(String STATE) {
            this.STATE = STATE;
        }

        public String getVOL() {
            return VOL;
        }

        public void setVOL(String VOL) {
            this.VOL = VOL;
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

        public String getACTTIME() {
            return ACTTIME;
        }

        public void setACTTIME(String ACTTIME) {
            this.ACTTIME = ACTTIME;
        }
    }
}
