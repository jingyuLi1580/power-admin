package com.example.powerAdmin.service.impl;

import com.example.powerAdmin.entity.Permissions;
import com.example.powerAdmin.mapper.PermissionsMapper;
import com.example.powerAdmin.service.IPermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author ljy
 * @since 2023-03-27
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

}
