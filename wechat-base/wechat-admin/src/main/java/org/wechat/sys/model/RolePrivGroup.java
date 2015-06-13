package org.wechat.sys.model;

import java.io.Serializable;

/**
 * 用户权限组信息
 * @author XieJason
 * @date 2015年4月19日
 */
public class RolePrivGroup	implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4262085788194275957L;
	private Integer groupNo;  //权限组编号
	private String groupName;  //权限组名称
	private String groupAliasName;  //权限组别名

	/**
	获取权限组编号
	*/
	public Integer getGroupNo(){
		return this.groupNo;
	}
	/**
	赋值权限组编号
	*/
	public void setGroupNo(Integer groupNo){
		this.groupNo=groupNo;
	}
	/**
	获取权限组名称
	*/
	public String getGroupName(){
		return this.groupName;
	}
	/**
	赋值权限组名称
	*/
	public void setGroupName(String groupName){
		this.groupName=groupName== null||groupName.equals("") ? null : groupName.trim();
	}
	/**
	获取权限组别名
	*/
	public String getGroupAliasName(){
		return this.groupAliasName;
	}
	/**
	赋值权限组别名
	*/
	public void setGroupAliasName(String groupAliasName){
		this.groupAliasName=groupAliasName== null||groupAliasName.equals("") ? null : groupAliasName.trim();
	}
	public String toString() {
		 return " 权限组编号:"+ getGroupNo()+ ",  权限组名称:"+ getGroupName()+ ",  权限组别名:"+ getGroupAliasName()+ ", ";
	}

}