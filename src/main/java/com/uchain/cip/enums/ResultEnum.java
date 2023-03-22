package com.uchain.cip.enums;

public enum ResultEnum {

    /**
     * */
    SUCCESS(1001, "成功"),
    FAIL(1000, "失败"),
    LOGIN_SUCCESS(1011, "登陆成功"),
    LOGIN_FAIL(1020, "账号或密码错误，请检查后重试"),
    NOT_FIND_USER(1030, "未找到相应用户"),
    PASSWORD_ERROR(1040, "密码错误"),
    EMAIL_ALREADY_EXISTS(1050, "邮箱已被注册"),
    NICKNAME_ALREADY_EXISTS(1060, "用户昵称已存在"),
    REGISTER_SUCCESS(1071, "注册成功"),
    REGISTER_FAIL(1070, "验证码错误"),
    EMAIL_SEN_FAIL(1080, "邮件发送失败，请检查邮箱是否正确"),
    EMAIL_SEN_SUCCESS(1081, "验证码发送成功，请注意查收"),
    VERIFY_CODE_ERROR(1090, "验证码错误"),
    DATA_QUERY_SUCCESS(2001, "数据查询成功"),
    DATA_QUERY_FAIL(2000, "数据查询失败"),
    UNKNOWN_ERROR(9999, "未知错误，请刷新页面后重试")
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
