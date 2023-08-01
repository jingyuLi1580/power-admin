package com.example.powerAdmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.powerAdmin.entity.User;
import com.example.powerAdmin.mapper.UserMapper;
import com.example.powerAdmin.request.ChannelUserRequest;
import com.example.powerAdmin.request.SearchSysUserRequest;
import com.example.powerAdmin.response.SearchUserVo;
import com.example.powerAdmin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.powerAdmin.utils.BeanUtil;
import com.example.powerAdmin.utils.JsonUtils;
import com.example.powerAdmin.utils.RedisCacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ljy
 * @since 2023-03-27
 */
@Service
//@CacheConfig(cacheNames = "users")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    Integer successNo = 0;

    Integer failNo = 0;

    @Resource
    private UserMapper userMapper;

    @Resource
    RedisCacheUtil redisCacheUtil;

    @Override
//    @Cacheable(key = "'user_cache_'+#p0")
    public List<SearchUserVo> search(SearchSysUserRequest searchSysUserRequest) {
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUserId, searchSysUserRequest.getUserId()));
        List<SearchUserVo> searchUserVos = BeanUtil.map2List(users, SearchUserVo.class);
//        redisCacheUtil.set("userList", JsonUtils.toJson(searchUserVos),60, TimeUnit.SECONDS);
        return searchUserVos;
    }

    @Override
    public void insert(SearchSysUserRequest searchSysUserRequest) {
        userMapper.inserta(searchSysUserRequest);
    }

    @Override
    public Map<String, Integer> insChannelUser(List<ChannelUserRequest> datas) {
        datas.forEach(item ->{
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(!Objects.isNull(item.getUserId()), User::getUserId, item.getUserId())
                    .eq(StringUtils.isNotEmpty(item.getUserName()), User::getUserName, item.getUserName())
                    .eq(StringUtils.isNotEmpty(item.getPassword()), User::getPassword, item.getPassword())
                    .eq(StringUtils.isNotEmpty(item.getEmail()), User::getEmail, item.getEmail())
                    .eq(StringUtils.isNotEmpty(item.getPhone()), User::getPhone, item.getPhone())
                    .like(StringUtils.isNotEmpty(item.getGender().toString()),User::getGender,item.getGender())
                    .between(Objects.isNull(item.getCreateTime()), User::getCreateTime, item.getCreateTime(), new Date()));
            if (Objects.isNull(user)) {
                BeanUtils.copyProperties(item,User.class);
                userMapper.insert(user);
                successNo++;
            } else {
                failNo++;
            }
        });
        Map<String, Integer> map = new HashMap<>();
        map.put("successNo",successNo);
        map.put("failNo",failNo);
        return map;
    }
}
