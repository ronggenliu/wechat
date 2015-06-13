package org.wechat.sys.menu;



/**
 * @author Xie Jason
 * 菜单
 * @date 2014年11月22日
 */
public class Menu extends CommonButton{
	
	private Button parent;

	public Button getParent() {
		return parent;
	}

	public void setParent(Button parent) {
		this.parent = parent;
	}
	
	

	
	public String toString() {
		String str="CommonButton [getPriv()=" + getPriv() + ", getUrl()=" + getUrl() + ", getLevel()=" + getLevel() + "]";
		str =str+"Button [getClassStyle()=" + getClassStyle() + ", getMenuId()=" + getMenuId() + ", getName()=" + getName() + "]";
		return str;
	}

	
	

	

	
	
	

}
