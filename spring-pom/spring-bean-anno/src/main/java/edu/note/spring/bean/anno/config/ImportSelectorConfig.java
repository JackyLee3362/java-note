package edu.note.spring.bean.anno.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import edu.note.spring.bean.anno.importer.MyImportSelector;

/**
 * @author jackylee
 * @date 2026-04-19 14:42
 */
@Configuration
@Import(MyImportSelector.class)
public class ImportSelectorConfig {
}
