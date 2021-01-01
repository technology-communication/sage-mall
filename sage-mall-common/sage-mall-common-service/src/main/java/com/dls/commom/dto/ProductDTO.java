package com.dls.commom.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/25 11:17 上午
 * @desc
 */
@Getter
@Setter
public class ProductDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Long brandId;
    private Long categoryId;
    private String comment;
}
