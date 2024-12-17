package com.chain.cold.gateway.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author AprilGouzi
 * version 1.0
 */

@Configuration
public class CorsConfig {

    /**
     * 使用Filter过滤器配置
     * 有三种配置方式
     * 1. @CrossOrigin注解：方法上或类上
     * 2. 添加全局配置，需要一个配置类
     * 3. 使用过滤器配置
     * 都是构造CorsConfiguration
     *
     * @return
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许所有跨域方法
        config.addAllowedMethod("*");
        // 允许从任何域名发起请求
        config.addAllowedOrigin("*");
        // 允许的请求头
        config.addAllowedHeader("*");
        // 允许发送cookie的内容
        config.setAllowCredentials(true);
        // PathPatternParser路径解析器
        // UrlBasedCorsConfigurationSource 是 Spring 框架提供的一种基于 URL 的跨域资源共享（CORS）配置源，用于帮助配置跨域资源访问策略，保障 Web 应用程序的安全性
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        // 配置可以访问的地址
        source.registerCorsConfiguration("/**",config);

        // 就是过滤哪些地址
        return new CorsWebFilter(source);
    }
}
