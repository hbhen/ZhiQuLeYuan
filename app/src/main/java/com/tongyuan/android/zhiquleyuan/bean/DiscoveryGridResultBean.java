package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2017/3/9.
 */

public class DiscoveryGridResultBean implements Parcelable {
    @Override
    public String toString() {
        return "DiscoveryGridResultBean{" +
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

    public DiscoveryGridResultBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
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
     * CMD : QRYCOL
     * ACCT : 13623827181
     * TIME : 20170309132544879
     * BODY : {"LST":[{"IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170307/201703071644361016583111.png","ID":"201610301737481016432787","PID":"0","NAME":"儿童歌谣"},{"PID":"0","NAME":"国学经典","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091204361016563474.png","ID":"201611050827051016432864"},{"IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091204491016563476.png","PID":"0","NAME":"生活百科","ID":"201702091130331016563464"},{"NAME":"英语启蒙","PID":"0","ID":"201702091130511016563465","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091204591016563478.png"},{"IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205091016563480.png","PID":"0","NAME":"中国民乐","ID":"201702091131021016563466"},{"PID":"0","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205181016563482.png","ID":"201702091131111016563467","NAME":"西方古典"},{"PID":"0","NAME":"摇篮曲","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205261016563484.png","ID":"201702091131241016563468"},{"NAME":"胎教音乐","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205341016563486.png","PID":"0","ID":"201702091131341016563469"},{"NAME":"人格培养","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205431016563488.png","PID":"0","ID":"201702091131431016563470"}],"PN":"1","NC":"0","CNT":"9","PS":"10"}
     * VERI :
     * TOKEN : dfcd0a15-9b77-486f-b4cd-0635897faea4
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
         * LST : [{"IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170307/201703071644361016583111.png","ID":"201610301737481016432787","PID":"0","NAME":"儿童歌谣"},{"PID":"0","NAME":"国学经典","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091204361016563474.png","ID":"201611050827051016432864"},{"IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091204491016563476.png","PID":"0","NAME":"生活百科","ID":"201702091130331016563464"},{"NAME":"英语启蒙","PID":"0","ID":"201702091130511016563465","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091204591016563478.png"},{"IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205091016563480.png","PID":"0","NAME":"中国民乐","ID":"201702091131021016563466"},{"PID":"0","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205181016563482.png","ID":"201702091131111016563467","NAME":"西方古典"},{"PID":"0","NAME":"摇篮曲","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205261016563484.png","ID":"201702091131241016563468"},{"NAME":"胎教音乐","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205341016563486.png","PID":"0","ID":"201702091131341016563469"},{"NAME":"人格培养","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170209/201702091205431016563488.png","PID":"0","ID":"201702091131431016563470"}]
         * PN : 1
         * NC : 0
         * CNT : 9
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

        public static class LSTBean implements Parcelable {
            @Override
            public String toString() {
                return "LSTBean{" +
                        "IMG='" + IMG + '\'' +
                        ", ID='" + ID + '\'' +
                        ", PID='" + PID + '\'' +
                        ", NAME='" + NAME + '\'' +
                        '}';
            }

            public LSTBean(String IMG, String ID, String PID, String NAME) {
                this.IMG = IMG;
                this.ID = ID;
                this.PID = PID;
                this.NAME = NAME;
            }

            /**
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/4/png/20170307/201703071644361016583111.png
             * ID : 201610301737481016432787
             * PID : 0
             * NAME : 儿童歌谣
             */

            private String IMG;
            private String ID;
            private String PID;
            private String NAME;

            public String getIMG() {
                return IMG;
            }

            public void setIMG(String IMG) {
                this.IMG = IMG;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getPID() {
                return PID;
            }

            public void setPID(String PID) {
                this.PID = PID;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.IMG);
                dest.writeString(this.ID);
                dest.writeString(this.PID);
                dest.writeString(this.NAME);
            }

            protected LSTBean(Parcel in) {
                this.IMG = in.readString();
                this.ID = in.readString();
                this.PID = in.readString();
                this.NAME = in.readString();
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
            dest.writeString(this.PN);
            dest.writeString(this.NC);
            dest.writeString(this.CNT);
            dest.writeString(this.PS);
            dest.writeList(this.LST);
        }

        protected BODYBean(Parcel in) {
            this.PN = in.readString();
            this.NC = in.readString();
            this.CNT = in.readString();
            this.PS = in.readString();
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

    protected DiscoveryGridResultBean(Parcel in) {
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

    public static final Parcelable.Creator<DiscoveryGridResultBean> CREATOR = new Parcelable.Creator<DiscoveryGridResultBean>() {
        @Override
        public DiscoveryGridResultBean createFromParcel(Parcel source) {
            return new DiscoveryGridResultBean(source);
        }

        @Override
        public DiscoveryGridResultBean[] newArray(int size) {
            return new DiscoveryGridResultBean[size];
        }
    };
}
