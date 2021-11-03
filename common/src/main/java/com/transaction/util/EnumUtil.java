package com.transaction.util;

/**
 * 枚举工具
 * @author yangzf
 * @date 2018/8/24
 */
public class EnumUtil {
	/**
	 * 根据实现BaseEnum的value()方法进行比较获取对应的枚举类
	 * @param i 进行比较的值
	 * @param clazz 实现了BaseEnum的枚举类
	 * @return
	 */
	public static <T, E extends BaseEnum<T>> E parse(T i, Class<E> clazz) {
		for (E base : clazz.getEnumConstants()){
	        if (base.value().equals(i)) {
	        	return base;
	        }
	    }
		return null;
	}
	
	/**
	 * 检查是否有该枚举类型
	 * @param i
	 * @param clazz
	 */
	public static <T, E extends BaseEnum<T>> void check(T i, Class<E> clazz) {
		E e = parse(i, clazz);
		if (e == null) {
			throw new RuntimeException("无该枚举类型");
		}
	}
	
	/**
     * 检测枚举
	 * @param baseEnum
     * @param clazz
     * @param <T>
     * @param <E>
	 */
	public static <T, E extends BaseEnum<T>> void check(BaseEnum<T> baseEnum, Class<E> clazz) {
		for (E base : clazz.getEnumConstants()){
			if (base.equals(baseEnum)) {
				return;
			}
		}
		throw new RuntimeException("无该枚举类型");
	}
	
	public interface BaseEnum<T> {
		T value();
	}
	
}
