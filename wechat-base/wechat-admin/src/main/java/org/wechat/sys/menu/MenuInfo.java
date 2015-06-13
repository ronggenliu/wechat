package org.wechat.sys.menu;

public class MenuInfo {
	private String name;
	private String url;
	private Integer level;
	private String menuId;
	private Integer priv;
	private String classStyle;
	private CommonButton parent;
	
	public CommonButton getParent() {
		return parent;
	}
	public void setParent(CommonButton parent) {
		this.parent = parent;
	}
	
	
	public String getClassStyle() {
		return classStyle;
	}
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}
	public Integer getPriv() {
		return priv;
	}
	public void setPriv(Integer priv) {
		this.priv = priv;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Override
	public String toString() {
		return "MenuInfo [getParent()=" + getParent() + ", getClassStyle()=" + getClassStyle() + ", getPriv()=" + getPriv() + ", getName()=" + getName() + ", getUrl()=" + getUrl() + ", getLevel()=" + getLevel() + ", getMenuId()=" + getMenuId() + "]";
	}
	
	
	
	

	
	

}
