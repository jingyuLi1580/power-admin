package com.example.powerAdmin.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "入参对象-查询用户")
public class SearchSysUserRequest {
    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id")
    private Integer userId;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;


}
