package com.dls.product;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张金行
 * @Title: ProductApplication
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/21 7:16
 */
@SpringBootApplication
@MapperScan("com.dls.product.mapper")
@EnableDubbo
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class);
    }
}
