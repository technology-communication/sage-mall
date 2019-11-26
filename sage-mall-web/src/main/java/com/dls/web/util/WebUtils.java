package com.dls.web.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Slf4j
public class WebUtils {

    /**
     * XMLHttpRequest
     */
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    /**
     * 是否是Ajax请求
     *
     * @param request 请求
     * @return 是否是Ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        return requestedWith != null && XML_HTTP_REQUEST.equalsIgnoreCase(requestedWith);
    }

    /**
     * 是否是Ajax请求
     *
     * @param request 请求
     * @return 是否是Ajax请求
     */
    public static boolean isAjaxRequest(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        return header != null && XML_HTTP_REQUEST.equalsIgnoreCase(header);
    }


    /**
     * 输出JSON
     *
     * @param ret      返回信息
     * @param response 响应
     */
    public static void writeJson(String ret, ServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(ret);
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 获取客户端IP地址
     *
     * @param request 请求
     * @return IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * urlDecoder
     *
     * @param data 字符串
     * @return 解码后的字符串
     */
    public static String urlDecoder(String data) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(data)) {
            data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            data = data.replaceAll("\\+", "%2B");
            data = URLDecoder.decode(data, "UTF-8");
            return data;
        }
        return "";
    }

    /**
     * 获取请求体
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request) {
        int contentLength = request.getContentLength();
        /*当无请求参数时，request.getContentLength()返回-1 */
        if (contentLength == 0) {
            return null;
        }
        byte[] buffer = new byte[contentLength];
        try (ServletInputStream inputStream = request.getInputStream()) {
            for (int i = 0; i < contentLength; ) {
                int readLen = inputStream.read(buffer, i,
                        contentLength - i);
                if (readLen == -1) {
                    break;
                }
                i += readLen;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return buffer;
    }

    /**
     * 获取请求体
     *
     * @param request
     * @return
     */
    public static boolean isRequestBody(HttpServletRequest request) {
        int contentLength = request.getContentLength();
        /*当无请求参数时，request.getContentLength()返回-1 */
        if (contentLength == 0) {
            return false;
        }
        return true;
    }
}

