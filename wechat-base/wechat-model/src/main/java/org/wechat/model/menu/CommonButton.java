package org.wechat.model.menu;


/**
 * @author Xie Jason
 *	普通按钮 
 * @date 2014年11月22日
 */
public class CommonButton extends Button{
	private String type;
	private String key;
	private String url;//view事件必须
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "CommonButton [name:"+getName()+"getUrl()=" + getUrl() + ", getType()=" + getType() + ", getKey()=" + getKey() + "]";
	}
	
	
	
}
