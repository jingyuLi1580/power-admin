package com.example.powerAdmin.mapper;

import com.example.powerAdmin.entity.content.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljy
 * @since 2024-08-26
 */
@MapperScan
public interface ContentMapper extends BaseMapper<Content> {

}
