package com.dls.web.mapper;


import com.dls.web.bean.RoleUserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserMapper  {
    List<RoleUserDTO> selectRoleByUser(Long userId);
}