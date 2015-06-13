package org.wechat.model.menu;

import java.util.Arrays;


/**
 * @author Xie Jason
 * 菜单
 * @date 2014年11月22日
 */
public class Menu {
	
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}

	@Override
	public String toString() {
		return "Menu [getButton()=" + Arrays.toString(getButton()) + "]";
	}
	
	

}
