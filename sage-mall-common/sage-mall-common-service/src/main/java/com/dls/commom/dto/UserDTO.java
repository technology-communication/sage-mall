package com.dls.commom.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/28 5:01 下午
 * @desc
 */

@Getter
@Setter
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String password;
}
