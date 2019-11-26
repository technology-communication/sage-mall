package com.dls.web.config.security.handler;


import com.alibaba.fastjson.JSONObject;
import com.dls.web.exception.BusinessExceptionConst;
import com.dls.web.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限校验处理器
 * @author K. L. Mao
 * @create 2019/1/11
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
/*        CommonResult<Object> ret = new CommonResult<>();
        ret.setMessage(BusinessExceptionConst.E005);
        WebUtils.writeJson(JSONObject.toJSONString(ret), response);*/
    }
}