package com.dls.web.config.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.dls.web.bean.CommonResult;
import com.dls.web.exception.BusinessExceptionConst;
import com.dls.web.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败操作
 */
@Component
@Slf4j
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("进入登录失败处理类");
        String msg = BusinessExceptionConst.E002;
        CommonResult<String> ret = new CommonResult<>();
        // 用户不存在
        if (UsernameNotFoundException.class.equals(e.getClass())) {
            ret.setMessage(BusinessExceptionConst.E003);
        //密码错误
        } else if (BadCredentialsException.class.equals(e.getClass())) {
            ret.setMessage(BusinessExceptionConst.E003);
        } else {
            ret.setMsgCode(BusinessExceptionConst.RESULT_FLAG_SYSTEM_ERROR);
            log.error("业务处理错误记述：" + msg, e);
        }
        WebUtils.writeJson(JSONObject.toJSONString(ret), httpServletResponse);
    }

}