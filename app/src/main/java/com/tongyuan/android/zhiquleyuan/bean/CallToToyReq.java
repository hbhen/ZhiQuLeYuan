package com.tongyuan.android.zhiquleyuan.bean;

/**
 * Created by DTC on 2017/5/8.
 */

public class CallToToyReq {

    /**
     * cmd : contact_toy
     * param : {"toyid":"1","method":"1","room":"666666"}
     * token : xxx
     */

    private String cmd;
    private ParamBean param;
    private String token;

    @Override
    public String toString() {
        return "CallToToyReq{" +
                "cmd='" + cmd + '\'' +
                ", param=" + param +
                ", token='" + token + '\'' +
                '}';
    }

    public CallToToyReq(String cmd, ParamBean param, String token) {
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
         * toyid : 1
         * method : 1
         * room : 666666
         */

        private String toyid;
        private String method;
        private String room;

        @Override
        public String toString() {
            return "ParamBean{" +
                    "toyid='" + toyid + '\'' +
                    ", method='" + method + '\'' +
                    ", room='" + room + '\'' +
                    '}';
        }

        public ParamBean(String toyid, String method, String room) {
            this.toyid = toyid;
            this.method = method;
            this.room = room;
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

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }
    }
}
