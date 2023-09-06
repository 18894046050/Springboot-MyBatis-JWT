package com.demo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangJiangQi
 * @since 2023-03-06
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 新增分页拦截器，并设置数据库类型为mysql
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
