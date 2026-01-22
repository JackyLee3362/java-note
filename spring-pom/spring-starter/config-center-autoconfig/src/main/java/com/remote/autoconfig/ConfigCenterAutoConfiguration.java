package com.remote.autoconfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jackylee
 * @date 2026-01-15 13:55
 */
@Configuration
@EnableConfigurationProperties({ ConfigCenterConfig.class, })
public class ConfigCenterAutoConfiguration {
    @Bean
    ConfigCenterService configCenterService(ConfigCenterConfig configCenterConfig) {
        return new ConfigCenterService(configCenterConfig);
    }
}
