package com.dls.product.entity.mysql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 张金行
 * @Title: Product
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/14 8:05
 */
//indexName：对应索引库名称
//type：对应在索引库中的类型
//shards：分片数量，默认5
//replicas：副本数量，默认1

//type：字段类型，是是枚举：FieldType
//index：是否索引，布尔类型，默认是true
//store：是否存储，布尔类型，默认是false
//analyzer：分词器名称
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("spu_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private BigDecimal price;
}
