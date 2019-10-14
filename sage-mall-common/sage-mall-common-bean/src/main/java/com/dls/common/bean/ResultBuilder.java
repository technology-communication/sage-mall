package com.dls.common.bean;

/**
 * @author 张金行
 * @Title: ResultBuilder
 * @ProjectName sage-mall
 * @Description:
 * @date 2019/10/14 21:02
 */
public class ResultBuilder<T> {
    public static <T> ReturnResult success(T data){
        ReturnResult<T> result = new ReturnResult<>();
        result.setCode(0);
        result.setData(data);
        result.setMsg("成功");
        return result;
    }

    public static ReturnResult success(){
        ReturnResult result = new ReturnResult<>();
        result.setCode(0);
        result.setMsg("成功");
        return result;
    }

    public static <T> ReturnResult fail(T data){
        ReturnResult<T> result = new ReturnResult<>();
        result.setCode(1);
        result.setData(data);
        result.setMsg("失败");
        return result;
    }

}
