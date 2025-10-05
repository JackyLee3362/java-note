package edu.note.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author jackylee
 * @date 2025-10-05 15:58
 */
@Configuration
@ComponentScan("edu.note.spring.aop")
@EnableAspectJAutoProxy
public class SpringConfig {

}
