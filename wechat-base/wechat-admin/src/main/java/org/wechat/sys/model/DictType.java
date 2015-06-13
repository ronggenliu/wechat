package org.wechat.sys.model;

import java.io.Serializable;
/**
 * 字典类型
 * @author xiejiesheng
 * @date 2015年4月17日
 */
public class DictType	implements Serializable{
	private static final long serialVersionUID = -8628169268755508338L;
	private Long typeId;  //类型序号
	private String typeName;  //类型名称
	private String typeNo;  //类型编号
	private String typeAliasName;  //类型别名
	private Integer delFlag;  //
	private Long version;  //
	private String remark;  //备注

	/**
	获取类型序号
	*/
	public Long getTypeId(){
		return this.typeId;
	}
	/**
	赋值类型序号
	*/
	public void setTypeId(Long typeId){
		this.typeId=typeId;
	}
	/**
	获取类型名称
	*/
	public String getTypeName(){
		return this.typeName;
	}
	/**
	赋值类型名称
	*/
	public void setTypeName(String typeName){
		this.typeName=typeName== null||typeName.equals("") ? null : typeName.trim();
	}
	/**
	获取类型编号
	*/
	public String getTypeNo(){
		return this.typeNo;
	}
	/**
	赋值类型编号
	*/
	public void setTypeNo(String typeNo){
		this.typeNo=typeNo== null||typeNo.equals("") ? null : typeNo.trim();
	}
	/**
	获取类型别名
	*/
	public String getTypeAliasName(){
		return this.typeAliasName;
	}
	/**
	赋值类型别名
	*/
	public void setTypeAliasName(String typeAliasName){
		this.typeAliasName=typeAliasName== null||typeAliasName.equals("") ? null : typeAliasName.trim();
	}
	/**
	获取
	*/
	public Integer getDelFlag(){
		return this.delFlag;
	}
	/**
	赋值
	*/
	public void setDelFlag(Integer delFlag){
		this.delFlag=delFlag;
	}
	/**
	获取
	*/
	public Long getVersion(){
		return this.version;
	}
	/**
	赋值
	*/
	public void setVersion(Long version){
		this.version=version;
	}
	/**
	获取备注
	*/
	public String getRemark(){
		return this.remark;
	}
	/**
	赋值备注
	*/
	public void setRemark(String remark){
		this.remark=remark== null||remark.equals("") ? null : remark.trim();
	}
	public String toString() {
		 return " 类型序号:"+ getTypeId()+ ",  类型名称:"+ getTypeName()+ ",  类型编号:"+ getTypeNo()+ ",  类型别名:"+ getTypeAliasName()+ ",  :"+ getDelFlag()+ ",  :"+ getVersion()+ ",  备注:"+ getRemark()+ ", ";
	}

}