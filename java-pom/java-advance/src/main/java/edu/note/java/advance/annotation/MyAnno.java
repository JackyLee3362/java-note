package edu.note.java.advance.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * public @interface 注解名称 {
 *     public 属性类型 属性名() default 默认值; // 这种在使用时不赋值时使用默认值
 *     public 属性类型 属性名(); // 这种在使用时一定要赋值
 * }
 *
 * 其中 【属性类型】 是【基本数据类型，String，Class，注解，枚举】 以上类型的一维数组
 * 
 * 【Target】取值
 * TYPE，类，接口
 * FIELD, 成员变量
 * METHOD, 成员方法
 * PARAMETER, 方法参数
 * CONSTRUCTOR, 构造方法
 * LOCAL_VARIABLE, 局部变量
 *
 */
@SuppressWarnings("all")
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE}) // MODULE 有这个选项吗
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnno {
    public String name() default "zhangsan";
    public int age() default 0;
    public String[] value();
}
