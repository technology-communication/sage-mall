package com.dls.web.controller;


import com.dls.commom.dto.ProductDTO;
import com.dls.commom.service.ProductService;
import com.dls.web.vo.ProductVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/21 2:30 下午
 * @desc
 */
@Controller
public class ProductController {
    @Reference
    private ProductService productService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseEntity<Page<ProductDTO>> search(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        Page<ProductDTO> byProductName = productService.findByProductName(keyword);
        //redisUtil.set(keyword,product);
        return ResponseEntity.ok(byProductName);
    }

    @PostMapping("/product")
    @ResponseBody
    public ResponseEntity<Object> save(@RequestBody ProductVO product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product,productDTO);
        productService.save(productDTO);
        return ResponseEntity.ok().build();
    }
}
