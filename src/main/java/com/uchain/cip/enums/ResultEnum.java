package com.uchain.cip.enums;

public enum ResultEnum {

    /**
     * */
    SUCCESS(1000, "成功"),
    FAIL(1001, "失败"),
    NOT_FIND_USER(1003, "未找到相应用户"),
    PASSWORD_ERROR(1004, "密码错误"),
    ;


    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
