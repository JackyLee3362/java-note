package edu.note.spring.aop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import edu.note.spring.aop.BookDaoImpl;
import edu.note.spring.aop.aspect.MyAdviceBefore;

/**
 * @author jackylee
 * @date 2025-10-05 15:58
 */
@Configuration
@Import({ BookDaoImpl.class, MyAdviceBefore.class })
@EnableAspectJAutoProxy
public class SpringConfigBefore {

}
