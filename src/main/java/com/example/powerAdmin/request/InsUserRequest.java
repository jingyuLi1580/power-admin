package com.example.powerAdmin.request;

import com.example.powerAdmin.annotations.EmojProcess;
import com.example.powerAdmin.entity.Roles;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsUserRequest {

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id")
    private Integer userId;

    /**
     * 用户名称
     */
    @EmojProcess(value = "repalce")
    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;

    private Roles roles;
}
