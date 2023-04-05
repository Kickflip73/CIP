package com.uchain.cip.tools;

import lombok.Data;

/**
 * 登录表单
 * */

@Data
public class LoginForm {
    private String nickNameOrEmail;
    private String password;
}
