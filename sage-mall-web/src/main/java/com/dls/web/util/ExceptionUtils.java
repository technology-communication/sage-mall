package com.dls.web.util;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 异常工具类
 *
 *  <strong>ExceptionUtil使用示例</strong><br>
 *  <code><pre>
 *  ・・・
 *  try {
 *     ・・・
 *  } catch (Exception e) {
 *      // 取得trace信息
 *      log.error("error-message", ExceptionUtil.getStackTrace(e));
 *  }
 *  ・・・
 */
@Slf4j
public final class ExceptionUtils {

    /**
     * 只是支持ServletException
     */
    private static final String SERVLET_EXCEPTION_NAME =
        "javax.servlet.ServletException";

    /**
     * ServletException发生的时候，抛出时的方法名
     */
    private static final String GET_ROOT_CAUSE = "getRootCause";

    /**
     * 指定异常的trace信息取得
     *
     * @param throwable 异常
     * @return trace信息
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String getStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (throwable != null) {
            baos.reset();
            throwable.printStackTrace(new PrintStream(baos));
            sb.append(baos.toString());

            Class throwableClass = throwable.getClass();

            if (SERVLET_EXCEPTION_NAME.equals(throwableClass.getName())) {
                try {
                    Method method = throwableClass.getMethod(GET_ROOT_CAUSE);
                    throwable = (Throwable) method.invoke(throwable);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    log.error(e.getMessage());
                    throwable = null;
                }
            } else {
                throwable = throwable.getCause();
            }
        }
        return sb.toString();
    }

}
