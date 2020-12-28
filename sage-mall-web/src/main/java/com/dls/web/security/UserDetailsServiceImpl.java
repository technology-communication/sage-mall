package com.dls.web.security;

import com.dls.commom.dto.UserDTO;
import com.dls.commom.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/28 4:46 下午
 * @desc
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDTO user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissions = userService.findPermissions(username);
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new User(username, user.getPassword(), grantedAuthorities);
    }
}
