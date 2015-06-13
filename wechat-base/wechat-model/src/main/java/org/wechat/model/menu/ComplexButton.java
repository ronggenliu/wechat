package org.wechat.model.menu;

import java.util.Arrays;


/**
 * @author Xie Jason
 *	复杂按钮
 * @date 2014年11月22日
 */
public class ComplexButton extends Button{
	
	 private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}

	@Override
	public String toString() {
		return "ComplexButton [getSub_button()=" + Arrays.toString(getSub_button()) + "name:"+getName()+"]";
	} 
	
	
	 

}
