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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("spu")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private BigDecimal price;
}
