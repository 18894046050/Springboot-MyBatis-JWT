package com.demo.service.impl;

import com.demo.mapper.AuthTokenMapper;
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
