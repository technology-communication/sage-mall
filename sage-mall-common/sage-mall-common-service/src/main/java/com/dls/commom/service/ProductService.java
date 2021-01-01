package com.dls.commom.service;

import com.dls.commom.dto.ProductDTO;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ProductService{
    void createIndex();
    void deleteIndex(String index);
    Page<ProductDTO> findByProductName(String productName);
    boolean save(ProductDTO product);
    Page<ProductDTO> findByProductPrice(Double price);
    void deleteProduct(Long productId);
    int updateProduct(ProductDTO product);
    List<ProductDTO> getProductByTitle(String productTitle);
    ProductDTO getProductById(Long productId);
    int addProduct(ProductDTO product);

    List<ProductDTO> findAllProduct();
}
