package com.dls.web.service.impl;


import com.dls.web.bean.JwtUser;
import com.dls.web.bean.RoleUserDTO;
import com.dls.web.bean.User;
import com.dls.web.mapper.RoleUserMapper;
import com.dls.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("JWTUserDetailsServiceImpl")
// 事务
public class JWTUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.selectUserByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        } else {
            List<RoleUserDTO> roleUsers = roleUserMapper.selectRoleByUser(user.getId());
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (int i = 0; i < roleUsers.size(); i++) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + roleUsers.get(i).getRole().getRoleName()));
            }
            return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), authorities);
        }
    }
}
