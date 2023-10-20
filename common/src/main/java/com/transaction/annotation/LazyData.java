package com.transaction.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Consumer;

/**
 * @author styzf
 * @date 2023/2/8 21:53
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface LazyData {
    String value();
}
