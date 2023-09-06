package com.demo.wrapper;

/**
 * api错误码接口
 *
 * @author WangJiangQi
 * @since 2023-02-06
 */
public interface IStatus {

    /**
     * 状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 返回信息
     *
     * @return 返回信息
     */
    String getMessage();

}