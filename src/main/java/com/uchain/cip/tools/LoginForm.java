package com.uchain.cip.tools;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录表单
 * */

@Data
@ApiModel(value = "登录表单类", description = "保存昵称或邮箱和密码")
public class LoginForm {
    @ApiModelProperty(value = "昵称或邮箱")
    private String nickNameOrEmail;
    @ApiModelProperty(value = "密码")
    private String password;
}
