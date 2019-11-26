package com.dls.web.bean;

import lombok.Data;

@Data
public class RoleUserDTO {
    private Long id;
    private Role role;
    private User user;
}
