package com.dls.web.controller;


import com.dls.commom.dto.ProductDTO;
import com.dls.commom.service.ProductService;
import com.dls.web.vo.ProductVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @PreAuthorize("hasRole('ROLE_admin')")
    @ResponseBody
    public ResponseEntity<Object> save(@RequestBody @Validated ProductVO product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productService.save(productDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ResponseBody
    public ResponseEntity<Object> DeleteProduct(@RequestParam Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ResponseBody
    public ResponseEntity<Object> UpdateProduct(@RequestBody @Validated ProductVO product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productService.updateProduct(productDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ResponseBody
    public ResponseEntity<List<ProductDTO>> SelectProductByTitle(@RequestParam("title") String productTitle) {
        List<ProductDTO> list = productService.getProductByTitle(productTitle);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/productMessage")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ResponseBody
    public ResponseEntity<Object> SelectProductById(@RequestParam("id") Long productId) {
        ProductDTO product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/productInsert")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ResponseBody
    public ResponseEntity<Object> insertProduct(@RequestBody @Validated ProductDTO product) {
        productService.addProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/productAll")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ResponseBody
    public ResponseEntity<List<ProductDTO>> SelectAllProduct() {
        List<ProductDTO> list = productService.findAllProduct();
        return ResponseEntity.ok(list);
    }


}
