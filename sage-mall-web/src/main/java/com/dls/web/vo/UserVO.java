package com.dls.web.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/29 9:46 上午
 * @desc
 */

@Getter
@Setter
public class UserVO {
    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
