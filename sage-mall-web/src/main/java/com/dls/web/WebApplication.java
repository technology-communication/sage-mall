package com.dls.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 张金行
 * @Title: WebApplication
 * @ProjectName sage-mall
 * @Description:
 * @date 2019/10/14 20:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
