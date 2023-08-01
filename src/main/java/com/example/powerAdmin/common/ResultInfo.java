package com.example.powerAdmin.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.example.web.common
 * @ClassName: ResultInfo
 * @Author: admin
 * @CreateTime: 2020/12/22 18:26
 * @Description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultInfo<T> implements Serializable {
    //状态码
    private Integer code;

    //提示信息
    private String message;

    //数据对象
    private T result;

    public ResultInfo(RespCode respCode,T result) {
        this.code = respCode.getCode();
        this.message = respCode.getMessage();
        this.result = result;
    }

    public ResultInfo(RespCode respCode) {
        this.code = respCode.getCode();
        this.message = respCode.getMessage();
    }
}
