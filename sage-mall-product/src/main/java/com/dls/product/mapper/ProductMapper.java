package com.dls.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dls.commom.dto.ProductDTO;
import com.dls.product.entity.mysql.Product;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductMapper extends BaseMapper<Product> {

    void delete(Long productId);
    int update(ProductDTO product);
    List<Product> getByTitle(String productTitle);
    Product getById(Long productId);
    int add(ProductDTO product);

    List<Product> findAll();
}
