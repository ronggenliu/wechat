package org.wechat.model.msg.request;

/**
 * 文本消息
 * 
 * @date 2014-09-26
 */

public class TextMessage extends BaseMessage{

	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
