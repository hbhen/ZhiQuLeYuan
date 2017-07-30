package com.tongyuan.android.zhiquleyuan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by DTC on 2017/4/12.
 */

public class QueryMyPushResBean implements Parcelable {


    /**
     * TYPE : RES
     * CMD : MYPUSH
     * ACCT : XXXX
     * TIME : 20170709200554300
     * VERI :
     * SEQ : 1
     * CODE : 0
     * MSG :
     * BODY : {"LST":[{"IMG":"","ID":"201707091103161016840374","RCDID":"201707092004111016840471","DUR":"0:00:08","TIMES":"13","COLNAME":"",
     * "TYPE":"","COLID":"","NAME":"2017年07月09日 11时02分52秒.mp3","SIZE":"13.52KB","ENDTIME":"","REMARK":"","BEGINTIME":"20170709200411"},{"IMG":"",
     * "TYPE":"","RCDID":"201707092003541016840470","BEGINTIME":"20170709200355","TIMES":"4","DUR":"0:00:01","REMARK":"","NAME":"2017年07月09日
     * 11时50分50秒.mp3","COLID":"","ID":"201707091151021016840390","SIZE":"3.48KB","COLNAME":"","ENDTIME":""},{"ID":"201707091103161016840374",
     * "BEGINTIME":"20170709110330","NAME":"2017年07月09日 11时02分52秒.mp3","REMARK":"","COLID":"","DUR":"0:00:08","IMG":"","TIMES":"13","TYPE":"",
     * "ENDTIME":"","COLNAME":"","SIZE":"13.52KB","RCDID":"201707091103301016840377"},{"ID":"201707080933031016840316","NAME":"2017年07月08日09:32",
     * "TYPE":"","REMARK":"","SIZE":"25.40KB","COLID":"","RCDID":"201707080934341016840331","TIMES":"11","BEGINTIME":"20170708093435",
     * "DUR":"0:00:06","COLNAME":"","ENDTIME":"","IMG":""},{"COLNAME":"","COLID":"","DUR":"0:00:06","TIMES":"11","NAME":"2017年07月08日09:32",
     * "SIZE":"25.40KB","IMG":"","RCDID":"201707080933441016840328","BEGINTIME":"20170708093345","TYPE":"","REMARK":"","ENDTIME":"",
     * "ID":"201707080933031016840316"},{"ID":"201706041509200000003908","DUR":"0:03:18","REMARK":"","TYPE":"音频文件","IMG":"","ENDTIME":"",
     * "NAME":"感动未来mp3","RCDID":"201707021849391016839698","SIZE":"3.04MB","BEGINTIME":"20170702184939","COLNAME":"儿童歌谣 ",
     * "COLID":"201610301737481016432787 ","TIMES":"7"},{"COLID":"201610301737481016432787 ","DUR":"0:02:01","IMG":"","COLNAME":"儿童歌谣 ",
     * "RCDID":"201707021849161016839697","REMARK":"","ID":"201706041509200000003907","ENDTIME":"","BEGINTIME":"20170702184917","TIMES":"19",
     * "SIZE":"2.53MB","TYPE":"音频文件","NAME":"小螺号mp3"},{"COLID":"201702091131341016563469 ","TIMES":"306","ID":"201705151824191016803202",
     * "RCDID":"201706291256311016839289","COLNAME":"绘本故事 ","DUR":"0:10:33","NAME":"猪八戒吃西瓜","SIZE":"9.68MB","ENDTIME":"","TYPE":"音频文件",
     * "BEGINTIME":"20170629125631","REMARK":"","IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206
     * .jpg"},{"COLNAME":"绘本故事 ","TIMES":"306","RCDID":"201706291256291016839288","SIZE":"9.68MB","REMARK":"","IMG":"http://120.27.41
     * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","BEGINTIME":"20170629125629","NAME":"猪八戒吃西瓜",
     * "DUR":"0:10:33","ENDTIME":"","ID":"201705151824191016803202","TYPE":"音频文件","COLID":"201702091131341016563469 "},{"TYPE":"音频文件",
     * "COLNAME":"儿童歌谣 ","ID":"201706041509200000003906","RCDID":"201706222052311016837779","NAME":"捉泥鳅","REMARK":"","TIMES":"39","IMG":"http://120
     * .27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170702/201707021625101016839670.jpg","ENDTIME":"","SIZE":"7.71MB","DUR":"0:03:21",
     * "BEGINTIME":"20170622205231","COLID":"201610301737481016432787 "}],"PN":"1","CNT":"75","NC":"65","PS":"10"}
     * TOKEN : 5e2d5649-6030-47c8-9efc-f02487df1b6a
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
        return "QueryMyPushResBean{" +
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

    public QueryMyPushResBean(String TYPE, String CMD, String ACCT, String TIME, String VERI, String SEQ, String CODE, String MSG, BODYBean BODY,
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
         * LST : [{"IMG":"","ID":"201707091103161016840374","RCDID":"201707092004111016840471","DUR":"0:00:08","TIMES":"13","COLNAME":"","TYPE":"",
         * "COLID":"","NAME":"2017年07月09日 11时02分52秒.mp3","SIZE":"13.52KB","ENDTIME":"","REMARK":"","BEGINTIME":"20170709200411"},{"IMG":"",
         * "TYPE":"","RCDID":"201707092003541016840470","BEGINTIME":"20170709200355","TIMES":"4","DUR":"0:00:01","REMARK":"","NAME":"2017年07月09日
         * 11时50分50秒.mp3","COLID":"","ID":"201707091151021016840390","SIZE":"3.48KB","COLNAME":"","ENDTIME":""},{"ID":"201707091103161016840374",
         * "BEGINTIME":"20170709110330","NAME":"2017年07月09日 11时02分52秒.mp3","REMARK":"","COLID":"","DUR":"0:00:08","IMG":"","TIMES":"13","TYPE":"",
         * "ENDTIME":"","COLNAME":"","SIZE":"13.52KB","RCDID":"201707091103301016840377"},{"ID":"201707080933031016840316",
         * "NAME":"2017年07月08日09:32","TYPE":"","REMARK":"","SIZE":"25.40KB","COLID":"","RCDID":"201707080934341016840331","TIMES":"11",
         * "BEGINTIME":"20170708093435","DUR":"0:00:06","COLNAME":"","ENDTIME":"","IMG":""},{"COLNAME":"","COLID":"","DUR":"0:00:06","TIMES":"11",
         * "NAME":"2017年07月08日09:32","SIZE":"25.40KB","IMG":"","RCDID":"201707080933441016840328","BEGINTIME":"20170708093345","TYPE":"",
         * "REMARK":"","ENDTIME":"","ID":"201707080933031016840316"},{"ID":"201706041509200000003908","DUR":"0:03:18","REMARK":"","TYPE":"音频文件",
         * "IMG":"","ENDTIME":"","NAME":"感动未来mp3","RCDID":"201707021849391016839698","SIZE":"3.04MB","BEGINTIME":"20170702184939","COLNAME":"儿童歌谣
         * ","COLID":"201610301737481016432787 ","TIMES":"7"},{"COLID":"201610301737481016432787 ","DUR":"0:02:01","IMG":"","COLNAME":"儿童歌谣 ",
         * "RCDID":"201707021849161016839697","REMARK":"","ID":"201706041509200000003907","ENDTIME":"","BEGINTIME":"20170702184917","TIMES":"19",
         * "SIZE":"2.53MB","TYPE":"音频文件","NAME":"小螺号mp3"},{"COLID":"201702091131341016563469 ","TIMES":"306","ID":"201705151824191016803202",
         * "RCDID":"201706291256311016839289","COLNAME":"绘本故事 ","DUR":"0:10:33","NAME":"猪八戒吃西瓜","SIZE":"9.68MB","ENDTIME":"","TYPE":"音频文件",
         * "BEGINTIME":"20170629125631","REMARK":"","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg"},{"COLNAME":"绘本故事 ","TIMES":"306",
         * "RCDID":"201706291256291016839288","SIZE":"9.68MB","REMARK":"","IMG":"http://120.27.41
         * .179:8081/zqpland/resource/thumbnail/5/jpg/20170515/201705151824401016803206.jpg","BEGINTIME":"20170629125629","NAME":"猪八戒吃西瓜",
         * "DUR":"0:10:33","ENDTIME":"","ID":"201705151824191016803202","TYPE":"音频文件","COLID":"201702091131341016563469 "},{"TYPE":"音频文件",
         * "COLNAME":"儿童歌谣 ","ID":"201706041509200000003906","RCDID":"201706222052311016837779","NAME":"捉泥鳅","REMARK":"","TIMES":"39",
         * "IMG":"http://120.27.41.179:8081/zqpland/resource/thumbnail/5/jpg/20170702/201707021625101016839670.jpg","ENDTIME":"","SIZE":"7.71MB",
         * "DUR":"0:03:21","BEGINTIME":"20170622205231","COLID":"201610301737481016432787 "}]
         * PN : 1
         * CNT : 75
         * NC : 65
         * PS : 10
         */

