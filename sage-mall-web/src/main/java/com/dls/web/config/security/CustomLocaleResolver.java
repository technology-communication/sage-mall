package com.dls.web.config.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class CustomLocaleResolver implements LocaleResolver {
    private ThreadLocal<Locale> threadLocal = new ThreadLocal<>();

    public CustomLocaleResolver() {
        // 用户可在前端选择设置语言，开发环境下设置默认语言环境为美国，方便测试资源国际化
        threadLocal.set(Locale.US);
    }

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        return threadLocal.get();
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
    }

    public Locale getLocale(){
        return threadLocal.get();
    }
}
