package org.wechat.sys.menu;


/**
 * @author Xie Jason
 *	普通按钮 
 * @date 2014年11月22日
 */
public class CommonButton extends Button{
	private Integer level;
	private String url;//view事件必须
	private Integer priv;
	
	public Integer getPriv() {
		return priv;
	}
	public void setPriv(Integer priv) {
		this.priv = priv;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "CommonButton [getPriv()=" + getPriv() + ", getUrl()=" + getUrl() + ", getLevel()=" + getLevel() + "]";
	}
	
	
	
	
	
	
	
}
