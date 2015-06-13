package org.wechat.sys.common;

import javax.servlet.http.HttpServletRequest;

import org.wechat.sys.model.UserInfo;

/**
 * @author xiejiesheng
 * 2015年5月3日
 */
public class SessionUtil {

	public static Object getSession(HttpServletRequest request){
		return request.getSession().getAttribute(request.getSession().getId());
	}
	
	public static Long getUserId(HttpServletRequest request){
		UserInfo user= (UserInfo) request.getSession().getAttribute(request.getSession().getId());
		return user.getUserId();
	}
}
