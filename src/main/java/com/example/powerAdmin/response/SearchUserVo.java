package com.example.powerAdmin.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "反参-查询用户")
@Data
public class SearchUserVo {
    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id")
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;
}
