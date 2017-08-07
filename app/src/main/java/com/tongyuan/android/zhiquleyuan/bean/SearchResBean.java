package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Android on 2017/7/18.
 */
public class SearchResBean implements Parcelable {
    /**
     * TYPE : RES
     * CMD : SEARES
     * ACCT : XXXX
     * TIME : 20170718112552262
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"PS":"10","NC":"2","PN":"1","LST":[{"DUR":"0:05:48","TYPE":"音频文件","TIMES":"0","REMARK":"","SIZE":"2.00MB","COLNAME":"","IMG":"",
     * "ID":"201706041509200000002730","NAME":"68哈哈镜","COLID":""},{"NAME":"哈哈笑mp3","TIMES":"0","COLID":"201610301737481016432787 ","SIZE":"1.95MB",
     * "DUR":"0:02:07","REMARK":"","COLNAME":"儿童歌谣 ","IMG":"","ID":"201706041509200000003969","TYPE":"音频文件"},{"ID":"201706041509200000004213",
     * "IMG":"","TIMES":"0","DUR":"0:03:53","REMARK":"","COLID":"201610301737481016432787 ","TYPE":"音频文件","SIZE":"5.85MB","NAME":"娃哈哈mp3",
     * "COLNAME":"儿童歌谣 "},{"ID":"201706041509200000004309","TIMES":"0","NAME":"哇哈哈mp3","COLNAME":"儿童歌谣 ","IMG":"","SIZE":"784.62KB","TYPE":"音频文件",
     * "DUR":"0:00:49","REMARK":"","COLID":"201610301737481016432787 "},{"TYPE":"音频文件","IMG":"","COLNAME":"","ID":"201706041509200000004471",
     * "TIMES":"0","SIZE":"2.84MB","NAME":"46真假哈哈","REMARK":"","COLID":"","DUR":"0:03:33"},{"TYPE":"音频文件","NAME":"13大哈哈镜里的怪物-能力培养","COLID":"",
     * "TIMES":"0","SIZE":"4.74MB","IMG":"","ID":"201706041509200000004990","COLNAME":"","REMARK":"","DUR":"0:03:38"},{"NAME":"53狮子照哈哈镜",
     * "COLID":"","TIMES":"0","ID":"201706041509200000005202","SIZE":"1.64MB","REMARK":"","IMG":"","DUR":"0:03:33","COLNAME":"","TYPE":"音频文件"},
     * {"TYPE":"音频文件","TIMES":"0","COLNAME":"","COLID":"","ID":"201706041509200000005206","NAME":"18狮子照哈哈镜","IMG":"","REMARK":"","DUR":"0:04:22",
     * "SIZE":"2.01MB"},{"ID":"201706041509200000009931","TIMES":"1","DUR":"0:03:34","TYPE":"音频文件","SIZE":"1.64MB","COLNAME":"睡前故事 ",
     * "NAME":"狮子照哈哈镜","IMG":"","COLID":"201702091131241016563468 ","REMARK":""},{"IMG":"","COLNAME":"睡前故事 ","ID":"201706041509200000010879",
     * "SIZE":"1.64MB","DUR":"0:03:34","REMARK":"","TIMES":"0","COLID":"201702091131241016563468 ","NAME":"狮子照哈哈镜","TYPE":"音频文件"}],"CNT":"12"}
     * TOKEN : 2
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
        return "SearchResBean{" +
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
         * NC : 2
         * PN : 1
         * LST : [{"DUR":"0:05:48","TYPE":"音频文件","TIMES":"0","REMARK":"","SIZE":"2.00MB","COLNAME":"","IMG":"","ID":"201706041509200000002730",
         * "NAME":"68哈哈镜","COLID":""},{"NAME":"哈哈笑mp3","TIMES":"0","COLID":"201610301737481016432787 ","SIZE":"1.95MB","DUR":"0:02:07","REMARK":"",
         * "COLNAME":"儿童歌谣 ","IMG":"","ID":"201706041509200000003969","TYPE":"音频文件"},{"ID":"201706041509200000004213","IMG":"","TIMES":"0",
         * "DUR":"0:03:53","REMARK":"","COLID":"201610301737481016432787 ","TYPE":"音频文件","SIZE":"5.85MB","NAME":"娃哈哈mp3","COLNAME":"儿童歌谣 "},
         * {"ID":"201706041509200000004309","TIMES":"0","NAME":"哇哈哈mp3","COLNAME":"儿童歌谣 ","IMG":"","SIZE":"784.62KB","TYPE":"音频文件","DUR":"0:00:49",
         * "REMARK":"","COLID":"201610301737481016432787 "},{"TYPE":"音频文件","IMG":"","COLNAME":"","ID":"201706041509200000004471","TIMES":"0",
         * "SIZE":"2.84MB","NAME":"46真假哈哈","REMARK":"","COLID":"","DUR":"0:03:33"},{"TYPE":"音频文件","NAME":"13大哈哈镜里的怪物-能力培养","COLID":"","TIMES":"0",
         * "SIZE":"4.74MB","IMG":"","ID":"201706041509200000004990","COLNAME":"","REMARK":"","DUR":"0:03:38"},{"NAME":"53狮子照哈哈镜","COLID":"",
         * "TIMES":"0","ID":"201706041509200000005202","SIZE":"1.64MB","REMARK":"","IMG":"","DUR":"0:03:33","COLNAME":"","TYPE":"音频文件"},
         * {"TYPE":"音频文件","TIMES":"0","COLNAME":"","COLID":"","ID":"201706041509200000005206","NAME":"18狮子照哈哈镜","IMG":"","REMARK":"",
         * "DUR":"0:04:22","SIZE":"2.01MB"},{"ID":"201706041509200000009931","TIMES":"1","DUR":"0:03:34","TYPE":"音频文件","SIZE":"1.64MB",
         * "COLNAME":"睡前故事 ","NAME":"狮子照哈哈镜","IMG":"","COLID":"201702091131241016563468 ","REMARK":""},{"IMG":"","COLNAME":"睡前故事 ",
         * "ID":"201706041509200000010879","SIZE":"1.64MB","DUR":"0:03:34","REMARK":"","TIMES":"0","COLID":"201702091131241016563468 ",
         * "NAME":"狮子照哈哈镜","TYPE":"音频文件"}]
         * CNT : 12
         */

