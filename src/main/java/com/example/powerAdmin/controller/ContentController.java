package com.example.powerAdmin.controller;


import com.example.powerAdmin.common.RespCode;
import com.example.powerAdmin.common.ResultInfo;
import com.example.powerAdmin.entity.content.ContentDetail;
import com.example.powerAdmin.request.InsertContentParam;
import com.example.powerAdmin.request.SearchContentParam;
import com.example.powerAdmin.request.SearchSysUserRequest;
import com.example.powerAdmin.response.SearchUserVo;
import com.example.powerAdmin.service.IContentService;
import com.example.powerAdmin.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljy
 * @since 2024-08-26
 */
@RestController
@RequestMapping("/powerAdmin/content")
public class ContentController {

    @Resource
    IContentService contentService;

    @PostMapping("insert")
    @ApiOperation(value="新增研报内容")
    public ResultInfo insert(@RequestBody InsertContentParam insertContentParam) {
        contentService.insert(insertContentParam);
        return new ResultInfo<>(RespCode.SUCCESS);
    }

    @PostMapping("search")
    @ApiOperation(value="查询研报内容")
    public ResultInfo<ContentDetail> search(@RequestBody SearchContentParam searchContentParam) {
        ContentDetail contentDetail = contentService.search(searchContentParam);
        return new ResultInfo<>(RespCode.SUCCESS,contentDetail);
    }
}

