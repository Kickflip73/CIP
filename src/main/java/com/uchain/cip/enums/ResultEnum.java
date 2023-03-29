package com.uchain.cip.enums;

/**
 * 返回数据枚举类
 * */

public enum ResultEnum {

    /**
     * 通用
     * */
    SUCCESS(1001, "成功"),
    FAIL(1000, "失败"),

    /**
     * 登录相关
     * */
    LOGIN_SUCCESS(1011, "登陆成功"),
    LOGIN_FAIL(1020, "账号或密码错误，请检查后重试"),

    /**
     * 注册相关
     * */
    REGISTER_SUCCESS(1051, "注册成功"),
    REGISTER_FAIL(1050, "注册失败"),
    EMAIL_ALREADY_EXISTS(1060, "邮箱已被注册"),
    NICKNAME_ALREADY_EXISTS(1070, "用户昵称已存在"),
    EMAIL_SEN_SUCCESS(1091, "验证码发送成功，请注意查收"),
    EMAIL_SEN_FAIL(1090, "邮件发送失败，请检查邮箱是否正确"),
    VERIFY_CODE_ERROR(1100, "验证码错误"),

    /**
     * 账户相关
     * */
    USER_NOT_EXIST(1500, "此账户不存在"),
    UPDATE_USER_SUCCESS(1601, "修改信息成功"),
    UPDATE_USER_FAIL(1600, "修改信息失败"),

    /**
     * 比赛帖子相关
     * */
    COMPETITION_DATA_QUERY_SUCCESS(2001, "查询比赛信息成功"),
    COMPETITION_DATA_QUERY_FAIL(2000, "查询比赛信息失败"),
    CREATE_COMPETITION_SUCCESS(2011, "创建比赛信息成功"),
    CREATE_COMPETITION_FAIL(2010, "创建比赛信息失败"),
    DELETE_COMPETITION_SUCCESS(2021, "删除比赛信息成功"),
    DELETE_COMPETITION_FAIL(2020, "删除比赛信息失败"),
    UPDATE_COMPETITION_SUCCESS(2031, "修改比赛信息成功"),
    UPDATE_COMPETITION_FAIL(2030, "修改比赛信息失败"),
    THING_NOT_EXIST(2040, "该帖子不存在"),

    /**
     * 资源帖子相关
     * */
    RESOURCE_DATA_QUERY_SUCCESS(2001, "查询资源信息成功"),
    RESOURCE_DATA_QUERY_FAIL(2000, "查询资源信息失败"),
    CREATE_RESOURCE_SUCCESS(2011, "创建资源信息成功"),
    CREATE_RESOURCE_FAIL(2010, "创建资源信息失败"),
    DELETE_RESOURCE_SUCCESS(2021, "删除资源信息成功"),
    DELETE_RESOURCE_FAIL(2020, "删除资源信息失败"),
    UPDATE_RESOURCE_SUCCESS(2031, "修改资源信息成功"),
    UPDATE_RESOURCE_FAIL(2030, "修改资源信息失败"),

    /**
     * 评论相关
     * */
    COMMENT_ON_SUCCESS(4001, "评论成功"),
    COMMENT_ON_FAIL(4000, "评论失败"),
    COMMENT_NOT_EXIST(4010, "评论不存在"),
    COMMENT_DELETE_SUCCESS(4021, "删除评论成功"),
    COMMENT_DELETE_FAIL(4020, "删除评论失败"),

    /**
     * ChatGPT相关
     * */
    CONNECT_TIME_OUT(7000, "连接超时"),

    /**
     * 其他
     * */
    UNKNOWN_ERROR(9999, "未知错误，请刷新页面后重试")
    ;

    private final int code;
    private final String message;

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
