package com.tongyuan.android.zhiquleyuan.bean;

import java.util.List;

/**
 * Created by Android on 2017/8/4.
 */
public class DeleteMyPushReqBean {
    @Override
    public String toString() {
        return "DeleteMyPush{" +
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

    public DeleteMyPushReqBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ) {
        this.TYPE = TYPE;
        this.CMD = CMD;
        this.ACCT = ACCT;
        this.TIME = TIME;
        this.BODY = BODY;
        this.VERI = VERI;
        this.TOKEN = TOKEN;
        this.SEQ = SEQ;
    }

    /**
     * TYPE : REQ
     * CMD : DMYPUSH
     * ACCT : XXXX
     * TIME : 20161127101010000
     * BODY : {"LST":[{"RCDID":"1"}]}
     * VERI :
     * TOKEN : ff35777e-1f5f-4f7c-be03-359e4e752918
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

        @Override
        public String toString() {
            return "BODYBean{" +
                    "LST=" + LST +
                    '}';
        }

        public BODYBean(List<LSTBean> LST) {
            this.LST = LST;
        }

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean {
            /**
             * RCDID : 1
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
