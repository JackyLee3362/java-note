package com.remote.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author jackylee
 * @date 2026-01-15 13:41
 */
@Data
@ConfigurationProperties("config-center.config")
public class ConfigCenterConfig {
    
    /**
     * 命名空间
     */
    private String namespace;
    
    /**
     * 分组
     */
    private String group;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 端口
     */
    private Integer port;
}
