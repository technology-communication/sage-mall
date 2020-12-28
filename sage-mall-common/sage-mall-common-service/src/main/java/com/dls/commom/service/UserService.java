package com.dls.commom.service;

import com.dls.commom.dto.UserDTO;

import java.util.Set;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/28 4:55 下午
 * @desc
 */
public interface UserService {
    UserDTO findByUsername(String username);

    Set<String> findPermissions(String username);
}
