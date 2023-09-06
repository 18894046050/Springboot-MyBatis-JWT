package com.demo.utils;

import com.demo.exception.BaseException;
import com.demo.wrapper.ApiResponse;
import com.demo.wrapper.IStatus;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * response通用工具类
 *
 * @author WangJiangQi
 * @since 2023-02-06
 */
@Slf4j
public class ResponseUtil {


    /**
     * 往 response 写出 json
     *
     * @param response 响应
     * @param status   状态
     * @param data     返回数据
     */
    public static void renderJson(HttpServletResponse response, IStatus status, Object data) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);

            response.getWriter().write(JsonUtil.toJsonString(ApiResponse.ofStatus(status, data)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }

    /**
     * 往 response 写出 json
     *
     * @param response  响应
     * @param exception 异常
     */
    public static void renderJson(HttpServletResponse response, BaseException exception) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);
            response.getWriter().write(JsonUtil.toJsonString(ApiResponse.ofException(exception)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }
}