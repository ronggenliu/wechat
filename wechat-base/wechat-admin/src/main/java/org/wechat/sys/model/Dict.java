package org.wechat.sys.model;

import java.io.Serializable;
/**
 * 字典信息
 * @author xiejiesheng
 * @date 2015年4月17日
 */
public class Dict	implements Serializable{
	private static final long serialVersionUID = 8362233703581466909L;
	private Long dictId;  //字典ID
	private String dictType;  //字典类型
	private String dictKey;  //名称
	private String dictValue;  //字典值
	private Integer delFlag;  //
	private Long version;  //

	
	private String typeName;//类型名称
	
	
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	获取字典ID
	*/
	public Long getDictId(){
		return this.dictId;
	}
	/**
	赋值字典ID
	*/
	public void setDictId(Long dictId){
		this.dictId=dictId;
	}
	/**
	获取字典类型
	*/
	public String getDictType(){
		return this.dictType;
	}
	/**
	赋值字典类型
	*/
	public void setDictType(String dictType){
		this.dictType=dictType== null||dictType.equals("") ? null : dictType.trim();
	}
	/**
	获取名称
	*/
	public String getDictKey(){
		return this.dictKey;
	}
	/**
	赋值名称
	*/
	public void setDictKey(String dictKey){
		this.dictKey=dictKey== null||dictKey.equals("") ? null : dictKey.trim();
	}
	/**
	获取字典值
	*/
	public String getDictValue(){
		return this.dictValue;
	}
	/**
	赋值字典值
	*/
	public void setDictValue(String dictValue){
		this.dictValue=dictValue== null||dictValue.equals("") ? null : dictValue.trim();
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
	public String toString() {
		 return " 字典ID:"+ getDictId()+ ",  字典类型:"+ getDictType()+ ",  名称key:"+ getDictKey()+ ",  字典值:"+ getDictValue()+ ",  :"+ getDelFlag()+ ",  :"+ getVersion()+ ", ";
	}

}