package com.example.powerAdmin.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.powerAdmin.entity.content.Content;
import com.example.powerAdmin.entity.content.ContentDetail;
import com.example.powerAdmin.mapper.ContentMapper;
import com.example.powerAdmin.request.InsertContentParam;
import com.example.powerAdmin.request.SearchContentParam;
import com.example.powerAdmin.service.IContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljy
 * @since 2024-08-26
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

    @Resource
    ContentMapper contentMapper;

    @Override
    public void insert(InsertContentParam insertContentParam) {
        Content content = Content.builder()
                .contentTitle(insertContentParam.getContentTitle())
                .contentTitleHighlight(insertContentParam.getContentTitleHighlight())
                .contentSubTitle(insertContentParam.getContentSubTitle())
                .contentCover(insertContentParam.getContentCover())
                .contentUrl(insertContentParam.getContentUrl())
                .contentAbstract(insertContentParam.getContentAbstract())
                .contentType(insertContentParam.getContentType())
                .volcTagList(JSON.toJSONString(insertContentParam.getVolcTagList())).build();
        contentMapper.insert(content);
    }

    @Override
    public ContentDetail search(SearchContentParam searchContentParam) {
        Content content = contentMapper.selectOne(new LambdaQueryWrapper<Content>()
                .eq(!Objects.isNull(searchContentParam.getId()), Content::getId, searchContentParam.getId())
                .like(Content::getContentTitle, searchContentParam.getContentTitle()));
        ContentDetail contentDetail = new ContentDetail();
        return null;
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "            \"contentId\": 1773533,\n" +
                "            \"contentTitle\": \"券商应加快自身制度变革和业务创新\",\n" +
                "            \"contentTitleHighlight\": null,\n" +
                "            \"contentCover\": \"\",\n" +
                "            \"contentUrl\": \"https://contcenter-test.ciccwm.com/content-center/content-manage/detail/1773533\",\n" +
                "            \"contentAbstract\": \"券商应加快自身制度变革和业务创新\",\n" +
                "            \"contentType\": null,\n" +
                "            \"contentClasses\": [\n" +
                "                {\n" +
                "                    \"className\": \"资讯##同花顺##新闻##财经\",\n" +
                "                    \"classCode\": \"100##256##105##100##103\",\n" +
                "                    \"classAlias\": null,\n" +
                "                    \"classLevel\": 4,\n" +
                "                    \"classOrder\": 4,\n" +
                "                    \"subClassList\": null,\n" +
                "                    \"createTime\": null,\n" +
                "                    \"modifyTime\": null,\n" +
                "                    \"contentModifyTime\": null,\n" +
                "                    \"classId\": 1139,\n" +
                "                    \"hasPermission\": 0\n" +
                "                }\n" +
                "            ],\n" +
                "            \"authorityConfigs\": [\n" +
                "                {\n" +
                "                    \"authorityType\": \"READ\",\n" +
                "                    \"authorityName\": \"可查看\",\n" +
                "                    \"authorityRoles\": [\n" +
                "                        {\n" +
                "                            \"roleKey\": \"ContentAdmin\",\n" +
                "                            \"roleName\": \"内容管理员\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"roleKey\": \"AllInternalUser\",\n" +
                "                            \"roleName\": \"全体员工(含中后台)\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"authorityUsers\": [\n" +
                "                        \"18810465952(CICC)\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"authorityType\": \"ALL\",\n" +
                "                    \"authorityName\": \"所有权限\",\n" +
                "                    \"authorityRoles\": [\n" +
                "                        {\n" +
                "                            \"roleKey\": \"SystemAdmin\",\n" +
                "                            \"roleName\": \"系统管理员\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"authorityUsers\": [\n" +
                "                        \"18810465952(CICC)\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "}";
        Content content = JSON.parseObject(str, Content.class);
        ContentDetail contentDetail = JSON.parseObject(str, ContentDetail.class);
        System.out.println("content = " + content);
    }
}
