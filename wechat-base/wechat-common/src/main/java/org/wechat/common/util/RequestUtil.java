package org.wechat.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author xiejs
 * @date 2015年5月13日
 */
public class RequestUtil {

	public static Map<String,Long> checkLoadTime=new HashMap<String,Long>();
	public static Map<String,String> checkLoadUrl=new HashMap<String,String>();
	
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
				else if(DataUtil.isValidStr(types.get(params[0]))&&!types.get(params[0]).equals("String")){
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
	 * 判断是同一个session在连续请求
	 * @author xiejs
	 * @date 2015年5月13日
	 * @param request
	 * @return boolean
	 */
	public static boolean isRepeatRequest(HttpServletRequest request){
		HttpSession session = request.getSession();
		String sesssionId=session.getId();
		boolean flag=false;
		String url=request.getRequestURI();
		if(!checkLoadUrl.containsKey(sesssionId)){
			checkLoadUrl.put(sesssionId, url);
			checkLoadTime.put(sesssionId, System.currentTimeMillis());
		}
		else if(checkLoadUrl.get(sesssionId).equals(url)){
			Long lagTime=System.currentTimeMillis()-checkLoadTime.get(sesssionId);
//			System.out.println(lagTime);
			if(lagTime<2000){
				flag= true;
			}
			else{
				checkLoadUrl.remove(sesssionId);
				checkLoadTime.remove(sesssionId);
				checkLoadUrl.put(sesssionId, url);
				checkLoadTime.put(sesssionId, System.currentTimeMillis());
			}
		}
		else{
			checkLoadUrl.remove(sesssionId);
			checkLoadTime.remove(sesssionId);
		}
		return flag;
		
	}
}