        private String PS;
        private String NC;
        private String PN;
        private String CNT;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PS='" + PS + '\'' +
                    ", NC='" + NC + '\'' +
                    ", PN='" + PN + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PS, String NC, String PN, String CNT, List<LSTBean> LST) {
            this.PS = PS;
            this.NC = NC;
            this.PN = PN;
            this.CNT = CNT;
            this.LST = LST;
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

        public List<LSTBean> getLST() {
            return LST;
        }

        public void setLST(List<LSTBean> LST) {
            this.LST = LST;
        }

        public static class LSTBean extends MusicPlayerBean implements Parcelable {
            /**
             * DUR : 0:05:48
             * TYPE : 音频文件
             * TIMES : 0
             * REMARK :
             * SIZE : 2.00MB
             * COLNAME :
             * IMG :
             * ID : 201706041509200000002730
             * NAME : 68哈哈镜
             * COLID :
             */

            private String DUR;
            private String TYPE;
            private String TIMES;
            private String REMARK;
            private String SIZE;
            private String COLNAME;
            //            private String IMG;
//            private String ID;
//            private String NAME;
            private String COLID;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "DUR='" + DUR + '\'' +
                        ", TYPE='" + TYPE + '\'' +
                        ", TIMES='" + TIMES + '\'' +
                        ", REMARK='" + REMARK + '\'' +
                        ", SIZE='" + SIZE + '\'' +
                        ", COLNAME='" + COLNAME + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", ID='" + ID + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", COLID='" + COLID + '\'' +
                        '}';
            }

            public LSTBean(String DUR, String TYPE, String TIMES, String REMARK, String SIZE, String COLNAME, String IMG, String ID, String NAME,
                           String COLID) {
                this.DUR = DUR;
                this.TYPE = TYPE;
                this.TIMES = TIMES;
                this.REMARK = REMARK;
                this.SIZE = SIZE;
                this.COLNAME = COLNAME;
                this.IMG = IMG;
                this.ID = ID;
                this.NAME = NAME;
                this.COLID = COLID;
            }

            public String getDUR() {
                return DUR;
            }

            public void setDUR(String DUR) {
                this.DUR = DUR;
            }

            public String getTYPE() {
                return TYPE;
            }

            public void setTYPE(String TYPE) {
                this.TYPE = TYPE;
            }

            public String getTIMES() {
                return TIMES;
            }

            public void setTIMES(String TIMES) {
                this.TIMES = TIMES;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getSIZE() {
                return SIZE;
            }

            public void setSIZE(String SIZE) {
                this.SIZE = SIZE;
            }

            public String getCOLNAME() {
                return COLNAME;
            }

            public void setCOLNAME(String COLNAME) {
                this.COLNAME = COLNAME;
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

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getCOLID() {
                return COLID;
            }

            public void setCOLID(String COLID) {
                this.COLID = COLID;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
                dest.writeString(this.DUR);
                dest.writeString(this.TYPE);
                dest.writeString(this.TIMES);
                dest.writeString(this.REMARK);
                dest.writeString(this.SIZE);
                dest.writeString(this.COLNAME);
                dest.writeString(this.COLID);
            }

            protected LSTBean(Parcel in) {
                super(in);
                this.DUR = in.readString();
                this.TYPE = in.readString();
                this.TIMES = in.readString();
                this.REMARK = in.readString();
                this.SIZE = in.readString();
                this.COLNAME = in.readString();
                this.COLID = in.readString();
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
            dest.writeString(this.NC);
            dest.writeString(this.PN);
            dest.writeString(this.CNT);
            dest.writeTypedList(this.LST);
        }

        protected BODYBean(Parcel in) {
            this.PS = in.readString();
            this.NC = in.readString();
            this.PN = in.readString();
            this.CNT = in.readString();
            this.LST = in.createTypedArrayList(LSTBean.CREATOR);
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

    protected SearchResBean(Parcel in) {
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

    public static final Parcelable.Creator<SearchResBean> CREATOR = new Parcelable.Creator<SearchResBean>() {
        @Override
        public SearchResBean createFromParcel(Parcel source) {
            return new SearchResBean(source);
        }

        @Override
        public SearchResBean[] newArray(int size) {
            return new SearchResBean[size];
        }
    };
}
