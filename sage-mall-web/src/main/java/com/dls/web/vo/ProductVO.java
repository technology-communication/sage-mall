package com.dls.web.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "描述不能为空")
    private String description;
    private Long brandId;
    private Long categoryId;
    private String comment;
}

