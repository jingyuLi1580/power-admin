package com.example.powerAdmin.mapper;

import com.example.powerAdmin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.powerAdmin.request.SearchSysUserRequest;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ljy
 * @since 2023-03-27
 */
public interface UserMapper extends BaseMapper<User> {

    void inserta(SearchSysUserRequest searchSysUserRequest);
}
