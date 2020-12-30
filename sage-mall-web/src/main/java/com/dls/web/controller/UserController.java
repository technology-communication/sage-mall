package com.dls.web.controller;

import com.dls.commom.dto.UserDTO;
import com.dls.commom.service.UserCenterService;
import com.dls.web.vo.UserVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/29 9:45 上午
 * @desc
 */

@RestController
public class UserController {
    @Reference
    private UserCenterService userCenterService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Object> save(@RequestBody @Validated UserVO userVO) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userVO,userDTO);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userCenterService.save(userDTO);
        return ResponseEntity.ok().build();
    }

}
