package com.uchain.cip.tools;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "举报表单类", description = "保存了与举报相关的信息")
public class ReportForm {
    @ApiModelProperty(value = "发起举报的用户Id")
    private long userId;
    @ApiModelProperty(value = "被举报的帖子的类型，1：资源帖 / 2：比赛帖")
    private int thingType;
    @ApiModelProperty(value = "被举报的帖子的Id")
    private long thingId;
    @ApiModelProperty(value = "举报的原因描述")
    private String description;
}
