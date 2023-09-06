package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.AuthToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author WangJiangQi
 * @since 2023-06-02
 */
@Mapper
public interface AuthTokenMapper extends BaseMapper<AuthToken> {
    AuthToken selectAuthTokenByUsername(@Param("username") String username);
    int addAuthToken(@Param("authToken") AuthToken authToken);

    int updateAuthTokenByUserId(@Param("username") String username, @Param("token") String token);

    int deleteAuthTokenByUserId(@Param("username") String username);

}
