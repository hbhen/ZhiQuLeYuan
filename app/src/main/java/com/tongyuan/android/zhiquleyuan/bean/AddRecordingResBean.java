package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/6/13.
 */

public class AddRecordingResBean {

    @Override
    public String toString() {
        return "AddRecordingResBean{" +
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

    public AddRecordingResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean BODY,
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
     * CMD : ARES
     * ACCT : 13628327181
     * TIME : 20170617112258912
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"TIMES":"0","DUR":"","SIZE":"","TYPE":"0","IMG":"","COLID":"","NAME":"哈哈","ID":"201706171122581016824542","COLNAME":"",
     * "RESOLUTION":"","REMARK":""}
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
         * TIMES : 0
         * DUR :
         * SIZE :
         * TYPE : 0
         * IMG :
         * COLID :
         * NAME : 哈哈
         * ID : 201706171122581016824542
         * COLNAME :
         * RESOLUTION :
         * REMARK :
         */

        private String TIMES;
        private String DUR;
        private String SIZE;
        private String TYPE;
        private String IMG;
        private String COLID;
        private String NAME;
        private String ID;
        private String COLNAME;
        private String RESOLUTION;
        private String REMARK;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "TIMES='" + TIMES + '\'' +
                    ", DUR='" + DUR + '\'' +
                    ", SIZE='" + SIZE + '\'' +
                    ", TYPE='" + TYPE + '\'' +
                    ", IMG='" + IMG + '\'' +
                    ", COLID='" + COLID + '\'' +
                    ", NAME='" + NAME + '\'' +
                    ", ID='" + ID + '\'' +
                    ", COLNAME='" + COLNAME + '\'' +
                    ", RESOLUTION='" + RESOLUTION + '\'' +
                    ", REMARK='" + REMARK + '\'' +
                    '}';
        }

        public BODYBean(String TIMES, String DUR, String SIZE, String TYPE, String IMG, String COLID, String NAME, String ID, String COLNAME,
                        String RESOLUTION, String REMARK) {
            this.TIMES = TIMES;
            this.DUR = DUR;
            this.SIZE = SIZE;
            this.TYPE = TYPE;
            this.IMG = IMG;
            this.COLID = COLID;
            this.NAME = NAME;
            this.ID = ID;
            this.COLNAME = COLNAME;
            this.RESOLUTION = RESOLUTION;
            this.REMARK = REMARK;
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

        public String getSIZE() {
            return SIZE;
        }

        public void setSIZE(String SIZE) {
            this.SIZE = SIZE;
        }

        public String getTYPE() {
            return TYPE;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }

        public String getIMG() {
            return IMG;
        }

        public void setIMG(String IMG) {
            this.IMG = IMG;
        }

        public String getCOLID() {
            return COLID;
        }

        public void setCOLID(String COLID) {
            this.COLID = COLID;
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

        public String getCOLNAME() {
            return COLNAME;
        }

        public void setCOLNAME(String COLNAME) {
            this.COLNAME = COLNAME;
        }

        public String getRESOLUTION() {
            return RESOLUTION;
        }

        public void setRESOLUTION(String RESOLUTION) {
            this.RESOLUTION = RESOLUTION;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }
    }
}
