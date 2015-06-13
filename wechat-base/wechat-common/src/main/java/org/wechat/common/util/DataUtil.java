package org.wechat.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 处理 数据的转换，验证，并返回结果
 * @author Xiejs
 * @date 2015年1月5日
 * @import：都是java自带的jar包
 * 
 */
public class DataUtil {

	public static Map<String, Long> checkLoadTime = new HashMap<String, Long>();
	public static Map<String, String> checkLoadUrl = new HashMap<String, String>();

	/**
	 * 判断字符串是否为空或者null
	 * @param str 要验证的参数
	 * @return boolean
	 */
	public static boolean isValidStr(String str) {
		if (null == str || "".equals(str)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断Map是否为空或者null
	 * @param Map:要验证的参数
	 * @return boolean
	 */
	public static boolean isValidMap(Map map) {
		if (null == map || map.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 如果list为null，或者size=0，则返回false
	 * @param list
	 * @return boolean
	 */
	public static boolean isValidList(List list) {
		if (null == list || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 如果set为null，或者size=0，则返回false
	 * @param set
	 * @return boolean
	 */
	public static boolean isValidSet(Set set) {
		if (null == set || set.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static <T> void print(List<T> objs) {
		for (T obj : objs) {
			System.out.println(obj);
		}
	}

	public static <T> void print(T[] objs) {
		for (T obj : objs) {
			System.out.println(obj);
		}
	}

	public static void print(Map map) {
		Iterator it = map.entrySet().iterator();
//		System.out.println(map.entrySet().size());
		String key;
		String value;
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			key = entry.getKey().toString();
			value = entry.getValue().toString();
			System.out.println(key + "==" + value);
		}
	}
	
	/**
	 * 判断两个字符串是否相等，屏蔽为null和为空的情况
	 * @author xiejs
	 * @date 2015年4月30日
	 * @param str1
	 * @param str2
	 */
	public boolean equals(String str1,String str2){
		if(null==str1&&null==str2){
			return true;
		}
		if(str1.equals("")&&str2.equals("")){
			return true;
		}
		if(DataUtil.isValidStr(str1)&&DataUtil.isValidStr(str2)){
			if(str1.equals(str2)){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 计算text的长度（一个中文算两个字符）
	 * @param text
	 * @return int： text的长度
	 */
	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	/**
	 * 对比两个同类对象对应属性的值是否相等
	 */
	public static boolean isChanged(Object oldOne, Object newOne) {
		boolean flag = false;
		Map<String, Object> olds = ObjectUtil.getProperties(oldOne);
		Map<String, Object> news = ObjectUtil.getProperties(newOne);
		List<String> keys = ObjectUtil.getFieldNames(oldOne);
		for (int i = 0; (i < keys.size()) && !flag; i++) {
			if (olds.get(keys.get(i)) != null && news.get(keys.get(i)) != null) {
				if (!olds.get(keys.get(i)).equals(news.get(keys.get(i)))) {
					flag = true;
				}
			} else if (olds.get(keys.get(i)) == null && news.get(keys.get(i)) == null) {
				continue;
			} else {
				flag = true;
			}
		}
		return flag;
	}

	/** 深复制 */
	public static Object deepClone(Object o) {

		/* 写入当前对象的二进制流 */
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(o);
			/* 读出二进制流产生的新对象 */
			bis = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bis);
			return ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * utf-8 解码
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将前台传过来的ids字符串转换成List<Object> param str 要转换的字符串 param split 字符串str按照split切分
	 * 
	 */
	public static List<Object> strToList(String str, String split) {
		if (str == null || str == "") {
			return null;
		}
		List<Object> ids = new ArrayList<Object>();
		String[] list = str.split(split);
		for (int i = 0; i < list.length; i++) {
			ids.add(list[i].trim());
		}
		return ids;

	}
	
	/**
	 * 将请求参数装换为map对象.
	 * @param requestBody：请求参数字符串：userName=&page=1&rows=10;
	 * @return Map<String,Object> ：查询条件
	 */
	public static Map<String,Object> getRequestBody(String requestBody){
		String[] paramArr=requestBody.split("&");
		Map<String,Object> condition=new HashMap<String, Object>();
		for(int i=0;i<paramArr.length;i++){
			String[] params=paramArr[i].split("=");
			if(params.length==2){
				if(params[0].equals("page")||params[0].equals("rows")){
					condition.put(params[0], Integer.parseInt(params[1]));
				}
				else{//其他都默认为String
					condition.put(params[0], params[1]);
				}
			}
		}
		
		//如果有分页查询
		if(condition.get("page")!=null){
			int start=(DataType.getAsInt(condition.get("page"))-1)*(DataType.getAsInt(condition.get("rows")));
			condition.put("start", start);
		}
		return condition;
	}
	
	/**
	 * 将请求参数装换为map对象,需要对象类型，方便装换.
	 * @author xiejs
	 * @date 2015年5月3日
	 * @param requestBody：请求参数字符串：userName=&page=1&rows=10;
	 * @param clazz：对象类型：UserInfo.class
	 * @return Map<String,Object> ：查询条件
	 */
	public static Map<String,Object> getRequestBody(String requestBody,Class clazz){
		String[] paramArr=requestBody.split("&");
		Map<String,Object> condition=new HashMap<String, Object>();
		Map<String,String> types=ObjectUtil.getFieldType(clazz);
		for(int i=0;i<paramArr.length;i++){
			String[] params=paramArr[i].split("=");
			if(params.length==2){
				if(params[0].equals("page")||params[0].equals("rows")){
					condition.put(params[0], Integer.parseInt(params[1]));
				}
				else if(isValidStr(types.get(params[0]))&&!types.get(params[0]).equals("String")){
					//如果该字符串是对象类型的属性字段，则转换为属性字段的属性类型，除了String类型
					condition.put(params[0], DataType.transfer(params[1], types.get(params[0])));
				}
				else{//其他都默认为String
					condition.put(params[0], params[1]);
				}
			}
		}
		//如果有分页查询
		if(condition.get("page")!=null){
			int start=(DataType.getAsInt(condition.get("page"))-1)*(DataType.getAsInt(condition.get("rows")));
			condition.put("start", start);
		}
		return condition;
	}

	/**
	 * 服务： 字符串是否含数字
	 * @auther xiejason
	 * @param content
	 */
	public static boolean hasDigit(String content) {

		boolean flag = false;
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher m = p.matcher(content);
		if (m.matches())
			flag = true;
		return flag;

	}

	/**
	 *  判断字符串str是否是数字，各种数字
	 * @auther xiejason
	 * @param str
	 */
	public static boolean isNumeric(String str) {
		if (!DataUtil.isValidStr(str)) {
			return false;
		}
		boolean isNum = false;
		Pattern pattern = Pattern.compile("[0-9]*");
		if (pattern.matcher(str).matches()) {
			isNum = true;
		} else if (str.contains(".") || str.contains("-")) {
			String[] strArr = str.split("\\.");
			String[] strArr2 = str.split("-");
			int isNav = str.indexOf("-");
			int isNav2 = str.indexOf(".");
			if (strArr.length > 2 || strArr2.length > 2 || isNav > 0 || isNav2 == 0 || isNav2 == str.length() - 1) {
				isNum = false;
			} else {
				String str1 = str.replace(".", "");
				String str2 = str.replace("-", "");
				String str3 = str1.replace("-", "");
				if (pattern.matcher(str1).matches()) {
					isNum = true;
				}
				if (pattern.matcher(str2).matches()) {
					isNum = true;
				}
				if (pattern.matcher(str3).matches()) {
					isNum = true;
				}
			}
		}
		return isNum;
	}

	/**
	 * 如果字符串为空或者null，则返回null，否则返回本身
	 * @author xiejs
	 * @date 2015年5月6日
	 * @param str
	 * @return null，或则str.trim()
	 */
	public static String getStr(String str) {
		if (null == str || str.trim().equals("")) {
			return null;
		} else {
			return str.trim();
		}
	}
	
	/**
	 * 对小数进行格式化处理的方法.
	 * @param number 要处理的数字
	 * @param format 格式化字符串，例如0.00,标识保留两位小数
	 * @return 格式化后的数字
	 */
	public static String formatDouble(final double number, final String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}
	
	/**
	 * 对小数进行格式化处理的方法.
	 * @param number 要处理的数字
	 * @return 格式化后的数字：2.00
	 */
	public static String defaultFormatDouble(final double number) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(number);
	}

	/**
	 * 
	 * @author xiejs
	 * @date 2015年5月6日
	 * @return 随机字符串，32位
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}


	/**
	 * 
	 * 判断字符串是有汉字
	 * @auther xiejason
	 * @param str
	 * @param isAll true:判断该字符串是否是汉字，false:判断该字符串是否包含汉字
	 */
	public static boolean hasChinese(String str, boolean isAll) {
		Pattern p_str = Pattern.compile("[\\u4e00-\\u9fa5]+");
		Matcher m = p_str.matcher(str);
		if (isAll) {
			if (m.find() && m.group(0).equals(str)) {
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if (m.find()) {
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	/**
	 * 获取随机数字串
	 * @author xiejs
	 * @date 2015年5月6日
	 * @param len 返回字符创的长度
	 * @return 随机数字，长度为len
	 */
	public static String getRandStr(int len) {
		StringBuffer randStr = new StringBuffer();
		Random random = new Random();
		for(int i = 0; i < len; i++) {
			randStr.append(random.nextInt(10));
		}
		return randStr.toString();
	}
	
	/**
	 * 获取随机字符串，有字母大小写和数字组成
	 * @author xiejs
	 * @date 2015年5月6日
	 * @return 随机字符串，长度为len
	 */
	public static String getSncode(int len){
		StringBuffer buf = new StringBuffer("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
		buf.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
//		buf.append(",~,@,#,$,%,^,&,*,(,),_,+,|,`,.");
		buf.append(",1,2,3,4,5,6,7,8,9,0");
		String[] arr = buf.toString().split(",");
		
		StringBuffer b = new StringBuffer();
		java.util.Random r;
		int k ;
		for(int i=0;i<len;i++){
			 r = new java.util.Random();
			 k = r.nextInt();
			 b.append(String.valueOf(arr[Math.abs(k % 61)]));//字符数-1
		}
		
		return b.toString();
	}
	
	

	public static void main(String[] args) {

		String str = "123";
		System.out.println(getSncode(20));
	}


}
