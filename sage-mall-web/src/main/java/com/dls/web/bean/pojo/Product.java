package com.dls.web.bean.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "test", type = "product", shards = 1, replicas = 0)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Double)
    private Double price;
}
