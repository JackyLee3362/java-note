package edu.note.spring.bean.anno.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * properties 配合 @Value 注解使用
 * 
 * @author jackylee
 * @date 2025-10-05 14:54
 */

@Configuration
@ComponentScan("edu.note.spring.bean.anno")
// 单配置文件
@PropertySource("jdbc.properties")
// 多配置文件
// @PropertySource({"jdbc.properties","xxx.properties"})
// 通配符
// @PropertySource({"*.properties"})
// 类路径下的配置
// @PropertySource({"classpath:jdbc.properties"})
public class PropertiesConfig {

}
