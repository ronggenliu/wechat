package org.wechat.sys.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.wechat.common.sys.HttpRequestDeviceUtil;

public class LoginFilter implements Filter {

	private static Logger log=Logger.getLogger(LoginFilter.class);
	

	
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("begin loading data……");
		
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
		
		String requestURI = req.getRequestURI();
		String sessionId=req.getSession().getId();
//		log.info("request Time:"+DateUtil.getNowDateTime()+" | "+"request url:"+requestURI+" | sessionId:"+sessionId);
		
        //如果是手机请求，并且请求的路径都含有wechat字段，则都放过
        if(HttpRequestDeviceUtil.isMobileDevice(req)&&requestURI.contains("newmedia")){
//        	System.out.println("……");
        	log.info("手机微信请求……");
        	chain.doFilter(request, response);
        	return;
        }
        

//        requestURI=requestURI.replace("/wx-admin/", "");//去掉项目名称中的admin
        if(null!=sessionId){
        	
        	
        	//未登录用户，但访问的不是后台页面或action，直接放过
        	if(null == session.getAttribute(sessionId)&&!requestURI.contains("admin")&&!requestURI.contains(".action")){
//        		System.out.println(requestURI);
//        	 System.out.println("用户名为空，但不包含admin请求路径……");
        		chain.doFilter(request, response);
        		return;
        	}
        	
        	
        	if(null != session.getAttribute(sessionId)){
        	 
        		//后台管理才会有session
        		session.setMaxInactiveInterval(60*30);//闲置20分钟，将重新会话
        		chain.doFilter(request, response);
        		return;
        	}
        	else{
        		((HttpServletResponse) response).sendRedirect("/newmedia");
        		return;
        	}
        	
        }
        if(LoginUtil.isFreePath(requestURI)){
        	log.debug(requestURI);
			 chain.doFilter(request, response);
	         return;
		}
        ((HttpServletResponse) response).sendRedirect("/newmedia");
	}

	public  void destroy() {
		log.debug("正在销毁 filter...");
	}

}
