package com.wprojectframework.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * <pre>数据缓存专用方法元注解</pre>
 * @author  WuJ
 * @version v1.0
 * @date    2014年1月23日
 * @since   JDK1.6
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
	
	/**
	 * 缓存中对应的key
	 * @return String
	 */
	String key() default "";
	
	/**
	 * 缓存类型，默认为查询类型
	 * @return
	 */
	CacheType type() default CacheType.QUERY;
	
	/**
	 * 缓存保持秒数
	 * @return
	 */
	int time() default 0;
}
