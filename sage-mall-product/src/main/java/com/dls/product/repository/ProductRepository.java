package com.dls.product.repository;

import com.dls.product.entity.es.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author 张金行
 * @Title: ProductMapper
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/19 23:15
 */
@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductDocument, Long> {

    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"productName.keyword\" : \"?\"}}}}")
    Page<ProductDocument> findByProductName(String productName, Pageable pageable);
}
