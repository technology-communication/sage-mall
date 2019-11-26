package com.dls.web.bean;

import com.zlkj.pass.engine.config.exception.BusinessException;
import com.zlkj.pass.engine.config.exception.BusinessExceptionConst;
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
