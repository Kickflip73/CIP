package com.uchain.cip.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回视图
 * */
@ApiModel(value = "返回视图对象类", description = "给前端的响应类")
public class ResultVO {
    @ApiModelProperty(value = "响应码", example = "1001")
    private int code;
    @ApiModelProperty(value = "响应信息", example = "成功")
    private String message;
    @ApiModelProperty(value = "响应数据", example = "对象数据")
    private Object data;

    public ResultVO() {
    }

    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
