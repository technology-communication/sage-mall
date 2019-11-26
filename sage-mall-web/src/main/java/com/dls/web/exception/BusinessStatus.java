package com.dls.web.exception;

public enum BusinessStatus {
    TEST_STATUS(1, "13");
    private int code;
    private String msgCode;

    private BusinessStatus(int code, String msgCode) {
        this.code = code;
        this.msgCode = msgCode;
    }

    public int getCode() {
        return code;
    }

    public String getMsgCode() {
        return msgCode;
    }
}
