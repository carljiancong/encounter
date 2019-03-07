package com.harmonycloud.result;


public class CodeMsg {
    private String msg;

    public CodeMsg() {
    }

    public CodeMsg(String msg) {
        this.msg = msg;
    }

    public static CodeMsg SERVICE_ERROR = new CodeMsg("Service error");
    public static CodeMsg URI = new CodeMsg("URI excepstion");
    public static CodeMsg QUERY_FAIL = new CodeMsg("query fail");

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
