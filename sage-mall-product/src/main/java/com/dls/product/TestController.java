package com.dls.product;

import com.dls.product.entity.Product;
import com.dls.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 张金行
 * @Title: TestController
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/21 7:20
 */
@Controller
public class TestController {
    @Autowired
    ProductMapper productMapper;

    @RequestMapping("health")
    public ResponseEntity<Product> healthCheck(){
        Product health = productMapper.health();
        return ResponseEntity.ok(health);
    }

    @PostMapping("product")
    public ResponseEntity<Integer> insertCheck(@RequestBody Product product){
        return ResponseEntity.ok(productMapper.insert(product));
    }
}
