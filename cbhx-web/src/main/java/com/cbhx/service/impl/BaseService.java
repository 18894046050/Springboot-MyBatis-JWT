package com.cbhx.service.impl;

import com.cbhx.mapper.AuthTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WangJiangQi
 * @since 2023-06-02
 */
public abstract class BaseService {

    public AuthTokenMapper authTokenMapper;

    @Autowired
    public void setAuthTokenMapper(AuthTokenMapper authTokenMapper) {
        this.authTokenMapper = authTokenMapper;
    }
}
