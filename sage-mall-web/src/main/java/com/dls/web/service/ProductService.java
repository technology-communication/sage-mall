package com.dls.web.service;

import com.dls.web.bean.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    void createIndex();
    void deleteIndex(String index);
    Page<Product> findByProductName(String productName);
    Product save(Product product);
    Page<Product> findByProductPrice(Double price);
}
