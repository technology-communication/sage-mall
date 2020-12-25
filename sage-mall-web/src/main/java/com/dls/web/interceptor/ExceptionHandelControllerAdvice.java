package com.dls.web.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/7 11:38 上午
 * @desc 统一异常处理
 */
@Log4j2
@ControllerAdvice("com.dls.web.controller")
@ResponseBody
public class ExceptionHandelControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handlerRuntimeException(RuntimeException e) {
        log.error(e.getClass().getName() + ":", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerException(Exception e) {
        log.error(e.getClass().getName() + ":", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
