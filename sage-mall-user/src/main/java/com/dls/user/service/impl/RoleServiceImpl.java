package com.dls.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dls.user.entity.Role;
import com.dls.user.mapper.RoleMapper;
import com.dls.user.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/29 6:19 下午
 * @desc
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
