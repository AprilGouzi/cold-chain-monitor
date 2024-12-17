package com.chain.cold.admin.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AprilGouzi
 * version 1.0
 * mybatis-plus配置
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     * 配置分页拦截器
     * 使用Page < T > 类，构造分页参数的方法
     * 通过Page类传递分页参数，插件会自动处理分页SQL
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return new PaginationInterceptor();
    }
}
