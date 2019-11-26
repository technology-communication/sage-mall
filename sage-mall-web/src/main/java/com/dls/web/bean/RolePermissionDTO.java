package com.dls.web.bean;

import lombok.Data;

@Data
public class RolePermissionDTO {
    private Long id;
    private Role role;
    private Permission permission;
}
