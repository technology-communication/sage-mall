package com.dls.web.service;

import com.dls.web.bean.pojo.Product;
import com.dls.web.mapper.ProductMapper;
import com.google.common.collect.Lists;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 张金行
 * @Title: ProductService
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/19 23:38
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;
    @Autowired
    private ProductMapper productMapper;

    private Pageable pageable = PageRequest.of(0, 10);

    @Override
    public void createIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Product.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Product.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(index);
    }

    @Override
    public Page<Product> findByProductName(String productName) {
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", productName)
                .fuzziness(Fuzziness.TWO)
                .prefixLength(0)
                .maxExpansions(10);
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("name");
//        highlightBuilder.preTags("preTag");
//        highlightBuilder.postTags("postTag");

        return new PageImpl<>(Lists.newArrayList(productMapper.search(fuzzyQueryBuilder, pageable)));
    }

    @Override
    public Product save(Product product) {
        return productMapper.save(product);
    }

    @Override
    public Page<Product> findByProductPrice(Double price) {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("name", "手机"));
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        // 搜索，获取结果
        return productMapper.search(queryBuilder.build());
    }
}
