package org.wechat.common.sys;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	//根据用户名删除cookie
	public static void deleteCookie(String username,Cookie[] cookies ,HttpServletResponse response){
		 Cookie c = null ;
	       for(int i=0;i<cookies.length;i++){
	           c = cookies[i] ;
	           if(c.getName().equals(username)){
	              c.setMaxAge(0);
	              response.addCookie(c) ;
	           }
	       }
	}
	
	//根据用户名获取cookie
	public static Cookie getCookie(String username,Cookie[] cookies){
		if(cookies==null||cookies.length==0){
			return null;
		}
		else{
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals(username)){
					return cookies[i];
				}
			}
			return null;
		}
	}
	
	public static void testCookies(Cookie[] cookies){
		if (cookies!=null){
			for(int i=0;i<cookies.length;i++){
				Cookie cookie = cookies[i];
				System.out.println("cookie name:"+cookie.getName()+"  value:"+cookie.getValue()+"  expires:"+cookie.getMaxAge());
//				System.out.println();
			}
		}
	}

}
