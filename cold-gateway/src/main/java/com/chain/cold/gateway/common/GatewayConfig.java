package com.chain.cold.gateway.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AprilGouzi
 * version 1.0
 */
@Configuration
public class GatewayConfig {

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
