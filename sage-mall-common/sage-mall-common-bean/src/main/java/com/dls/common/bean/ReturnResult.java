package com.dls.common.bean;

import lombok.Data;

/**
 * @author 张金行
 * @Title: ReturnResult
 * @ProjectName sage-mall
 * @Description:
 * @date 2019/10/14 20:59
 */
@Data
public class ReturnResult<T> {
    private int code = 1;
    private String msg = "操作成功";
    private T data;
}
