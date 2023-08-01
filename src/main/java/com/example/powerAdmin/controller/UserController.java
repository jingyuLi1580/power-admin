package com.example.powerAdmin.controller;


import com.alibaba.excel.EasyExcel;
import com.example.powerAdmin.common.IdentityCommon;
import com.example.powerAdmin.common.RespCode;
import com.example.powerAdmin.common.ResultInfo;
import com.example.powerAdmin.listener.UserListener;
import com.example.powerAdmin.request.ChannelUserRequest;
import com.example.powerAdmin.request.InsUserRequest;
import com.example.powerAdmin.request.Promat;
import com.example.powerAdmin.request.SearchSysUserRequest;
import com.example.powerAdmin.response.SearchUserVo;
import com.example.powerAdmin.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ljy
 * @since 2023-03-27
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping("/powerAdmin/user")
public class UserController {

    @Autowired
    private IdentityCommon identityCommon;

    @Resource
    IUserService userService;

    @PostMapping("search")
    @ApiOperation(value="查询用户信息")
    public ResultInfo<List<SearchUserVo>> search(@RequestBody @Validated SearchSysUserRequest searchSysUserRequest) {
//        log.info("自动注入：", JSON.toJSONString(identityCommon.toString()));
        System.out.println(" identityCommon = " + identityCommon.getName());
        List<SearchUserVo> list = userService.search(searchSysUserRequest);
        return new ResultInfo<>(RespCode.SUCCESS,list);
    }


    @PostMapping("insert")
    @ApiOperation(value="查询用户信息")
    public ResultInfo insert(@RequestBody @Validated SearchSysUserRequest searchSysUserRequest) {
        userService.insert(searchSysUserRequest);
        return new ResultInfo<>(RespCode.SUCCESS);
    }

    @GetMapping("channelUser")
    @ApiOperation(value="导入用户信息")
    public ResultInfo insert(@RequestParam("file") MultipartFile file) {
        log.info("导入用户数据为：{}",file);
        UserListener userListener = new UserListener(userService);
        try {
            EasyExcel.read(file.getInputStream(), ChannelUserRequest.class,userListener).sheet().doRead();
            return new ResultInfo(RespCode.SUCCESS,userListener.result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultInfo<>(RespCode.SUCCESS);
    }
}

