package com.dls.web.config.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.dls.web.bean.CommonResult;
import com.dls.web.exception.BusinessExceptionConst;
import com.dls.web.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来解决匿名用户访问无权限资源时的异常
 */
@Slf4j
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("进入认证失败处理类");
        CommonResult<Object> ret = new CommonResult<>();
        // 认证失败
        if (InsufficientAuthenticationException.class.equals(e.getClass())) {
            log.error(e.getMessage());
            ret.setMessage(BusinessExceptionConst.E004);
        } else {
            log.error(e.getMessage());
            ret.setMsgCode(BusinessExceptionConst.RESULT_FLAG_SYSTEM_ERROR);
        }
        WebUtils.writeJson(JSONObject.toJSONString(ret), httpServletResponse);
    }
}
