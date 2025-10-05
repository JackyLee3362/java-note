package edu.note.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import edu.note.spring.importer.JdbcConfig;

/**
 * @author jackylee
 * @date 2025-10-05 15:25
 */
@Configuration
@Import({ JdbcConfig.class })
public class SpringConfigImport {

}
