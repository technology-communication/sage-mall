package com.dls.product.mapper;

import com.dls.product.entity.Product;

public interface ProductMapper {
    Product health();

    Integer insert(Product product);
}
