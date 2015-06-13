package org.wechat.sys.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils.MethodFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wechat.common.sys.DocItem;
import org.wechat.sys.service.impl.ContextSV;

/**
 * 获取controller对外接口的信息
 * 
 * @author xiejiesheng 2015年5月5日
 */
public class ControllerUtil {

	public static List<DocItem> getAllDocItem() {

		ApplicationContext appContext = ContextSV.appContext;
		System.out.println(appContext.getApplicationName());
		System.out.println(appContext.getDisplayName());
		System.out.println(appContext.getBeanDefinitionCount());
		System.out.println(appContext.getBeanDefinitionNames().length);
		// 请求url和处理方法的映射
		List<DocItem> docItems = new ArrayList<DocItem>();
		// 获取所有的RequestMapping
		// Map<String, Object> beans = BeanFactoryUtils
		// .beansOfTypeIncludingAncestors(appContext, Object.class, false, true);
		List<Object> beans = new ArrayList<Object>(appContext.getBeansOfType(Object.class, false, true).values());
		System.out.println(beans.size());
		// System.out.println("docItems:"+beans);
		// WriteUtil.writeFile("e:/", beans.toString(),"beans.txt");
		String className = "";
		for (int i = 0; i < appContext.getBeanDefinitionCount(); i++) {
			Class<?> clazz = beans.get(i).getClass();
			className = clazz.getName();
			if (className.endsWith("Controller")) {
				System.out.println(className);
				String classPath = clazz.getAnnotation(RequestMapping.class).value()[0];
				System.out.println(classPath);
				Collection<Method> methods = ReflectionTools.getAllMethodsInSourceCodeOrder(clazz, new MethodFilter() {

					public boolean matches(Method method) {
						return null != method.getAnnotation(RequestMapping.class);
					}
				});
				for (Method m : methods) {
					System.out.println(m.getName());
					System.out.println(m.getAnnotation(RequestMapping.class).value().length);
					System.out.println(m.getAnnotation(RequestMapping.class).method().length);
					if (m.getAnnotation(RequestMapping.class).value().length > 0) {
						System.out.print(m.getAnnotation(RequestMapping.class).value()[0]);
					}
					if (m.getAnnotation(RequestMapping.class).method().length > 0) {
						System.out.println(m.getAnnotation(RequestMapping.class).method()[0]);
					}
					if (m.getAnnotation(RequestMapping.class).params().length > 0) {
						System.out.println("params:" + m.getAnnotation(RequestMapping.class).params()[0]);
					}
					System.out.println("name:" + m.getAnnotation(RequestMapping.class).name());

				}

			}
		}
		return docItems;
	}

}


