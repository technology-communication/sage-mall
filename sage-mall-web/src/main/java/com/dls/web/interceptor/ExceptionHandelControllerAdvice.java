package com.dls.web.interceptor;

import com.dls.commom.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.omg.CORBA.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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

    public static final String SYSTEM_ERROR = "系统内部异常";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBusinessException(MethodArgumentNotValidException e) {
        // 校验错误
        List<FieldError> bindingResult = e.getBindingResult().getFieldErrors();
        log.info("MethodArgumentNotValidException:", e);
        HashMap<String, Object> map = buildResponseMap(bindingResult.get(0).getDefaultMessage());
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e) {
        log.info("AccessDeniedException:", e);
        HashMap<String, Object> map = buildResponseMap(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException e) {
        log.info("BusinessException:", e);
        HashMap<String, Object> map = buildResponseMap(e.getMessage());
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<Object> handleSystemException(SystemException e) {
        log.error("SystemException:", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getClass().getName() + ":", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.getClass().getName() + ":", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private HashMap<String, Object> buildResponseMap(String message) {
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("message", message);
        return map;
    }

}
