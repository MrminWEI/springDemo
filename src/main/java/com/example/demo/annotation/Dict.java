package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Dict
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dict {

  /**
   * 数据code
   */
  String dicCode();

  /**
   * 数据文本
   */
  String dicText() default "";


  /**
   * 数据字典表
   */
  String dictTable() default "";

}
