package org.wechat.common.util;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	
	/**
	 * 将对象转换成json字符串
	 * 
	 **/
	
	public static String toJson(Object obj){
//		Gson gson = new Gson();
//		Type type=new TypeToken<Object>(){}.getType();
		return new Gson().toJson(obj);
	}
	
	/**
	 * 将json字符串转换为单个对象
	 * @param <T>
	 * 
	 **/
	public static <T> T jsonToObj(String json,Class<T> clazz){
//		Gson gson = new Gson();
		
		return new Gson().fromJson(json, clazz);
	}
	
	/**
	 * 将json字符串转换为对象集合
	 * @param <T>
	 **/
	public static <T> List<T> jsonToList(String json){
//		Type type=new TypeToken<List<T>>(){}.getType();
		return new Gson().fromJson(json, new TypeToken<List<T>>(){}.getType());
	}
	
	/**
	 * 少用，仅将单个对象转换
	 */
	public static  Map<Object,Object> jsonToMap(String json){
//		Type type=new TypeToken<List<T>>(){}.getType();
		return new Gson().fromJson(json, new TypeToken<Map<Object,Object>>(){}.getType());
	}
	
	public static  Map<Object,Object> objToMap(Object obj){
//		Type type=new TypeToken<List<T>>(){}.getType();
		Gson gson = new Gson();
		
		String json=gson.toJson(obj);
		return gson.fromJson(json, new TypeToken<Map<Object,Object>>(){}.getType());
	}

}
