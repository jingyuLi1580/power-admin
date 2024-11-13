package com.example.powerAdmin.request;

import com.example.powerAdmin.entity.content.LevelLabel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InsertContentParam {
    private Long id;

    /**
     * 内容标题
     */
    private String contentTitle;

    /**
     * 内容标题高亮
     */
    private String contentTitleHighlight;

    /**
     * 内容副标题
     */
    private String contentSubTitle;

    /**
     * 内容封面URL
     */
    private String contentCover;

    /**
     * 内容详情URL
     */
    private String contentUrl;

    /**
     * 内容简介
     */
    private String contentAbstract;

    /**
     * 内容类型
     */
    private Integer contentType;

    /**
     * 内容关联分类
     */
    private List<Object> contentClasses;

    /**
     * 内容版本号
     */
    private Integer conetentVersion;

    /**
     * 内容关联产品
     */
    private List<Object> contentProducts;

    /**
     * 内容密级 0-对内，1-对外
     */
    private Integer securityLevel;

    /**
     * 内容关联标签
     */
    private List<Object> contentLabels;

    /**
     * 内容权限配置
     */
    private List<Object> authorityConfigs;

    /**
     * 内容附件
     */
    private List<Object> attachments;

    /**
     * 内容创建者
     */
    private String creator;

    /**
     * 内容修改者
     */
    private String modifier;

    /**
     * 内容创建时间
     */
    private LocalDateTime createTime;

    /**
     * 内容修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 内容发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 置顶时间戳(大于0表示置顶数据)
     */
    private Long topTimestamp;

    /**
     * 文章内容
     */
    private String contentData;

    /**
     * 语音链接
     */
    private String audioUrl;

    /**
     * 第三方文章链接
     */
    private String sourceDetailUrl;

    /**
     * 文章创建者头像url
     */
    private String creatorAvatar;

    /**
     * 文章创建者姓名
     */
    private String creatorName;

    /**
     * 外部内容源链接
     */
    private String sourceLink;

    /**
     * 数据类型 研报-200
     */
    private Integer refType;

    /**
     * 内容统一ID
     */
    private String contentUnifiedId;

    /**
     * 股票信息
     */
    private List<Object> stockInfos;

    /**
     * 内容详细来源
     */
    private String source;

    /**
     * 直播人员名单
     */
    private String liveUserName;

    /**
     * 是否对公众开放 0-否 1-是
     */
    private Integer isPublic;

    /**
     * 搜索匹配度
     */
    private Float score;

    /**
     * 火山标签列表
     */
    private List<LevelLabel> volcTagList;

    /**
     * 新闻唯一ID
     */
    private String uuid;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 渠道
     */
    private List<Object> channel;
}
