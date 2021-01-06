package com.dls.web.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dls.commom.dto.UserDTO;
import com.dls.commom.service.UserCenterService;
import com.dls.web.util.RedisUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
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
    private UserCenterService userCenterService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDTO user = userCenterService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        List<String> permissions = userCenterService.findPermissions(user.getId());
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new User(username, user.getPassword(), grantedAuthorities);
    }

    public void deleteUserLoginInfo(String username) {

    }

    public String saveUserLoginInfo(UserDetails user) throws UnsupportedEncodingException {
        String salt = BCrypt.gensalt();  //实时生成加密的salt
        String username = user.getUsername();
        // 将salt保存到缓存中
        redisUtil.set("token:" + username, salt, 3600);
        Algorithm algorithm = Algorithm.HMAC256(salt);
        Date date = new Date(System.currentTimeMillis() + 3600 * 1000);  //设置1小时后过期
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }
}
