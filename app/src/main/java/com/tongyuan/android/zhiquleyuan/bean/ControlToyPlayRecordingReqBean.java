package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by Android on 2017/6/17.
 */

public class ControlToyPlayRecordingReqBean {
    /**
     * cmd : control_play
     * param : {"toyid":"201705081005211016644025","method":"1","resid":"201706171543321016824612","start":"0"}
     * token : c19d5a15-592a-4b78-863e-846e789163be
     */

    private String cmd;
    private ParamBean param;
    private String token;

    @Override
    public String toString() {
        return "ControlToyPlayRecordingReqBean{" +
                "cmd='" + cmd + '\'' +
                ", param=" + param +
                ", token='" + token + '\'' +
                '}';
    }

    public ControlToyPlayRecordingReqBean(String cmd, ParamBean param, String token) {
        this.cmd = cmd;
        this.param = param;
        this.token = token;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class ParamBean {
        /**
         * toyid : 201705081005211016644025
         * method : 1
         * resid : 201706171543321016824612
         * start : 0
         */

        private String toyid;
        private String method;
        private String resid;
        private String start;

        @Override
        public String toString() {
            return "ParamBean{" +
                    "toyid='" + toyid + '\'' +
                    ", method='" + method + '\'' +
                    ", resid='" + resid + '\'' +
                    ", start='" + start + '\'' +
                    '}';
        }

        public ParamBean(String toyid, String method, String resid, String start) {
            this.toyid = toyid;
            this.method = method;
            this.resid = resid;
            this.start = start;
        }

        public String getToyid() {
            return toyid;
        }

        public void setToyid(String toyid) {
            this.toyid = toyid;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getResid() {
            return resid;
        }

        public void setResid(String resid) {
            this.resid = resid;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }
    }
}
