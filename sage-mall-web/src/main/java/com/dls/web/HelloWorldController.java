package com.dls.web;

import com.dls.web.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张金行
 * @Title: HelloWorldController
 * @ProjectName sage-mall
 * @Description:
 * @date 2019/10/14 20:44
 */
@RestController
@RefreshScope
public class HelloWorldController {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${ping}")
    private String ping;

    @GetMapping("/ping")
    public String loginPage() {
        return ping;
    }
}


