package org.wechat.sys.service.impl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * 系统启动，加载数据.
 * @author xiejiesheng
 * 2015年4月21日
 */
@Service
public class ContextSV implements ApplicationListener<ContextRefreshedEvent>,ServletContextListener {
	private static Logger log=Logger.getLogger(ContextSV.class);

	public static WebApplicationContext webAppContext;
	public static ApplicationContext appContext;
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//root application context 没有parent，他就是老大. 
		if(event.getApplicationContext().getParent() == null){ 
	         //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。 
//			initContext();
			appContext=event.getApplicationContext();
//			 List<Object> beans = new ArrayList<Object>(appContext.getBeansOfType(Object.class, false, true).values());
//			 DataUtil.print(beans);
	    }  
		
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		  System.out.println("context destroying……");
	}

	public void contextInitialized(ServletContextEvent event) {
		 System.out.println("context initial……");
		  webAppContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}

}
