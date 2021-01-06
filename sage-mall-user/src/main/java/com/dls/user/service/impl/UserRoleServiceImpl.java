package com.dls.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dls.user.entity.UserRole;
import com.dls.user.mapper.UserRoleMapper;
import com.dls.user.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/29 10:00 下午
 * @desc
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
