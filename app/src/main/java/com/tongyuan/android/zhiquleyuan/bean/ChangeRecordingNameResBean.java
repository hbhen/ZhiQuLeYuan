package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/6/17.
 */

public class ChangeRecordingNameResBean {

    /**
     * TYPE : RES
     * CMD : ARES
     * ACCT : 13628327181
     * TIME : 20170617100839009
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"TYPE":"0","NAME":"哈哈","ID":"201706171008381016824535","TIMES":"0","SIZE":"","DUR":"","COLID":"","RESOLUTION":"","REMARK":"",
     * "IMG":"","COLNAME":""}
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
        return "ChangeRecordingNameResBean{" +
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

    public ChangeRecordingNameResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean
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
         * TYPE : 0
         * NAME : 哈哈
         * ID : 201706171008381016824535
         * TIMES : 0
         * SIZE :
         * DUR :
         * COLID :
         * RESOLUTION :
         * REMARK :
         * IMG :
         * COLNAME :
         */

        private String TYPE;
        private String NAME;
        private String ID;
        private String TIMES;
        private String SIZE;
        private String DUR;
        private String COLID;
        private String RESOLUTION;
        private String REMARK;
        private String IMG;
        private String COLNAME;

        public BODYBean(String TYPE, String NAME, String ID, String TIMES, String SIZE, String DUR, String COLID, String RESOLUTION, String REMARK,
                        String IMG, String COLNAME) {
            this.TYPE = TYPE;
            this.NAME = NAME;
            this.ID = ID;
            this.TIMES = TIMES;
            this.SIZE = SIZE;
            this.DUR = DUR;
            this.COLID = COLID;
            this.RESOLUTION = RESOLUTION;
            this.REMARK = REMARK;
            this.IMG = IMG;
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

        public String getSIZE() {
            return SIZE;
        }

        public void setSIZE(String SIZE) {
            this.SIZE = SIZE;
        }

        public String getDUR() {
            return DUR;
        }

        public void setDUR(String DUR) {
            this.DUR = DUR;
        }

        public String getCOLID() {
            return COLID;
        }

        public void setCOLID(String COLID) {
            this.COLID = COLID;
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

        public String getIMG() {
            return IMG;
        }

        public void setIMG(String IMG) {
            this.IMG = IMG;
        }

        public String getCOLNAME() {
            return COLNAME;
        }

        public void setCOLNAME(String COLNAME) {
            this.COLNAME = COLNAME;
        }
    }
}
