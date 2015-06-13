package org.wechat.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LazilyParsedNumber;

/**
 * 读取对象属性
 * 
 * @author xiejiesheng 2015年5月3日
 */

public class ObjectUtil {

	/**
	 * 将对象的属性名和属性值放入map中
	 */
	public static Map<String, Object> getProperties(Object o) {
		Map<String, Object> obj = new HashMap<String, Object>();
		List<String> fieldNames = getFieldNames(o);
		List<Object> fieldValues = getFieldValues(o);
		for (int i = 0; i < fieldNames.size(); i++) {
			obj.put(fieldNames.get(i), fieldValues.get(i));
		}
		return obj;
	}

	/**
	 * 获取对象属性名
	 * */
	public static List<String> getFieldNames(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		List<String> fieldNames = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			// System.out.println(fields[i].getType());
			fieldNames.add(fields[i].getName());
		}
		return fieldNames;
	}

	/**
	 * 获取对象属性的类型
	 * */
	public static Map<String, String> getFieldType(Class clazz) {
		// List<Object> fieldTypes = new ArrayList<Object>();
		Map<String, String> types = new HashMap<String, String>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			// fieldTypes.add(fields[i].getType());
			types.put(fields[i].getName(), fields[i].getType().getSimpleName());
		}
		return types;

	}

	/**
	 * 获取对象的所有属性值
	 * 
	 * */
	public static List<Object> getFieldValues(Object o) {
		List<String> fieldNames = getFieldNames(o);
		List<Object> value = new ArrayList<Object>();
		for (int i = 0; i < fieldNames.size(); i++) {
			value.add(getFieldValueByName(fieldNames.get(i), o));
		}
		return value;
	}

	/**
	 * 根据对象的属性取出对应属性的值
	 * */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
//		Map<String, String> types = getFieldType(UserInfo.class);
//		DataUtil.print(types);
////		System.out.println(types.get("userId").equals(java.lang.Long.class));
//		String test = "1231";
//		Double d = -123.9;
//		System.out.println(isNumber(d));
//		JsonPrimitive jsonType=new JsonPrimitive();

	}

	public static boolean isNumber(Object value) {
		return value instanceof Number;
	}

	public Number getAsNumber(Object value) {
		return (value instanceof String) ? new LazilyParsedNumber((String) value) : (Number) value;
	}

}
