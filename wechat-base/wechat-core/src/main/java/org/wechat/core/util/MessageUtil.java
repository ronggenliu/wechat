package org.wechat.core.util;


import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.wechat.model.msg.response.Article;
import org.wechat.model.msg.response.ImageMessage;
import org.wechat.model.msg.response.MusicMessage;
import org.wechat.model.msg.response.NewsMessage;
import org.wechat.model.msg.response.TextMessage;
import org.wechat.model.msg.response.VideoMessage;
import org.wechat.model.msg.response.VoiceMessage;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


/**
 * 消息工具类,负责处理发送数据的格式
 * @author xiejason
 * @date 2014-09-26
 */
public class MessageUtil {
		//请求消息
		public static final String REQ_MESSAGE_TYPE_TEXT = "text";//请求消息 文本
		public static final String REQ_MESSAGE_TYPE_IMAGE="image"; // 请求    图片
		public static final String REQ_MESSAGE_TYPE_VOICE="voice"; //请求  语音
		public static final String REQ_MESSAGE_TYPE_VIDEO="video";//请求  视频
		public static final String REQ_MESSAGE_TYPE_LOCATION="location"; //请求 位置
		public static final String REQ_MESSAGE_TYPE_LINK="link"; //请求 链接
		
		
		//响应消息
		public static final String RESP_MESSAGE_TYPE_TEXT = "text";
		public static final String RESP_MESSAGE_TYPE_IMAGE="image";
		public static final String RESP_MESSAGE_TYPE_VOICE="voice"; 
		public static final String RESP_MESSAGE_TYPE_VIDEO="video";
		public static final String RESP_MESSAGE_TYPE_MUSIC="music";
		public static final String RESP_MESSAGE_TYPE_NEWS="news";
		
		
		//事件推送
		public static final String REQ_MESSAGE_TYPE_EVENT="event";//事件推送
		public static final String EVENT_TYPE_SUBSCRIBE="subscribe";//订阅
		public static final String EVENT_TYPE_UNSUBSCRIBE="unsubscribe";//取消订阅
		public static final String EVENT_TYPE_SCAN="SCAN";//关注用户扫描带参数二维码
		public static final String EVENT_TYPE_LOCATION="LOCATION";//上报地理位置
		public static final String EVENT_TYPE_CLICK="CLICK";//点击菜单拉取
		public static final String EVENT_TYPE_VIEW="VIEW";//点击菜单跳转链接
		

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

	/**
	 *  resp 文本消息对象转换成xml
	 * 
	 * @param textMessage 文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	/**
	 *	 resp image转换为 xml 
	 */
	public static String messageToXml(ImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	
	/**
	 *	 resp 语音 转换为 xml 
	 */
	public static String messageToXml(VoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}
	
	/**
	 *	 resp 视频 转换为 xml 
	 */
	public static String messageToXml(VideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}

	/**
	 *  resp 音乐消息对象转换成xml
	 * 
	 * @param musicMessage 音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * resp  图文消息对象转换成xml
	 * @param newsMessage 图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * @date 2014-09-26
	 * 说明：由于xstream框架本身并不支持CDATA块的生成，以下代码是对xtream做了扩展，使其支持在生成xml各元素值时添加CDATA块。
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				
				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}
				

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
}

