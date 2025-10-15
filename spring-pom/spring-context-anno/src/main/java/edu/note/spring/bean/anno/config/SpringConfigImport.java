package edu.note.spring.bean.anno.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import edu.note.spring.bean.anno.importer.JdbcConfig;

/**
 * @author jackylee
 * @date 2025-10-05 15:25
 */
@Configuration
@Import({ JdbcConfig.class })
public class SpringConfigImport {

}
