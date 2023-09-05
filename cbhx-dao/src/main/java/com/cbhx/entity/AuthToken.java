package com.cbhx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WangJiangQi
 * @since 2023-02-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("dq_auth_token")
public class AuthToken extends BaseEntity {

    private String username;

    private String token;

}
