package com.uchain.cip.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/*
* 收藏的实体类
* */
@Data
@ApiModel("收藏信息实体类")
public class Star {
    @ApiModelProperty("收藏id")
    private int id;
    @ApiModelProperty("用户id")
    private int user_id;
    @ApiModelProperty("帖子id")
    private  int thing_id;
    @ApiModelProperty("收藏时间")
    private int star_date_time;
    @ApiModelProperty("帖子类型id")
    private int thing_type;


}
