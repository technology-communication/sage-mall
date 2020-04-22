package com.dls.product;

import com.dls.product.entity.Product;
import com.dls.product.mapper.ProductMapper;
import com.sun.glass.ui.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 张金行
 * @Title: Test
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/21 7:33
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductTest {
    @Autowired
    ProductMapper productMapper;
    private AtomicLong productId = new AtomicLong(4);

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    long id = productId.getAndIncrement();
                    Product product3 = new Product(id, "product3", 3.3);
                    productMapper.insert(product3);
                    log.info("id------>" + id);
                }
            });
            thread.start();
            thread.join();
        }
    }
}
