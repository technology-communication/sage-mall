package com.dls.web.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public final class CommonResult<T> implements Serializable {

    public static Integer CODE_SUCCESS = 0;
    public static Integer CODE_FAIL = 1;

    /**
     * 是否显示提示消息
     */
    private Boolean isShow;

    /**
     * 相应的类型(警告，正常，成功，失败)success/error/warning/info
     */
    private String showType;

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 消息码
     */
    private String msgCode;
    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 因为 A 方法返回的 CommonResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T>    返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getMsgCode(), result.getMessage());
    }

    public static <T> CommonResult<T> error(String msgCode, String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CODE_FAIL;
        result.msgCode = msgCode;
        result.message = message;
        result.isShow = true;
        result.showType = "error";
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CODE_SUCCESS;
        result.showType = "success";
        result.isShow = false;
        result.data = data;
        result.message = "成功";
        return result;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return CODE_SUCCESS.equals(code);
    }

    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

}
