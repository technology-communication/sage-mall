package com.dls.commom.exception;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;

import java.util.Locale;

@Data
public class BusinessException extends RuntimeException {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -461244908043016951L;

    private int code;
    /**
     * 错误code
     */
    private String errorCode;
    /**
     * 输入参数验证错误信息
     */
    private String message;

    /**
     * 创建业务异常
     * @param msg 错误信息
     */
    public BusinessException(String msg) {
        super(msg);
    }

    /**
     * 创建业务异常
     *
     * @param errorCode 错误code
     * @param param     参数
     */
    public BusinessException(MessageSource messageSource, Locale locale, String errorCode, String... param) {
        super(getMessage(messageSource, locale, errorCode, param));
        this.errorCode = errorCode;
        this.message = super.getMessage();
    }

    public BusinessException(MessageSource messageSource, Locale locale, BusinessStatus businessStatus, String... param) {
        super(getMessage(messageSource, locale, businessStatus.getMsgCode(), param));
        this.errorCode = businessStatus.getMsgCode();
        this.code = businessStatus.getCode();
        this.message = super.getMessage();
    }
    /**
     * 依据异常类型取得异常消息内容
     *
     * @param errorCode 异常类型
     * @param param     参数
     */
    private static String getMessage(MessageSource messageSource, Locale locale, String errorCode, String... param) {
        String content = messageSource.getMessage(errorCode, param, locale);
        if (StringUtils.isNotEmpty(content)) {
            return content;
        } else {
            return "unknown accplition exception occourd.";
        }
    }
}
