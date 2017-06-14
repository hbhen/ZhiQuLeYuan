package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/6/14.
 */

public class DeleteMyPlayReqBean {
    /**
     * TYPE : REQ
     * CMD : DMYPLAY
     * ACCT : 13623827181
     * TIME : 20161127101010000
     * BODY : {"LST":[{"RCDID":"201706140940531016824448"}]}
     * VERI :
     * TOKEN : c19d5a15-592a-4b78-863e-846e789163be
     * SEQ : 1
     */

    private String TYPE;
    private String CMD;
    private String ACCT;
    private String TIME;
    private BODYBean BODY;
    private String VERI;
    private String TOKEN;
    private String SEQ;

    @Override
    public String toString() {
        return "DeleteMyPlayReqBean{" +
                "TYPE='" + TYPE + '\'' +
                ", CMD='" + CMD + '\'' +
                ", ACCT='" + ACCT + '\'' +
                ", TIME='" + TIME + '\'' +
                ", BODY=" + BODY +
                ", VERI='" + VERI + '\'' +
                ", TOKEN='" + TOKEN + '\'' +
                ", SEQ='" + SEQ + '\'' +
                '}';
    }

    public DeleteMyPlayReqBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.BODY = BODY;
        this.VERI = VERI;
        this.TOKEN = TOKEN;
        this.SEQ = SEQ;
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

    public static class BODYBean {
        private List<LSTBean> LST;

        public BODYBean(List<LSTBean> LST) {
            this.LST = LST;
        }

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        @Override
        public String toString() {
            return "BODYBean{" +
                    "LST=" + LST +
                    '}';
        }

        public static class LSTBean {
            /**
             * RCDID : 201706140940531016824448
             */

            private String RCDID;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "RCDID='" + RCDID + '\'' +
                        '}';
            }

            public LSTBean(String RCDID) {
                this.RCDID = RCDID;
            }

            public String getRCDID() {
                return RCDID;
            }

            public void setRCDID(String RCDID) {
                this.RCDID = RCDID;
            }
        }
    }
}
