package com.example.powerAdmin.request;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.powerAdmin.config.GenderConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ExcelProperty注解常用参数：
 * value=>导出字段的名称（Excel表的列头名称）
 * index=>导出的字段在Excel表格的顺序
 * converter=>导出的字段需要自定义映射的转换器
 *
 * @ExcelIgnore EasyExcel默认所有字段都会和excel去匹配，加了这个注解会忽略该字段
 * @DateTimeFormat 日期转换，用String去接收excel日期格式的数据会调用这个注解。里面的value参照java.text.SimpleDateFormat
 * @NumberFormat 数字转换，用String去接收excel数字格式的数据会调用这个注解。里面的value参照java.text.DecimalFormat
 */
@Data
public class ChannelUserRequest {

    /**
     * 主键id
     */
    @ExcelProperty(value = "id",index = 0)
    private Long id;

    /**
     * 用户Id
     */
    @ExcelProperty(value = "用户Id",index = 1)
    private Integer userId;

    /**
     * 用户名称
     */
    @ExcelProperty(value = "用户名称",index = 2)
    private String userName;

    /**
     * 登录密码
     */
    @ExcelProperty(value = "登录密码",index = 3)
    private String password;

    @ExcelProperty(value = "性别",index = 4,converter = GenderConverter.class)
    private Integer gender;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱",index = 5)
    private String email;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号",index = 6)
    private String phone;

    /**
     * 用户状态（0-未启用，1-启用）
     */
    @ExcelProperty(value = "用户状态（0-未启用，1-启用）",index = 7)
    private Long userStatus;

    /**
     * 创建日期
     */
    @ExcelProperty(value = "创建日期",index = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelIgnore
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ExcelIgnore
    private String creator;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedTime;

    /**
     * 修改者
     */
    @ExcelIgnore
    private String editor;

    /**
     * 是否删除（0-否，1-是）
     */
    @ExcelIgnore
    private Long deleted;
}
