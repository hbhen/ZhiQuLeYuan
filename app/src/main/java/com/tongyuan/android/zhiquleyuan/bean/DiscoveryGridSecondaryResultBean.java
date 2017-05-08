package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DTC on 2017/3/24.
 */
public class DiscoveryGridSecondaryResultBean implements Parcelable {
    @Override
    public String toString() {
        return "DiscoveryGridSecondaryResultBean{" +
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

    public DiscoveryGridSecondaryResultBean(String TYPE, String CMD, String ACCT, String TIME, BODYBean BODY, String VERI, String TOKEN, String SEQ, String CODE, String MSG) {
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
     * CMD : QRYRES
     * ACCT : XXXX
     * TIME : 20170324113712831
     * BODY : {"PN":"1","CNT":"1","PS":"10","NC":"0","LST":[{"TYPE":"音频文件","NAME":"蚂蚁","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/png/20170210/201702101530091016563538.png","ID":"201701121950061016547106","COLNAME":"国学经典 ","SIZE":"11.03MB","DUR":"","COLID":"201611050827051016432864 ","REMARK":"","TIMES":"5"}]}
     * VERI :
     * TOKEN : 51ff422f-fdec-4c1e-a277-90419c20b827
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

        /**
         * PN : 1
         * CNT : 1
         * PS : 10
         * NC : 0
         * LST : [{"TYPE":"音频文件","NAME":"蚂蚁","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/png/20170210/201702101530091016563538.png","ID":"201701121950061016547106","COLNAME":"国学经典 ","SIZE":"11.03MB","DUR":"","COLID":"201611050827051016432864 ","REMARK":"","TIMES":"5"}]
         */

        private String PN;
        private String CNT;
        private String PS;
        private String NC;
        private List<LSTBean> LST;

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

        public static class LSTBean implements Parcelable {
            @Override
            public String toString() {
                return "LSTBean{" +
                        "TYPE='" + TYPE + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", ID='" + ID + '\'' +
                        ", COLNAME='" + COLNAME + '\'' +
                        ", SIZE='" + SIZE + '\'' +
                        ", DUR='" + DUR + '\'' +
                        ", COLID='" + COLID + '\'' +
                        ", REMARK='" + REMARK + '\'' +
                        ", TIMES='" + TIMES + '\'' +
                        '}';
            }

            public LSTBean(String TYPE, String NAME, String IMG, String ID, String COLNAME, String SIZE, String DUR, String COLID, String REMARK, String TIMES) {
                this.TYPE = TYPE;
                this.NAME = NAME;
                this.IMG = IMG;
                this.ID = ID;
                this.COLNAME = COLNAME;
                this.SIZE = SIZE;
                this.DUR = DUR;
                this.COLID = COLID;
                this.REMARK = REMARK;
                this.TIMES = TIMES;
            }

            /**
             * TYPE : 音频文件
             * NAME : 蚂蚁
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/5/png/20170210/201702101530091016563538.png
             * ID : 201701121950061016547106
             * COLNAME : 国学经典
             * SIZE : 11.03MB
             * DUR :
             * COLID : 201611050827051016432864
             * REMARK :
             * TIMES : 5
             */

            private String TYPE;
            private String NAME;
            private String IMG;
            private String ID;
            private String COLNAME;
            private String SIZE;
            private String DUR;
            private String COLID;
            private String REMARK;
            private String TIMES;

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

            public String getCOLNAME() {
                return COLNAME;
            }

            public void setCOLNAME(String COLNAME) {
                this.COLNAME = COLNAME;
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

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getTIMES() {
                return TIMES;
            }

            public void setTIMES(String TIMES) {
                this.TIMES = TIMES;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.TYPE);
                dest.writeString(this.NAME);
                dest.writeString(this.IMG);
                dest.writeString(this.ID);
                dest.writeString(this.COLNAME);
                dest.writeString(this.SIZE);
                dest.writeString(this.DUR);
                dest.writeString(this.COLID);
                dest.writeString(this.REMARK);
                dest.writeString(this.TIMES);
            }

            protected LSTBean(Parcel in) {
                this.TYPE = in.readString();
                this.NAME = in.readString();
                this.IMG = in.readString();
                this.ID = in.readString();
                this.COLNAME = in.readString();
                this.SIZE = in.readString();
                this.DUR = in.readString();
                this.COLID = in.readString();
                this.REMARK = in.readString();
                this.TIMES = in.readString();
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
            dest.writeString(this.CNT);
            dest.writeString(this.PS);
            dest.writeString(this.NC);
            dest.writeList(this.LST);
        }

        protected BODYBean(Parcel in) {
            this.PN = in.readString();
            this.CNT = in.readString();
            this.PS = in.readString();
            this.NC = in.readString();
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

    protected DiscoveryGridSecondaryResultBean(Parcel in) {
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

    public static final Parcelable.Creator<DiscoveryGridSecondaryResultBean> CREATOR = new Parcelable.Creator<DiscoveryGridSecondaryResultBean>() {
        @Override
        public DiscoveryGridSecondaryResultBean createFromParcel(Parcel source) {
            return new DiscoveryGridSecondaryResultBean(source);
        }

        @Override
        public DiscoveryGridSecondaryResultBean[] newArray(int size) {
            return new DiscoveryGridSecondaryResultBean[size];
        }
    };
}
