package org.wechat.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wechat.core.util.MessageUtil;
import org.wechat.model.msg.response.Article;
import org.wechat.model.msg.response.NewsMessage;
import org.wechat.model.msg.response.TextMessage;



/**
 * 微信核心服务类.
 * @author jason
 * @date 2014-10-16
 */
@Service("coreService")
public class CoreService {
	
	private static final Logger log=LoggerFactory.getLogger(CoreService.class);
	public static final String newLine="\n";
	
	
	public  String processRequest(HttpServletRequest request) {
		
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");//用户的openId
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");//我们自己公众号的openId
			// 消息类型
			String msgType = requestMap.get("MsgType");//用户发送的消息类型
			log.info("fromUserName:"+fromUserName);
			log.info("toUserName:"+toUserName);
			log.info("msgType:"+msgType);

			TextMessage textMessage = new TextMessage();
			
			
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//消息内容  调试用
				String content = requestMap.get("Content").trim(); 
				log.debug("msgContent:"+content);
				//测试
				 if(content.equals("10")){
					respContent="用户的openId:"+requestMap.get("FromUserName")+"\n";
					respContent=respContent+"我们的origin_name:"+requestMap.get("ToUserName");
					textMessage.setToUserName(requestMap.get("FromUserName"));
					textMessage.setFromUserName(requestMap.get("ToUserName"));
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setFuncFlag(0);
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
					return respMessage;
				}
				
			}
			// 图片消息，暂未有相关业务处理
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				// 取得图片地址  
                String picUrl = requestMap.get("PicUrl");
                log.info("进入图片信息："+picUrl);
//                responseMsgSV.dealRespText(textMessage, ruleInfo, requestMap);
				respMessage = MessageUtil.textMessageToXml(textMessage);
               
			}
			// 地理位置消息，位置导航
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				
			}
			
			
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				//事件定义返回消息的规则关键词是 key值
				String key = requestMap.get("EventKey");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
				}
				// 点击菜单拉取事件，暂且只做点击事件的业务
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					
					if(key.equals("CBPT")){
						String url="http://xmt.soukong.cn/newmedia/pages/mobile/spreadPlatform.html?openId="+requestMap.get("FromUserName");
						List<Article> articles=new ArrayList<Article>();
						Article art1=new Article();
						art1.setTitle("传播平台");
						art1.setUrl(url);
						art1.setPicUrl("http://img.nr99.com/attachment/forum/201305/29/153759xtv9nnn3yfz3nqn0.jpg");
						art1.setDescription("传播平台的介绍信息");
						articles.add(art1);
						
						NewsMessage news=new NewsMessage();
						news.setToUserName(requestMap.get("FromUserName"));
						news.setFromUserName(requestMap.get("ToUserName"));
						news.setCreateTime(new Date().getTime());
						news.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						news.setFuncFlag(0);
						news.setArticleCount(articles.size());
						news.setArticles(articles);
						respMessage = MessageUtil.newsMessageToXml(news);
						return respMessage;
					}
					else if(key.equals("QYJBD")){
						String url="http://xmt.soukong.cn/newmedia/pages/mobile/companyBandWechat.html?openId="+requestMap.get("FromUserName");
						List<Article> articles=new ArrayList<Article>();
						Article art1=new Article();
						art1.setTitle("企业绑定微信");
						art1.setUrl(url);
						art1.setPicUrl("http://img.nr99.com/attachment/forum/201305/29/153801fhpka9hf9f6aebf6.jpg");
						art1.setDescription("企业绑定微信的介绍信息");
						articles.add(art1);
						
						NewsMessage news=new NewsMessage();
						
						news.setToUserName(requestMap.get("FromUserName"));
						news.setFromUserName(requestMap.get("ToUserName"));
						news.setCreateTime(new Date().getTime());
						news.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						news.setFuncFlag(0);
						
						news.setArticleCount(articles.size());
						news.setArticles(articles);
						respMessage = MessageUtil.newsMessageToXml(news);
						return respMessage;
					}
				}
				
			}
			
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
	
}

