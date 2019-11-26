package com.dls.web.mapper;

import com.dls.web.bean.RolePermissionDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper {
    List<RolePermissionDTO> selectPermissionByRole(Long roleId);
}