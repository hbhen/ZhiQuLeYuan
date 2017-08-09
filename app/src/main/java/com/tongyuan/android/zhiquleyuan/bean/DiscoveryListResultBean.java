package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2017/3/10.
 */

public class DiscoveryListResultBean implements Parcelable {

    /**
     * TYPE : RES
     * CMD : QRYRES
     * ACCT : XXXX
     * TIME : 20170808094632067
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"PS":"10","LST":[{"SIZE":"109.17KB","TIMES":"83","TYPE":"音频文件","REMARK":"","COLNAME":"儿童歌谣 ","DUR":"","NAME":"拍手歌","IMG":"http://120
     * .27.41.179:8081/zqpland/resource/thumbnail/5/png/20170702/201707021623491016839668.png","RESOLUTION":"280x280",
     * "COLID":"201610301737481016432787 ","ISFAV":"0","ID":"201706041509200000003905"},{"COLNAME":"儿童歌谣 ","NAME":"捉泥鳅","REMARK":"","SIZE":"7
     * .71MB","TYPE":"音频文件","COLID":"201610301737481016432787 ","ID":"201706041509200000003906","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170702/201707021625101016839670.jpg","DUR":"0:03:21","TIMES":"57","ISFAV":"0"},{"ISFAV":"0",
     * "DUR":"0:02:01","IMG":"","COLNAME":"儿童歌谣 ","REMARK":"","TIMES":"39","ID":"201706041509200000003907","NAME":"小螺号mp3","TYPE":"音频文件",
     * "COLID":"201610301737481016432787 ","SIZE":"2.53MB"},{"COLID":"201610301737481016432787 ","NAME":"感动未来mp3","ISFAV":"0",
     * "ID":"201706041509200000003908","TYPE":"音频文件","SIZE":"3.04MB","REMARK":"","COLNAME":"儿童歌谣 ","TIMES":"17","DUR":"0:03:18","IMG":""},
     * {"REMARK":"","ISFAV":"0","ID":"201706041509200000003909","COLID":"201610301737481016432787 ","TYPE":"音频文件","IMG":"","COLNAME":"儿童歌谣 ",
     * "NAME":"小龙人mp3","TIMES":"7","DUR":"0:01:54","SIZE":"1.76MB"},{"ID":"201706041509200000003910","TYPE":"音频文件","NAME":"小放牛mp3","DUR":"0:02:38",
     * "ISFAV":"0","REMARK":"","SIZE":"2.43MB","COLNAME":"儿童歌谣 ","COLID":"201610301737481016432787 ","TIMES":"3","IMG":""},{"DUR":"0:02:48",
     * "COLNAME":"儿童歌谣 ","REMARK":"","SIZE":"6.43MB","IMG":"","ISFAV":"0","ID":"201706041509200000003911","COLID":"201610301737481016432787 ",
     * "TYPE":"音频文件","NAME":"欢乐颂mp3","TIMES":"2"},{"SIZE":"1.82MB","TIMES":"4","ID":"201706041509200000003912","TYPE":"音频文件",
     * "COLID":"201610301737481016432787 ","IMG":"","NAME":"小宝宝睡着了mp3","DUR":"0:01:58","COLNAME":"儿童歌谣 ","REMARK":"","ISFAV":"0"},
     * {"ID":"201706041509200000003913","COLID":"201610301737481016432787 ","NAME":"友谊之光mp3","TIMES":"7","SIZE":"1.88MB","IMG":"","TYPE":"音频文件",
     * "ISFAV":"0","DUR":"0:02:02","REMARK":"","COLNAME":"儿童歌谣 "},{"IMG":"","REMARK":"","NAME":"当我们同在一起mp3","ISFAV":"0","DUR":"0:02:00",
     * "TYPE":"音频文件","COLNAME":"儿童歌谣 ","TIMES":"17","COLID":"201610301737481016432787 ","SIZE":"1.84MB","ID":"201706041509200000003914"}],
     * "CNT":"465","NC":"455","PN":"1"}
     * TOKEN : 9d79ef8f-d2cc-4bdb-9239-7ffe4b53c103
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
        return "DiscoveryListResultBean{" +
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

    public DiscoveryListResultBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean
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

    public static class BODYBean implements Parcelable {
        /**
         * PS : 10
         * LST : [{"SIZE":"109.17KB","TIMES":"83","TYPE":"音频文件","REMARK":"","COLNAME":"儿童歌谣 ","DUR":"","NAME":"拍手歌","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/png/20170702/201707021623491016839668.png","RESOLUTION":"280x280",
         * "COLID":"201610301737481016432787 ","ISFAV":"0","ID":"201706041509200000003905"},{"COLNAME":"儿童歌谣 ","NAME":"捉泥鳅","REMARK":"","SIZE":"7
         * .71MB","TYPE":"音频文件","COLID":"201610301737481016432787 ","ID":"201706041509200000003906","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170702/201707021625101016839670.jpg","DUR":"0:03:21","TIMES":"57","ISFAV":"0"},
         * {"ISFAV":"0","DUR":"0:02:01","IMG":"","COLNAME":"儿童歌谣 ","REMARK":"","TIMES":"39","ID":"201706041509200000003907","NAME":"小螺号mp3",
         * "TYPE":"音频文件","COLID":"201610301737481016432787 ","SIZE":"2.53MB"},{"COLID":"201610301737481016432787 ","NAME":"感动未来mp3","ISFAV":"0",
         * "ID":"201706041509200000003908","TYPE":"音频文件","SIZE":"3.04MB","REMARK":"","COLNAME":"儿童歌谣 ","TIMES":"17","DUR":"0:03:18","IMG":""},
         * {"REMARK":"","ISFAV":"0","ID":"201706041509200000003909","COLID":"201610301737481016432787 ","TYPE":"音频文件","IMG":"","COLNAME":"儿童歌谣 ",
         * "NAME":"小龙人mp3","TIMES":"7","DUR":"0:01:54","SIZE":"1.76MB"},{"ID":"201706041509200000003910","TYPE":"音频文件","NAME":"小放牛mp3",
         * "DUR":"0:02:38","ISFAV":"0","REMARK":"","SIZE":"2.43MB","COLNAME":"儿童歌谣 ","COLID":"201610301737481016432787 ","TIMES":"3","IMG":""},
         * {"DUR":"0:02:48","COLNAME":"儿童歌谣 ","REMARK":"","SIZE":"6.43MB","IMG":"","ISFAV":"0","ID":"201706041509200000003911",
         * "COLID":"201610301737481016432787 ","TYPE":"音频文件","NAME":"欢乐颂mp3","TIMES":"2"},{"SIZE":"1.82MB","TIMES":"4",
         * "ID":"201706041509200000003912","TYPE":"音频文件","COLID":"201610301737481016432787 ","IMG":"","NAME":"小宝宝睡着了mp3","DUR":"0:01:58",
         * "COLNAME":"儿童歌谣 ","REMARK":"","ISFAV":"0"},{"ID":"201706041509200000003913","COLID":"201610301737481016432787 ","NAME":"友谊之光mp3",
         * "TIMES":"7","SIZE":"1.88MB","IMG":"","TYPE":"音频文件","ISFAV":"0","DUR":"0:02:02","REMARK":"","COLNAME":"儿童歌谣 "},{"IMG":"","REMARK":"",
         * "NAME":"当我们同在一起mp3","ISFAV":"0","DUR":"0:02:00","TYPE":"音频文件","COLNAME":"儿童歌谣 ","TIMES":"17","COLID":"201610301737481016432787 ",
         * "SIZE":"1.84MB","ID":"201706041509200000003914"}]
         * CNT : 465
         * NC : 455
         * PN : 1
         */

