package com.remote.autoconfig;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2026-01-15 13:46
 */
@Slf4j
public class ConfigCenterService {

    private final ConfigCenterConfig configCenterConfig;

    public ConfigCenterService(ConfigCenterConfig configCenterConfig) {
        this.configCenterConfig = configCenterConfig;
        log.info("连接到配置中心, config:{}...", this.configCenterConfig);
    }

}