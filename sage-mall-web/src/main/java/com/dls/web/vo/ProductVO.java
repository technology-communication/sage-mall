package com.dls.web.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/25 11:41 上午
 * @desc
 */
@Getter
@Setter
public class ProductVO {
    private Long id;
    private String title;
    private BigDecimal price;
}
