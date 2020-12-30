package com.dls.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dls.user.entity.User;
import com.dls.user.mapper.UserMapper;
import com.dls.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/29 6:20 下午
 * @desc
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
