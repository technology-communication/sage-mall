package com.dls.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/29 11:20 上午
 * @desc
 */
public class TokenClearLogoutHandler implements LogoutHandler {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public TokenClearLogoutHandler(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsServiceImpl = userDetailsService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        clearToken(authentication);
    }

    protected void clearToken(Authentication authentication) {
        if(authentication == null)
            return;
        UserDetails user = (UserDetails)authentication.getPrincipal();
        if(user!=null && user.getUsername()!=null)
            userDetailsServiceImpl.deleteUserLoginInfo(user.getUsername());
    }

}
