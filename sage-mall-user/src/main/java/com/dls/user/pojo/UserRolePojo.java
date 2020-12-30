package com.dls.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dls.user.entity.Role;
import com.dls.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/29 9:26 下午
 * @desc
 */
@Getter
@Setter
@TableName("user_role")
public class UserRolePojo implements Serializable {
    private Long id;
    private String roleName;
}
