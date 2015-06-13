package org.wechat.core.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.core.service.impl.CoreService;
import org.wechat.core.util.SignUtil;

/**
 * 
 * 微信接口的入口将以前的servlet改成controller，方便处理业务逻辑
 * 
 * @author xiejs
 * @date 2015年5月6日
 */

@Controller
@RequestMapping("/wechat")
public class WechatController {

	private static Logger log = LoggerFactory.getLogger(WechatController.class);

	@Autowired
	CoreService coreService;
	
	@RequestMapping(value = "/do",
			method = RequestMethod.GET,
			name="确认请求来自微信服务器"
			)
	@ResponseBody
	public final void getDo(final HttpServletResponse response, final HttpServletRequest request) throws IOException {

		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		log.debug("签名：" + signature);
		log.debug("接收时间：" + timestamp);
		log.debug("随机字符串：" + echostr);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	
	
	@RequestMapping(value = "/do", 
			method = RequestMethod.POST,
			name="处理微信服务器发来的消息"
			)
	@ResponseBody
	public final void postDo( HttpServletResponse response,  HttpServletRequest request) throws IOException {

		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
        String respMessage =coreService.processRequest(request);  
        
        response.setContentType("text/xml");
        response.setHeader("encoding", "UTF-8");
          
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();
	}
}
