package org.wechat.sys.menu;


/**
 * @author Xie Jason
 *按钮的基类 
 * @date 2014年11月22日
 */
public class Button {  
    private String name;
    private String menuId;
    private String classStyle;
	
	public String getClassStyle() {
		return classStyle;
	}
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}
    
    public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }
	@Override
	public String toString() {
		return "Button [getClassStyle()=" + getClassStyle() + ", getMenuId()=" + getMenuId() + ", getName()=" + getName() + "]";
	}

	

	
    
    
} 