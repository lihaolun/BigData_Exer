package com.atguigu.bigdata.hbase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnRef {
    boolean rowKey() default false;
    String columnFamily() default "info";
    String column() default "";
}
