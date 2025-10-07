package edu.note.spring.aop.config;

import edu.note.spring.aop.BookDaoImpl;
import edu.note.spring.aop.aspect.MyAdviceAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author jackylee
 * @date 2025-10-05 15:58
 */
@Configuration
@Import({BookDaoImpl.class, MyAdviceAfter.class})
@EnableAspectJAutoProxy
public class SpringConfigAfter {

}
