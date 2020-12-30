package com.dls.commom.service;

import com.dls.commom.dto.UserDTO;

import java.util.List;
import java.util.Set;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/28 4:55 下午
 * @desc
 */
public interface UserCenterService {
    boolean save(UserDTO userDTO);

    UserDTO findByUsername(String username);

    List<String> findPermissions(Long userId);
}
