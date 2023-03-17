package com.uchain.cip.enums;

public enum ResultEnum {

    /**
     * */
    SUCCESS(1000, "成功"),
    FAIL(1001, "失败"),
    LOGIN_SUCCESS(1002, "登陆成功"),
    LOGIN_FAIL(1003, "账号或密码错误，请检查后重试"),
    NOT_FIND_USER(1004, "未找到相应用户"),
    PASSWORD_ERROR(1005, "密码错误"),
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
