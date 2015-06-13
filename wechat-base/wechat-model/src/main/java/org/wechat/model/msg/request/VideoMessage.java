package org.wechat.model.msg.request;

/**
 * 视频信息
 * @author jason
 * @date 2014-10-17
 */

public class VideoMessage extends BaseMessage {
	
	private String MediaId;
	private String ThumbMediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
