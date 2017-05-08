package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2017/3/3.
 */
public class QueryBabyListResult implements Parcelable {
    @Override
    public String toString() {
        return "QueryBabyListResult{" +
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

    public QueryBabyListResult(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
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
     * CMD : QMYB
     * ACCT : 13628327181
     * TIME : 20170303134513368
     * BODY : {"NC":"0","PN":"1","PS":"10","CNT":"7","LST":[{"SEX":"男","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/3/png/20170210/201702101420461016563522.png","NAME":"杜昂杜昂","ID":"201612292326501016533022","BIRTHDAY":"20161229000000","NICK":"正正"},{"SEX":"男","ID":"201702061004161016563148","NAME":"杜昂杜昂","NICK":"豆豆","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/3/png/20170210/201702101421371016563524.png","BIRTHDAY":"20170206000000"},{"ID":"201702101506461016563531","SEX":"女","NAME":"杜昂杜昂","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/3/png/20170210/201702101507381016563532.png","BIRTHDAY":"20170210000000","NICK":"点点"},{"NAME":"头一","IMG":"","NICK":"","SEX":"男","ID":"201703030005191016583039","BIRTHDAY":"20170101000000"},{"NAME":"还","NICK":"","IMG":"","BIRTHDAY":"20170101000000","ID":"201703030009171016583040","SEX":"男"},{"ID":"201703030016421016583041","BIRTHDAY":"20170101000000","NAME":"头","IMG":"","SEX":"男","NICK":""},{"IMG":"","ID":"201703031200221016583042","BIRTHDAY":"20120303000000","SEX":"男","NICK":"","NAME":"有"}]}
     * VERI :
     * TOKEN : 518e6521-b2ce-46e0-8083-b4c741f7e2b9
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

    public static class BODYBean implements Parcelable {
        @Override
        public String toString() {
            return "BODYBean{" +
                    "NC='" + NC + '\'' +
                    ", PN='" + PN + '\'' +
                    ", PS='" + PS + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String NC, String PN, String PS, String CNT, List<LSTBean> LST) {
            this.NC = NC;
            this.PN = PN;
            this.PS = PS;
            this.CNT = CNT;
            this.LST = LST;
        }

        /**
         * NC : 0
         * PN : 1
         * PS : 10
         * CNT : 7
         * LST : [{"SEX":"男","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/3/png/20170210/201702101420461016563522.png","NAME":"杜昂杜昂","ID":"201612292326501016533022","BIRTHDAY":"20161229000000","NICK":"正正"},{"SEX":"男","ID":"201702061004161016563148","NAME":"杜昂杜昂","NICK":"豆豆","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/3/png/20170210/201702101421371016563524.png","BIRTHDAY":"20170206000000"},{"ID":"201702101506461016563531","SEX":"女","NAME":"杜昂杜昂","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/3/png/20170210/201702101507381016563532.png","BIRTHDAY":"20170210000000","NICK":"点点"},{"NAME":"头一","IMG":"","NICK":"","SEX":"男","ID":"201703030005191016583039","BIRTHDAY":"20170101000000"},{"NAME":"还","NICK":"","IMG":"","BIRTHDAY":"20170101000000","ID":"201703030009171016583040","SEX":"男"},{"ID":"201703030016421016583041","BIRTHDAY":"20170101000000","NAME":"头","IMG":"","SEX":"男","NICK":""},{"IMG":"","ID":"201703031200221016583042","BIRTHDAY":"20120303000000","SEX":"男","NICK":"","NAME":"有"}]
         */

        private String NC;
        private String PN;
        private String PS;
        private String CNT;
        private List<LSTBean> LST;

        public String getNC() {
            return NC;
        }

        public void setNC(String NC) {
            this.NC = NC;
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

        public String getCNT() {
            return CNT;
        }

        public void setCNT(String CNT) {
            this.CNT = CNT;
        }

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean implements Parcelable {
            @Override
            public String toString() {
                return "LSTBean{" +
                        "SEX='" + SEX + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", ID='" + ID + '\'' +
                        ", BIRTHDAY='" + BIRTHDAY + '\'' +
                        ", NICK='" + NICK + '\'' +
                        '}';
            }

            public LSTBean(String SEX, String IMG, String NAME, String ID, String BIRTHDAY, String NICK) {
                this.SEX = SEX;
                this.IMG = IMG;
                this.NAME = NAME;
                this.ID = ID;
                this.BIRTHDAY = BIRTHDAY;
                this.NICK = NICK;
            }

            /**
             * SEX : 男
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/3/png/20170210/201702101420461016563522.png
             * NAME : 杜昂杜昂
             * ID : 201612292326501016533022
             * BIRTHDAY : 20161229000000
             * NICK : 正正
             */

            private String SEX;
            private String IMG;
            private String NAME;
            private String ID;
            private String BIRTHDAY;
            private String NICK;

            public String getSEX() {
                return SEX;
            }

            public void setSEX(String SEX) {
                this.SEX = SEX;
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

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.SEX);
                dest.writeString(this.IMG);
                dest.writeString(this.NAME);
                dest.writeString(this.ID);
                dest.writeString(this.BIRTHDAY);
                dest.writeString(this.NICK);
            }

            protected LSTBean(Parcel in) {
                this.SEX = in.readString();
                this.IMG = in.readString();
                this.NAME = in.readString();
                this.ID = in.readString();
                this.BIRTHDAY = in.readString();
                this.NICK = in.readString();
            }

            public static final Creator<LSTBean> CREATOR = new Creator<LSTBean>() {
                @Override
                public LSTBean createFromParcel(Parcel source) {
                    return new LSTBean(source);
                }

                @Override
                public LSTBean[] newArray(int size) {
                    return new LSTBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.NC);
            dest.writeString(this.PN);
            dest.writeString(this.PS);
            dest.writeString(this.CNT);
            dest.writeList(this.LST);
        }

        protected BODYBean(Parcel in) {
            this.NC = in.readString();
            this.PN = in.readString();
            this.PS = in.readString();
            this.CNT = in.readString();
            this.LST = new ArrayList<LSTBean>();
            in.readList(this.LST, LSTBean.class.getClassLoader());
        }

        public static final Creator<BODYBean> CREATOR = new Creator<BODYBean>() {
            @Override
            public BODYBean createFromParcel(Parcel source) {
                return new BODYBean(source);
            }

            @Override
            public BODYBean[] newArray(int size) {
                return new BODYBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TYPE);
        dest.writeString(this.CMD);
        dest.writeString(this.ACCT);
        dest.writeString(this.TIME);
        dest.writeParcelable(this.BODY, flags);
        dest.writeString(this.VERI);
        dest.writeString(this.TOKEN);
        dest.writeString(this.SEQ);
        dest.writeString(this.CODE);
        dest.writeString(this.MSG);
    }

    protected QueryBabyListResult(Parcel in) {
        this.TYPE = in.readString();
        this.CMD = in.readString();
        this.ACCT = in.readString();
        this.TIME = in.readString();
        this.BODY = in.readParcelable(BODYBean.class.getClassLoader());
        this.VERI = in.readString();
        this.TOKEN = in.readString();
        this.SEQ = in.readString();
        this.CODE = in.readString();
        this.MSG = in.readString();
    }

    public static final Parcelable.Creator<QueryBabyListResult> CREATOR = new Parcelable.Creator<QueryBabyListResult>() {
        @Override
        public QueryBabyListResult createFromParcel(Parcel source) {
            return new QueryBabyListResult(source);
        }

        @Override
        public QueryBabyListResult[] newArray(int size) {
            return new QueryBabyListResult[size];
        }
    };
}
