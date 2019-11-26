package com.dls.web;

import com.dls.web.bean.PingParam;
import com.dls.web.bean.PongVO;
import com.dls.web.bean.User;
import com.dls.web.exception.BusinessException;
import com.dls.web.exception.BusinessExceptionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class PingController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("home")
    @PreAuthorize("hasAnyRole('guest')")
    public String home() {
/*        SecurityContextHolderStrategy contextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
        SecurityContext context = contextHolderStrategy.getContext();
        System.out.println(context);*/
        return "pong";
    }

    @GetMapping("ping")
    @PreAuthorize("hasPermission('/ping','ping')")
    public PongVO ping(@Validated @RequestBody PingParam ping, BindingResult result) {
        if (result.hasErrors()) {
            throw new BusinessException(messageSource, Locale.US, BusinessExceptionConst.INVALID_REQUEST_EXCEPTION, result.getAllErrors().get(0).getDefaultMessage());
        }
        final String zh = messageSource.getMessage("ping", null, Locale.CHINA);
        final String en = messageSource.getMessage("ping", null, Locale.US);
        final PongVO pongVO = new PongVO();
        pongVO.setEn(en);
        pongVO.setZh(zh);
        return pongVO;
    }

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return user;
    }


}
