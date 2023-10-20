package com.transaction.function;

/**
 * @author styzf
 * @date 2023/2/8 21:43
 */
public interface ExceptionFunction<T, R> {
    
    R apply(T t) throws RuntimeException;
}
