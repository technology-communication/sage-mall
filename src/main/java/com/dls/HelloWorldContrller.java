package com.dls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张金行
 * @version 1.0
 * @Title: HelloWorldContrller
 * @ProjectName sage-mall
 * @Description:
 * @date 2019/10/8 00080:50
 */
@RestController
// autorefresh of configuration updates
@RefreshScope
public class HelloWorldContrller {
    @Value("${hello}")
    private String str;

    @RequestMapping("/get")
    public String get() {
        return str;
    }
}
