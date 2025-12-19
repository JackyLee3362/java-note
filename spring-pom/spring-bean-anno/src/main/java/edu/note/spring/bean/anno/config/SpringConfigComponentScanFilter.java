package edu.note.spring.bean.anno.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

/**
 * @author jackylee
 * @date 2025-10-21 22:59
 */

@Configuration
@ComponentScan(value = "edu.note.spring.bean.anno",
        /* 排除的类 */excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Service.class))
public class SpringConfigComponentScanFilter {

}
