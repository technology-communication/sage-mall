package com.dls.web.mapper;

import com.dls.web.bean.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author 张金行
 * @Title: ProductMapper
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/19 23:15
 */
@Component
public interface ProductMapper extends ElasticsearchRepository<Product, Long> {

    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"productName.keyword\" : \"?\"}}}}")
    Page<Product> findByProductName(String productName, Pageable pageable);
}
