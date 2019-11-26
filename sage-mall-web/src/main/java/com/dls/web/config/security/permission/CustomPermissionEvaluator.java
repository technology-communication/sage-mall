package com.dls.web.config.security.permission;

import com.dls.web.bean.JwtUser;
import com.dls.web.bean.Role;
import com.dls.web.bean.RolePermissionDTO;
import com.dls.web.mapper.RoleMapper;
import com.dls.web.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 自定义权限过滤器
 */
@Configuration
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 通过请求过来的url和数据库中的角色对应url来对应校验权限
     * @param authentication
     * @param targetUrl
     * @param targetPermission
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        JwtUser user = (JwtUser) authentication.getPrincipal();
        // 获得用户权限
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            // 去掉roleName中的“ROLE_”前缀
            String substring = roleName.substring(5);
            Role roleByName = roleMapper.selectRoleByName(substring);
            if (roleByName == null){
                return false;
            }
            // 得到角色所有的url权限
            List<RolePermissionDTO> rolePermissionDTOS = rolePermissionMapper.selectPermissionByRole(roleByName.getId());

            // 遍历permissionList
            for (RolePermissionDTO rolePermissionDTO : rolePermissionDTOS) {
                String url = rolePermissionDTO.getPermission().getUrl();
                String name = rolePermissionDTO.getPermission().getName();
                // 如果访问的Url和权限与权限用户符合的话，返回true
                if (targetUrl.equals(url) && targetPermission.equals(name)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object targetPermission) {
        return false;
    }

}
