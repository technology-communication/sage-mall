package com.dls.product;

import com.dls.product.entity.mysql.Product;
import com.dls.product.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 张金行
 * @Title: Test
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/21 7:33
 */
@Slf4j
@SpringBootTest
public class ProductDocumentTest {
    @Autowired
    ProductMapper productMapper;

    private AtomicLong productId = new AtomicLong(4);

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    long id = productId.getAndIncrement();
                    Product product3 = new Product();
                    productMapper.insert(product3);
                    log.info("id------>" + id);
                }
            });
            thread.start();
            thread.join();
        }
    }
}
