package com.dls.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/29 11:43 上午
 * @desc
 */
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserDetailsServiceImpl userDetailsService;

    public JsonLoginSuccessHandler(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //生成token，并把token加密相关信息缓存，具体请看实现类
        String token = userDetailsService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
        response.setHeader("Authorization", token);
    }

}
