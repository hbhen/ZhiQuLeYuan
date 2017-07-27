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
     * CMD : QRYREC
     * ACCT : 13628327181
     * TIME : 20170606171814674
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"NC":"22","CNT":"32","LST":[{"ID":"201705151824191016803202","REMARK":"","SIZE":"9.68MB","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","COLID":"201702091131341016563469 ","TIMES":"40",
     * "NAME":"猪八戒吃西瓜","TYPE":"音频文件","COLNAME":"绘本故事 ","DUR":"0:10:33"},{"TIMES":"14","COLNAME":"睡前故事 ","TYPE":"音频文件","SIZE":"2.05MB",
     * "DUR":"0:04:28","COLID":"201702091131241016563468 ","NAME":"小斑马过河","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151810031016803168.jpg","ID":"201705151810031016803167","REMARK":""},
     * {"DUR":"0:02:02","TYPE":"音频文件","COLNAME":"睡前故事 ","COLID":"201702091131241016563468 ","SIZE":"954.95KB","REMARK":"","NAME":"愚公移山","IMG":"",
     * "TIMES":"1","ID":"201706041509200000012266"},{"DUR":"0:02:15","COLID":"201702091131241016563468 ","TIMES":"0","SIZE":"1.04MB",
     * "NAME":"小花猫的胡子","COLNAME":"睡前故事 ","ID":"201706041509200000012265","IMG":"","REMARK":"","TYPE":"音频文件"},{"COLNAME":"睡前故事 ",
     * "COLID":"201702091131241016563468 ","IMG":"","TIMES":"0","ID":"201706041509200000012264","SIZE":"1.51MB","TYPE":"音频文件","REMARK":"",
     * "NAME":"脏嘴巴的小白兔","DUR":"0:03:17"},{"SIZE":"2.24MB","COLID":"201702091131241016563468 ","TIMES":"0","REMARK":"","TYPE":"音频文件",
     * "DUR":"0:04:53","COLNAME":"睡前故事 ","ID":"201706041509200000012263","NAME":"十二生肖的故事","IMG":""},{"ID":"201706041509200000012262",
     * "DUR":"0:06:00","COLID":"201702091131241016563468 ","TYPE":"音频文件","REMARK":"","IMG":"","TIMES":"0","SIZE":"2.76MB","COLNAME":"睡前故事 ",
     * "NAME":"花木兰替父从军"},{"ID":"201706041509200000012261","COLNAME":"睡前故事 ","TYPE":"音频文件","NAME":"小花猫种鱼","COLID":"201702091131241016563468 ",
     * "TIMES":"0","SIZE":"1.16MB","IMG":"","REMARK":"","DUR":"0:02:31"},{"COLID":"201702091131241016563468 ","TYPE":"音频文件","TIMES":"0","IMG":"",
     * "DUR":"0:04:38","ID":"201706041509200000012260","REMARK":"","COLNAME":"睡前故事 ","SIZE":"4.25MB","NAME":"渔童"},{"REMARK":"","COLNAME":"睡前故事 ",
     * "COLID":"201702091131241016563468 ","NAME":"河马先生的魔术","DUR":"0:01:38","IMG":"","TYPE":"音频文件","TIMES":"0","SIZE":"770.65KB",
     * "ID":"201706041509200000012259"}],"PS":"10","PN":"1"}
     * TOKEN : 329fbc135-787f-44dc-bfe7-70272a8bac27
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
         * NC : 22
         * CNT : 32
         * LST : [{"ID":"201705151824191016803202","REMARK":"","SIZE":"9.68MB","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","COLID":"201702091131341016563469 ","TIMES":"40",
         * "NAME":"猪八戒吃西瓜","TYPE":"音频文件","COLNAME":"绘本故事 ","DUR":"0:10:33"},{"TIMES":"14","COLNAME":"睡前故事 ","TYPE":"音频文件","SIZE":"2.05MB",
         * "DUR":"0:04:28","COLID":"201702091131241016563468 ","NAME":"小斑马过河","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151810031016803168.jpg","ID":"201705151810031016803167","REMARK":""},
         * {"DUR":"0:02:02","TYPE":"音频文件","COLNAME":"睡前故事 ","COLID":"201702091131241016563468 ","SIZE":"954.95KB","REMARK":"","NAME":"愚公移山",
         * "IMG":"","TIMES":"1","ID":"201706041509200000012266"},{"DUR":"0:02:15","COLID":"201702091131241016563468 ","TIMES":"0","SIZE":"1.04MB",
         * "NAME":"小花猫的胡子","COLNAME":"睡前故事 ","ID":"201706041509200000012265","IMG":"","REMARK":"","TYPE":"音频文件"},{"COLNAME":"睡前故事 ",
         * "COLID":"201702091131241016563468 ","IMG":"","TIMES":"0","ID":"201706041509200000012264","SIZE":"1.51MB","TYPE":"音频文件","REMARK":"",
         * "NAME":"脏嘴巴的小白兔","DUR":"0:03:17"},{"SIZE":"2.24MB","COLID":"201702091131241016563468 ","TIMES":"0","REMARK":"","TYPE":"音频文件",
         * "DUR":"0:04:53","COLNAME":"睡前故事 ","ID":"201706041509200000012263","NAME":"十二生肖的故事","IMG":""},{"ID":"201706041509200000012262",
         * "DUR":"0:06:00","COLID":"201702091131241016563468 ","TYPE":"音频文件","REMARK":"","IMG":"","TIMES":"0","SIZE":"2.76MB","COLNAME":"睡前故事 ",
         * "NAME":"花木兰替父从军"},{"ID":"201706041509200000012261","COLNAME":"睡前故事 ","TYPE":"音频文件","NAME":"小花猫种鱼","COLID":"201702091131241016563468 ",
         * "TIMES":"0","SIZE":"1.16MB","IMG":"","REMARK":"","DUR":"0:02:31"},{"COLID":"201702091131241016563468 ","TYPE":"音频文件","TIMES":"0",
         * "IMG":"","DUR":"0:04:38","ID":"201706041509200000012260","REMARK":"","COLNAME":"睡前故事 ","SIZE":"4.25MB","NAME":"渔童"},{"REMARK":"",
         * "COLNAME":"睡前故事 ","COLID":"201702091131241016563468 ","NAME":"河马先生的魔术","DUR":"0:01:38","IMG":"","TYPE":"音频文件","TIMES":"0","SIZE":"770
         * .65KB","ID":"201706041509200000012259"}]
         * PS : 10
         * PN : 1
         */

        private String NC;
        private String CNT;
        private String PS;
        private String PN;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "NC='" + NC + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", PS='" + PS + '\'' +
                    ", PN='" + PN + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String NC, String CNT, String PS, String PN, List<LSTBean> LST) {
            this.NC = NC;
            this.CNT = CNT;
            this.PS = PS;
            this.PN = PN;
            this.LST = LST;
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

        public static class LSTBean implements Parcelable {

            public LSTBean() {}
            /**
             * ID : 201705151824191016803202
             * REMARK :
             * SIZE : 9.68MB
             * IMG : http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg
             * COLID : 201702091131341016563469
             * TIMES : 40
             * NAME : 猪八戒吃西瓜
             * TYPE : 音频文件
             * COLNAME : 绘本故事
             * DUR : 0:10:33
             */

            private String ID;
            private String REMARK;
            private String SIZE;
            private String IMG;
            private String COLID;
            private String TIMES;
            private String NAME;
            private String TYPE;
            private String COLNAME;
            private String DUR;
            public boolean isPlaying = false;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "ID='" + ID + '\'' +
                        ", REMARK='" + REMARK + '\'' +
                        ", SIZE='" + SIZE + '\'' +
                        ", IMG='" + IMG + '\'' +
                        ", COLID='" + COLID + '\'' +
                        ", TIMES='" + TIMES + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", TYPE='" + TYPE + '\'' +
                        ", COLNAME='" + COLNAME + '\'' +
                        ", DUR='" + DUR + '\'' +
                        '}';
            }



            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
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

            public String getTIMES() {
                return TIMES;
            }

            public void setTIMES(String TIMES) {
                this.TIMES = TIMES;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getTYPE() {
                return TYPE;
            }

            public void setTYPE(String TYPE) {
                this.TYPE = TYPE;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.ID);
                dest.writeString(this.REMARK);
                dest.writeString(this.SIZE);
                dest.writeString(this.IMG);
                dest.writeString(this.COLID);
                dest.writeString(this.TIMES);
                dest.writeString(this.NAME);
                dest.writeString(this.TYPE);
                dest.writeString(this.COLNAME);
                dest.writeString(this.DUR);
            }

            protected LSTBean(Parcel in) {
                this.ID = in.readString();
                this.REMARK = in.readString();
                this.SIZE = in.readString();
                this.IMG = in.readString();
                this.COLID = in.readString();
                this.TIMES = in.readString();
                this.NAME = in.readString();
                this.TYPE = in.readString();
                this.COLNAME = in.readString();
                this.DUR = in.readString();
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
            dest.writeString(this.CNT);
            dest.writeString(this.PS);
            dest.writeString(this.PN);
            dest.writeList(this.LST);
        }

        protected BODYBean(Parcel in) {
            this.NC = in.readString();
            this.CNT = in.readString();
            this.PS = in.readString();
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
