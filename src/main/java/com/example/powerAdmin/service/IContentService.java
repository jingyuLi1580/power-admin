package com.example.powerAdmin.service;

import com.example.powerAdmin.entity.content.Content;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.powerAdmin.entity.content.ContentDetail;
import com.example.powerAdmin.request.InsertContentParam;
import com.example.powerAdmin.request.SearchContentParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljy
 * @since 2024-08-26
 */
public interface IContentService extends IService<Content> {

    void insert(InsertContentParam insertContentParam);

    ContentDetail search(SearchContentParam searchContentParam);
}
