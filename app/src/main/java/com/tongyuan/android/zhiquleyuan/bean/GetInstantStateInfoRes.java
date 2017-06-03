package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/5/11.
 */

public class GetInstantStateInfoRes {
    /**
     * TYPE : RES
     * CMD : TOYSTATE
     * ACCT : 13628327181
     * TIME : 20170511110159548
     * BODY : {"ONLINE":"1","MIC":"","VOL":"","CAMERA":"","ELEC":"","WIFI":""}
     * VERI :
     * TOKEN : a12fa4f5-5508-4368-948a-80ffa569cd08
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

    @Override
    public String toString() {
        return "GetInstantStateInfoRes{" +
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

    public GetInstantStateInfoRes(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String
            CODE, String MSG) {
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

    public static class BODYBean {
        /**
         * ONLINE : 1
         * MIC :
         * VOL :
         * CAMERA :
         * ELEC :
         * WIFI :
         */

        private String ONLINE;
        private String MIC;
        private String VOL;
        private String CAMERA;
        private String ELEC;
        private String WIFI;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "ONLINE='" + ONLINE + '\'' +
                    ", MIC='" + MIC + '\'' +
                    ", VOL='" + VOL + '\'' +
                    ", CAMERA='" + CAMERA + '\'' +
                    ", ELEC='" + ELEC + '\'' +
                    ", WIFI='" + WIFI + '\'' +
                    '}';
        }

        public BODYBean(String ONLINE, String MIC, String VOL, String CAMERA, String ELEC, String WIFI) {
            this.ONLINE = ONLINE;
            this.MIC = MIC;
            this.VOL = VOL;
            this.CAMERA = CAMERA;
            this.ELEC = ELEC;
            this.WIFI = WIFI;
        }

        public String getONLINE() {
            return ONLINE;
        }

        public void setONLINE(String ONLINE) {
            this.ONLINE = ONLINE;
        }

        public String getMIC() {
            return MIC;
        }

        public void setMIC(String MIC) {
            this.MIC = MIC;
        }

        public String getVOL() {
            return VOL;
        }

        public void setVOL(String VOL) {
            this.VOL = VOL;
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

        public String getWIFI() {
            return WIFI;
        }

        public void setWIFI(String WIFI) {
            this.WIFI = WIFI;
        }
    }
}
//{"TYPE":"RES","CMD":"TOYSTATE","ACCT":"13628327181","TIME":"20170511110828624","BODY":{"ONLINE":"1","MIC":"2","ELEC":"100","VOL":"13",
// "WIFI":"-23","CAMERA":"2"},"VERI":"","TOKEN":"a12fa4f5-5508-4368-948a-80ffa569cd08","SEQ":"1","CODE":"0","MSG":""}这个是超级多多的状态信息
