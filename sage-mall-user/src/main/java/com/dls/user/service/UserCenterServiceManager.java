package com.dls.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dls.commom.dto.UserDTO;
import com.dls.commom.service.UserCenterService;
import com.dls.user.entity.Role;
import com.dls.user.entity.User;
import com.dls.user.entity.UserRole;
import com.dls.user.pojo.UserRolePojo;
import com.google.common.collect.Sets;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/28 5:42 下午
 * @desc 服务聚合层
 */

@Service
public class UserCenterServiceManager implements UserCenterService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return userService.save(user);
    }

    @Override
    public UserDTO findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("username", username);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public List<String> findPermissions(Long userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("role_id").ge("user_id", userId);
        List<String> roleIds = userRoleService.listObjs(queryWrapper,String::valueOf);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.select("name").in("id",roleIds);
        List<String> roleNames = roleService.listObjs(wrapper,String::valueOf);
        return roleNames;
    }
}