        private String PN;
        private String CNT;
        private String NC;
        private String PS;
        private List<LSTBean> LST;

        @Override
        public String toString() {
            return "BODYBean{" +
                    "PN='" + PN + '\'' +
                    ", CNT='" + CNT + '\'' +
                    ", NC='" + NC + '\'' +
                    ", PS='" + PS + '\'' +
                    ", LST=" + LST +
                    '}';
        }

        public BODYBean(String PN, String CNT, String NC, String PS, List<LSTBean> LST) {
            this.PN = PN;
            this.CNT = CNT;
            this.NC = NC;
            this.PS = PS;
            this.LST = LST;
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

        public String getNC() {
            return NC;
        }

        public void setNC(String NC) {
            this.NC = NC;
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

        public static class LSTBean extends MusicPlayerBean {
            /**
             * IMG :
             * ID : 201707091103161016840374
             * RCDID : 201707092004111016840471
             * DUR : 0:00:08
             * TIMES : 13
             * COLNAME :
             * TYPE :
             * COLID :
             * NAME : 2017年07月09日 11时02分52秒.mp3
             * SIZE : 13.52KB
             * ENDTIME :
             * REMARK :
             * BEGINTIME : 20170709200411
             */

//            private String IMG;
//            private String ID;
            private String RCDID;
            private String DUR;
            private String TIMES;
            private String COLNAME;
            private String TYPE;
            private String COLID;
            //            private String NAME;
            private String SIZE;
            private String ENDTIME;
            private String REMARK;
            private String BEGINTIME;

            @Override
            public String toString() {
                return "LSTBean{" +
                        "IMG='" + IMG + '\'' +
                        ", ID='" + ID + '\'' +
                        ", RCDID='" + RCDID + '\'' +
                        ", DUR='" + DUR + '\'' +
                        ", TIMES='" + TIMES + '\'' +
                        ", COLNAME='" + COLNAME + '\'' +
                        ", TYPE='" + TYPE + '\'' +
                        ", COLID='" + COLID + '\'' +
                        ", NAME='" + NAME + '\'' +
                        ", SIZE='" + SIZE + '\'' +
                        ", ENDTIME='" + ENDTIME + '\'' +
                        ", REMARK='" + REMARK + '\'' +
                        ", BEGINTIME='" + BEGINTIME + '\'' +
                        '}';
            }

            public LSTBean(String IMG, String ID, String RCDID, String DUR, String TIMES, String COLNAME, String TYPE, String COLID, String NAME,
                           String SIZE, String ENDTIME, String REMARK, String BEGINTIME) {
                this.IMG = IMG;
                this.ID = ID;
                this.RCDID = RCDID;
                this.DUR = DUR;
                this.TIMES = TIMES;
                this.COLNAME = COLNAME;
                this.TYPE = TYPE;
                this.COLID = COLID;
                this.NAME = NAME;
                this.SIZE = SIZE;
                this.ENDTIME = ENDTIME;
                this.REMARK = REMARK;
                this.BEGINTIME = BEGINTIME;
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

            public String getRCDID() {
                return RCDID;
            }

            public void setRCDID(String RCDID) {
                this.RCDID = RCDID;
            }

            public String getDUR() {
                return DUR;
            }

            public void setDUR(String DUR) {
                this.DUR = DUR;
            }

            public String getTIMES() {
                return TIMES;
            }

            public void setTIMES(String TIMES) {
                this.TIMES = TIMES;
            }

            public String getCOLNAME() {
                return COLNAME;
            }

            public void setCOLNAME(String COLNAME) {
                this.COLNAME = COLNAME;
            }

            public String getTYPE() {
                return TYPE;
            }

            public void setTYPE(String TYPE) {
                this.TYPE = TYPE;
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

            public String getSIZE() {
                return SIZE;
            }

            public void setSIZE(String SIZE) {
                this.SIZE = SIZE;
            }

            public String getENDTIME() {
                return ENDTIME;
            }

            public void setENDTIME(String ENDTIME) {
                this.ENDTIME = ENDTIME;
            }

            public String getREMARK() {
                return REMARK;
            }

            public void setREMARK(String REMARK) {
                this.REMARK = REMARK;
            }

            public String getBEGINTIME() {
                return BEGINTIME;
            }

            public void setBEGINTIME(String BEGINTIME) {
                this.BEGINTIME = BEGINTIME;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
                dest.writeString(this.RCDID);
                dest.writeString(this.DUR);
                dest.writeString(this.TIMES);
                dest.writeString(this.COLNAME);
                dest.writeString(this.TYPE);
                dest.writeString(this.COLID);
                dest.writeString(this.SIZE);
                dest.writeString(this.ENDTIME);
                dest.writeString(this.REMARK);
                dest.writeString(this.BEGINTIME);
            }

            protected LSTBean(Parcel in) {
                super(in);
                this.RCDID = in.readString();
                this.DUR = in.readString();
                this.TIMES = in.readString();
                this.COLNAME = in.readString();
                this.TYPE = in.readString();
                this.COLID = in.readString();
                this.SIZE = in.readString();
                this.ENDTIME = in.readString();
                this.REMARK = in.readString();
                this.BEGINTIME = in.readString();
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
            dest.writeString(this.NC);
            dest.writeString(this.PS);
            dest.writeTypedList(this.LST);
        }

        protected BODYBean(Parcel in) {
            this.PN = in.readString();
            this.CNT = in.readString();
            this.NC = in.readString();
            this.PS = in.readString();
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

    protected QueryMyPushResBean(Parcel in) {
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

    public static final Parcelable.Creator<QueryMyPushResBean> CREATOR = new Parcelable.Creator<QueryMyPushResBean>() {
        @Override
        public QueryMyPushResBean createFromParcel(Parcel source) {
            return new QueryMyPushResBean(source);
        }

        @Override
        public QueryMyPushResBean[] newArray(int size) {
            return new QueryMyPushResBean[size];
        }
    };
}
