package com.dls.commom.service;


import com.dls.commom.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    void createIndex();
    void deleteIndex(String index);
    Page<ProductDTO> findByProductName(String productName);
    boolean save(ProductDTO product);
    Page<ProductDTO> findByProductPrice(Double price);
}
