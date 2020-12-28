package com.dls.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dls.commom.dto.ProductDTO;
import com.dls.product.entity.es.ProductDocument;
import com.dls.product.entity.mysql.Product;
import com.dls.product.mapper.ProductMapper;
import com.dls.product.repository.ProductRepository;
import com.dls.commom.service.ProductService;
import org.apache.dubbo.config.annotation.Service;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 张金行
 * @Title: ProductService
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/19 23:38
 */

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;
    @Autowired
    private ProductRepository productRepository;

    private final Pageable pageable = PageRequest.of(0, 10);

    @Override
    public void createIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(ProductDocument.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(ProductDocument.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(index);
    }

    @Override
    public Page<ProductDTO> findByProductName(String productName) {
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", productName)
                .fuzziness(Fuzziness.TWO)
                .prefixLength(0)
                .maxExpansions(10);
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("name");
//        highlightBuilder.preTags("preTag");
//        highlightBuilder.postTags("postTag");
        Page<ProductDocument> search = productRepository.search(fuzzyQueryBuilder, pageable);
        List<ProductDTO> collect = search.stream().map(productDocument -> {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(productDocument, productDTO);
            return productDTO;
        }).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public boolean save(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO,product);
        return save(product);
    }

    @Override
    public Page<ProductDTO> findByProductPrice(Double price) {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("name", "手机"));
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        // 搜索，获取结果
        Page<ProductDocument> search = productRepository.search(queryBuilder.build());
        List<ProductDTO> collect = search.stream().map(productDocument -> {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(productDocument, productDTO);
            return productDTO;
        }).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}
