package edu.note.spring.bean.anno.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author jackylee
 * @date 2025-10-05 14:54
 */

@Configuration
@ComponentScan("edu.note.spring.bean.anno")
@PropertySource("jdbc.properties") // 配合 @Value("${name}") 使用
// @PropertySource({"jdbc.properties","xxx.properties"})
// @PropertySource({"*.properties"})
// @PropertySource({"classpath:jdbc.properties"})
public class SpringConfigPropertySource {
    
}
