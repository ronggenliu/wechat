package org.wechat.sys.menu;

import java.util.Arrays;


/**
 * @author Xie Jason
 *	复杂按钮
 * @date 2014年11月22日
 */
public class ComplexButton extends Button{
	
	 private Button[] groupMenu;

	public Button[] getGroupMenu() {
		return groupMenu;
	}

	public void setGroupMenu(Button[] groupMenu) {
		this.groupMenu = groupMenu;
	}

	@Override
	public String toString() {
		return "ComplexButton [getGroupMenu()=" + Arrays.toString(getGroupMenu()) + "]";
	}

	

	

	

	

	
	
	
	
	 

}
