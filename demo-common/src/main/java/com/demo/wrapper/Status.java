package com.demo.wrapper;

import lombok.Getter;

/**
 * 通用状态码
 *
 * @author WangJiangQi
 * @since 2023-02-06
 */
@Getter
public enum Status implements IStatus {
    /**
     * 操作成功！
     */
    SUCCESS(0, "操作成功！"),

    /**
     * 操作异常！
     */
    ERROR(500, "操作异常！"),

    /**
     * 退出成功！
     */
    LOGOUT(200, "退出成功！"),

    /**
     * 请先登录！
     */
    UNAUTHORIZED(401, "请先登录！"),

    /**
     * 暂无权限访问！
     */
    ACCESS_DENIED(403, "权限不足！"),

    /**
     * 请求不存在！
     */
    REQUEST_NOT_FOUND(404, "请求不存在！"),

    /**
     * 请求方式不支持！
     */
    HTTP_BAD_METHOD(405, "请求方式不支持！"),

    /**
     * 请求异常！
     */
    BAD_REQUEST(400, "请求异常！"),

    /**
     * 参数不匹配！
     */
    PARAM_NOT_MATCH(400, "参数不匹配！"),

    /**
     * 参数不能为空！
     */
    PARAM_NOT_NULL(400, "参数不能为空！"),

    /**
     * 当前用户已被锁定，请联系管理员解锁！
     */
    USER_DISABLED(403, "当前用户已被锁定，请联系管理员解锁！"),

    /**
     * 用户权限不足,需要ADMIN权限
     */
    USER_ADMIN_PERMISSION(500, "用户权限不足,需要ADMIN权限"),

    USER_OPERATE_PERMISSION(500, "用户操作权限不足"),

    USER_PERMISSION(500, "用户权限不足"),

    USERNAME_EXIST_ERROR(5000, "用户名已存在，请重新输入！"),

    ROLE_EXIST_ERROR(5000, "角色名已存在，请重新输入！"),

    UPLOAD_FILE(5012, "上传文件失败"),
    UPDATE_FILE(5012, "更新文件失败"),
    UN_FOUND_FILE(5012, "无法找到license文件"),

    NOT_EXCEL(5014,"文件不是Excel文件"),
    /**
     * 用户名或密码错误！
     */
    USERNAME_PASSWORD_ERROR(5001, "用户名或密码错误，请重新输入！"),

    USERNAME_ENABLED_ERROR(5001, "用户被禁用，请联系管理员！"),

    /**
     * token 已过期，请重新登录！
     */
    TOKEN_EXPIRED(5002, "token 已过期，请重新登录！"),

    TOKEN_LOGIN(5002, "用户已经在其他地方登录，请重新登录！"),

    /**
     * token 解析失败，请尝试重新登录！
     */
    TOKEN_PARSE_ERROR(5002, "token 解析失败，请尝试重新登录！"),

    MYBATIS_ERROR(5003, "执行SQL错误"),
    SERVER_DATASOURCE_ERROR(5004, "创建KafkaCilent错误"),
    CALENDAR_NAME_ALREADY_EXISTS(10148,  "日历名称已存在"),
    CALENDAR_DATE_NOT_VALID(10155,  "日历日期不是有效日期"),
    CALENDAR_DATES_HAS_CREATED(10157, "创建日历日期错误：已创建日历日期"),
    FILE_LOAD_ERROR(10161,  "文件加载错误"),

    CALENDAR_INFO_NOT_EXIST(10150, "日历信息不存在"),
    CALENDAR_DATES_HAS_NOT_CREATED(10158,  "创建日历日期错误：尚未创建日历日期"),

    UPDATE_CALENDAR_DATES_ERROR_FOR_DELETE(10160,  "更新日历日期错误：删除批次日期"),
    UPDATE_CALENDAR_DATES_ERROR(10159, "更新日历日期错误"),
    DELETE_CALENDER_INFO_ERROR(10152, "删除日历信息错误"),
    CREATE_CALENDAR_DATES_ERROR(10154, "创建日历日期错误"),

    EXEC_SQL_ERROR(100015, "执行sql错误"),
    CALENDAR_INFO_EXIST(10163,  "日历信息已存在"),

    PARSE_TO_CRON_EXPRESSION_ERROR(10140,  "解析调度表达式错误"),

    ALERT_GROUP_EXIST(10012, "告警组名称已存在"),


    CALENDER_INFO_NAME_NOT_BLANK(10151, "日历信息名称不能为空");


    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Status fromCode(Integer code) {
        Status[] statuses = Status.values();
        for (Status status : statuses) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return SUCCESS;
    }

    @Override
    public String toString() {
        return String.format(" Status:{code=%s, message=%s} ", getCode(), getMessage());
    }

}
