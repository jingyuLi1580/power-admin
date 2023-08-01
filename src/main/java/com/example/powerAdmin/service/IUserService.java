package com.example.powerAdmin.service;

import com.example.powerAdmin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.powerAdmin.mapper.UserMapper;
import com.example.powerAdmin.request.ChannelUserRequest;
import com.example.powerAdmin.request.SearchSysUserRequest;
import com.example.powerAdmin.response.SearchUserVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ljy
 * @since 2023-03-27
 */
public interface IUserService extends IService<User> {

    List<SearchUserVo> search(SearchSysUserRequest searchSysUserRequest);

    void insert(SearchSysUserRequest searchSysUserRequest);

    Map<String, Integer> insChannelUser(List<ChannelUserRequest> datas);
}