        private String PS;
        private String CNT;
        private String NC;
        private String PN;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PS='" + PS + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", NC='" + NC + '\'' +
                    ", PN='" + PN + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PS, String CNT, String NC, String PN, List<LSTBean> LST) {
            this.PS = PS;
            this.CNT = CNT;
            this.NC = NC;
            this.PN = PN;
            this.LST = LST;
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

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean extends MusicPlayerBean {
            public LSTBean() {
            }
            /**
             * SIZE : 109.17KB
             * TIMES : 83
             * TYPE : 音频文件
             * REMARK :
             * COLNAME : 儿童歌谣
             * DUR :
             * NAME : 拍手歌
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/5/png/20170702/201707021623491016839668.png
             * RESOLUTION : 280x280
             * COLID : 201610301737481016432787
             * ISFAV : 0
             * ID : 201706041509200000003905
             */

            private String SIZE;
            private String TIMES;
            private String TYPE;
            private String REMARK;
            private String COLNAME;
            private String DUR;
//            private String NAME;
//            private String IMG;
            private String RESOLUTION;
            private String COLID;
            private String ISFAV;
//            private String ID;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "SIZE='" + SIZE + '\'' +
                        ", TIMES='" + TIMES + '\'' +
                        ", TYPE='" + TYPE + '\'' +
                        ", REMARK='" + REMARK + '\'' +
                        ", COLNAME='" + COLNAME + '\'' +
                        ", DUR='" + DUR + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", RESOLUTION='" + RESOLUTION + '\'' +
                        ", COLID='" + COLID + '\'' +
                        ", ISFAV='" + ISFAV + '\'' +
                        ", ID='" + ID + '\'' +
                        '}';
            }

            public LSTBean(String SIZE, String TIMES, String TYPE, String REMARK, String COLNAME, String DUR, String NAME, String IMG, String
                    RESOLUTION, String COLID, String ISFAV, String ID) {
                this.SIZE = SIZE;
                this.TIMES = TIMES;
                this.TYPE = TYPE;
                this.REMARK = REMARK;
                this.COLNAME = COLNAME;
                this.DUR = DUR;
                this.NAME = NAME;
                this.IMG = IMG;
                this.RESOLUTION = RESOLUTION;
                this.COLID = COLID;
                this.ISFAV = ISFAV;
                this.ID = ID;
            }

            public String getSIZE() {
                return SIZE;
            }

            public void setSIZE(String SIZE) {
                this.SIZE = SIZE;
            }

            public String getTIMES() {
                return TIMES;
            }

            public void setTIMES(String TIMES) {
                this.TIMES = TIMES;
            }

            public String getTYPE() {
                return TYPE;
            }

            public void setTYPE(String TYPE) {
                this.TYPE = TYPE;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getCOLNAME() {
                return COLNAME;
            }

            public void setCOLNAME(String COLNAME) {
                this.COLNAME = COLNAME;
            }

            public String getDUR() {
                return DUR;
            }

            public void setDUR(String DUR) {
                this.DUR = DUR;
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

            public String getRESOLUTION() {
                return RESOLUTION;
            }

            public void setRESOLUTION(String RESOLUTION) {
                this.RESOLUTION = RESOLUTION;
            }

            public String getCOLID() {
                return COLID;
            }

            public void setCOLID(String COLID) {
                this.COLID = COLID;
            }

            public String getISFAV() {
                return ISFAV;
            }

            public void setISFAV(String ISFAV) {
                this.ISFAV = ISFAV;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
                dest.writeString(this.SIZE);
                dest.writeString(this.TIMES);
                dest.writeString(this.TYPE);
                dest.writeString(this.REMARK);
                dest.writeString(this.COLNAME);
                dest.writeString(this.DUR);
                dest.writeString(this.NAME);
                dest.writeString(this.IMG);
                dest.writeString(this.RESOLUTION);
                dest.writeString(this.COLID);
                dest.writeString(this.ISFAV);
                dest.writeString(this.ID);
            }

            protected LSTBean(Parcel in) {
                super(in);
                this.SIZE = in.readString();
                this.TIMES = in.readString();
                this.TYPE = in.readString();
                this.REMARK = in.readString();
                this.COLNAME = in.readString();
                this.DUR = in.readString();
                this.NAME = in.readString();
                this.IMG = in.readString();
                this.RESOLUTION = in.readString();
                this.COLID = in.readString();
                this.ISFAV = in.readString();
                this.ID = in.readString();
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
            dest.writeString(this.PS);
            dest.writeString(this.CNT);
            dest.writeString(this.NC);
            dest.writeString(this.PN);
            dest.writeList(this.LST);
        }

        protected BODYBean(Parcel in) {
            this.PS = in.readString();
            this.CNT = in.readString();
            this.NC = in.readString();
            this.PN = in.readString();
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
        dest.writeString(this.VERI);
        dest.writeString(this.SEQ);
        dest.writeString(this.CODE);
        dest.writeString(this.MSG);
        dest.writeParcelable(this.BODY, flags);
        dest.writeString(this.TOKEN);
    }

    protected DiscoveryListResultBean(Parcel in) {
        this.TYPE = in.readString();
        this.CMD = in.readString();
        this.ACCT = in.readString();
        this.TIME = in.readString();
        this.VERI = in.readString();
        this.SEQ = in.readString();
        this.CODE = in.readString();
        this.MSG = in.readString();
        this.BODY = in.readParcelable(BODYBean.class.getClassLoader());
        this.TOKEN = in.readString();
    }

    public static final Parcelable.Creator<DiscoveryListResultBean> CREATOR = new Parcelable.Creator<DiscoveryListResultBean>() {
        @Override
        public DiscoveryListResultBean createFromParcel(Parcel source) {
            return new DiscoveryListResultBean(source);
        }

        @Override
        public DiscoveryListResultBean[] newArray(int size) {
            return new DiscoveryListResultBean[size];
        }
    };
}
