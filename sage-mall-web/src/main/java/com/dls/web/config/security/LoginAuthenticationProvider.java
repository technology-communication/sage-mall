package com.dls.web.config.security;

import com.dls.web.bean.JwtUser;
import com.dls.web.service.impl.JWTUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义登录校验器
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JWTUserDetailsServiceImpl userDetailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 获取封装用户信息的对象
        JwtUser jwtUser = (JwtUser) userDetailService.loadUserByUsername(username);
        // 校验密码
        boolean flag = bCryptPasswordEncoder.matches(password, jwtUser.getPassword());
        if (flag) {
            // 将密码隐藏，不放入token中
            jwtUser.setPassword("********");
            return new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
        }else {
            throw new BadCredentialsException(password);
        }
    }

    /**
     * 开启自定义支持
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
