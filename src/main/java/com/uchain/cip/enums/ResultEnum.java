package com.uchain.cip.enums;

public enum ResultEnum {

    /**
     * */
    SUCCESS(1000, "成功"),
    NOT_FIND_USER(1001, "未找到相应用户"),
    PASSWORD_ERROR(1002, "密码错误"),
    QUERY_FAIL(2001, "帖子查询失败")
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
