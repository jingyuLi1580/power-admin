package com.example.powerAdmin.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.powerAdmin.common.IdentityCommon;
import com.example.powerAdmin.common.RespCode;
import com.example.powerAdmin.common.ResultInfo;
import com.example.powerAdmin.entity.EsUser;
import com.example.powerAdmin.entity.Permissions;
import com.example.powerAdmin.entity.User;
import com.example.powerAdmin.listener.ExcelListener;
import com.example.powerAdmin.listener.UserListener;
import com.example.powerAdmin.request.ChannelUserRequest;
import com.example.powerAdmin.request.InsUserRequest;
import com.example.powerAdmin.request.Promat;
import com.example.powerAdmin.request.SearchSysUserRequest;
import com.example.powerAdmin.response.SearchUserVo;
import com.example.powerAdmin.service.IUserService;
import com.example.powerAdmin.service.impl.EsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private EsUserService esUserService;

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
    public ResultInfo channelUser(@RequestParam("file") MultipartFile file) {
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

    @GetMapping("channelMoreSheetUser")
    @ApiOperation(value="导入多页用户信息")
    public ResultInfo channelMoreSheetUser(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        //excel监听器
        //监视器
        ExcelListener listener = new ExcelListener();
        ExcelReader excelReader = EasyExcel.read(inputStream, listener).build();
        List<Sheet> sheets = excelReader.getSheets();
        log.info("Excel中包含的页数为：{}",sheets.size());
        // 第一个sheet读取类型
        ReadSheet readSheet1 = EasyExcel.readSheet(0).head(ChannelUserRequest.class).build();
        // 开始读取第一个sheet - 这里调用invoke()方法
        excelReader.read(readSheet1);
        //excel sheet0 信息
        List<Object> list = listener.getDatas();
        log.info("第一页数据为：{}", JSON.toJSONString(list));
        //处理完第一个清除数据
        listener.getDatas().clear();

        // 第一个sheet读取类型
        ReadSheet readSheet2 = EasyExcel.readSheet(1).head(ChannelUserRequest.class).build();
        // 开始读取第一个sheet
        excelReader.read(readSheet2);
        //excel sheet0 信息
        List<Object> list2 = listener.getDatas();
        log.info("第二页数据为：{}", JSON.toJSONString(list2));
        return new ResultInfo<>(RespCode.SUCCESS);
    }

    @GetMapping("exportData")
    @ApiOperation(value="导出用户信息")
    public ResultInfo exportData(HttpServletResponse response){
        try {
            String fileName = "系统提示词埋点导出.xlsx";
            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
            List<User> list = userService.list(new LambdaQueryWrapper<User>()
                    .eq(User::getUserStatus, 1)
                    .eq(User::getDeleted, false));
            //这个实现方式非常简单直接，使用EasyExcel的write方法将查询到的数据进行处理，以流的形式写出即可
            EasyExcel.write(response.getOutputStream(), User.class)//对应的导出实体类
                    .sheet("提示词")//导出sheet页名称
                    .doWrite(list); //查询获取的数据集合List<T>，转成excel
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统提示词埋点导出异常，{}", e);
        }
        return new ResultInfo(RespCode.SUCCESS);
    }



    @PostMapping("/createUser")
    public Object createUser(@RequestBody EsUser esUser) {
        return esUserService.saveUser(esUser);
    }

    @GetMapping("/getAllUsers")
    public Iterable<EsUser> getAllUsers() {
        return esUserService.findAllUsers();
    }

    @GetMapping("/getUserById")
    public EsUser getUserById(@RequestParam String id) {
        return esUserService.findUserById(id);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam String id) {
        esUserService.deleteUser(id);
    }

//    @GetMapping("exportData")
//    @ApiOperation(value="导出批量用户信息")
//    public ResultInfo dataExport300w(HttpServletResponse response){
//        try {
////            String fileName = "系统提示词埋点导出.xlsx";
////            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
////            List<User> list = userService.list(new LambdaQueryWrapper<User>()
////                    .eq(User::getUserStatus, 1)
////                    .eq(User::getDeleted, false));
////            //这个实现方式非常简单直接，使用EasyExcel的write方法将查询到的数据进行处理，以流的形式写出即可
////            EasyExcel.write(response.getOutputStream(), User.class)//对应的导出实体类
////                    .sheet("提示词")//导出sheet页名称
////                    .doWrite(list); //查询获取的数据集合List<T>，转成excel
////            response.getOutputStream().close();
//            OutputStream outputStream = null;
//            try {
//                long startTime = System.currentTimeMillis();
//                System.out.println("导出开始时间 = " + startTime);
//                outputStream = response.getOutputStream();
//                ExcelWriter excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
//                String fileName = new String(("excel100w").getBytes(), "UTF-8");
//                new Table();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("系统提示词埋点导出异常，{}", e);
//        }
//        return new ResultInfo(RespCode.SUCCESS);
//    }

}

