package edu.note.spring.bean.anno.importer;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author jackylee
 * @date 2026-04-19 15:38
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { "edu.note.spring.bean.anno.HelloDaoImpl" };
    }

}
