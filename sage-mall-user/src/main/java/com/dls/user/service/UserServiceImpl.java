package com.dls.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dls.commom.dto.UserDTO;
import com.dls.commom.service.UserService;
import com.dls.user.entity.User;
import com.dls.user.mapper.UserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Set;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/28 5:42 下午
 * @desc
 */

@Service(timeout = 50000)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return Collections.EMPTY_SET;
    }
}
