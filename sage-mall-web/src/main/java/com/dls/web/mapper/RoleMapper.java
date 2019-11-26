package com.dls.web.mapper;

import com.dls.web.bean.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper  {
    Role selectRoleByName(String roleName);
}