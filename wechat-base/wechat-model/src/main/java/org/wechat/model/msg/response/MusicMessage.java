package org.wechat.model.msg.response;


/**
 * 音乐消息
 * 
 * 
 * @date 2014-09-26
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
