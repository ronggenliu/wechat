package org.wechat.model.menu;


/**
 * @author Xie Jason
 * 按钮的基类 
 * @date 2014年11月22日
 */
public class Button {  
    private String name;  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }

	@Override
	public String toString() {
		return "Button [getName()=" + getName() + "]";
	}  
    
    
} 