package org.wechat.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Param {

	/**
     * 参数名称，如username
     */
    String field();
    
	/**
     * 参数类型
     */
	ParamType type() default ParamType.STRING;
	
	 /**
     * 该参数是否是必须要传的，默认必须传
     */
    boolean required() default true;

    /**
     * 如果不是必须传，需要在这里说明参数的默认值
     */
    String defaultValue() default "";

    /**
     * 参数描述，用于详细说明参数格式
     */
    String[] desc() default "";

	/**
	 * 请求参数值的数据类型
	 */
	public static enum ParamType {
		STRING("字符串"), 
		INT("整数"), 
		LONG("长整数"), 
		POSITIVE_INT("正整数(>0整数)"), 
		NEGATIVE_INT("负整数(<0整数)"), 
		NATURAL_INT("自然数(>=0整数)"),
		FLOAT("浮点数"),
		POSITIVE_FLOAT("正浮点数"),
		NEGATIVE_FLOAT("负浮点数"), 
		BOOL("BOOL类型(仅true/y/1为真)"), 
		ENUM("枚举类型");

		private String detail;

		private ParamType(String detail) {
			this.detail = detail;
		}

		@Override
		public String toString() {
			return detail;
		}
	}
}
