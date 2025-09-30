package edu.note.java.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import edu.note.java.model.User;

/**
 * public @interface 注解名称 {
 * -- public 属性类型 属性名() default 默认值;
 * -- public 属性类型 属性名();
 * }
 *
 * 属性类型
 * - 原始类型(int, boolean...)
 * - String
 * - Class
 * - 注解
 * - 枚举
 * - 以上类型的数组 []
 * 
 * Target
 * - TYPE，类，接口
 * - FIELD, 成员变量
 * - METHOD, 成员方法
 * - PARAMETER, 方法参数
 * - CONSTRUCTOR, 构造方法
 * - LOCAL_VARIABLE, 局部变量
 *
 */
@SuppressWarnings("all")
@Target({ TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE })
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnno {
    public String name() default "Foo";

    public int age() default 0;

    public String[] value();
}
