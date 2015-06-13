package org.wechat.sys.model;

import java.io.Serializable;
/**
 * 登陆用户信息
 * @author xiejiesheng
 * @date 2015年4月17日
 */
public class UserInfo	implements Serializable{
	private static final long serialVersionUID = 6140690915135405632L;
	private Long userId;  //用户编号
	private String userName;  //用户名
	private String password;  //用户登录密码
	private Integer roleType;  //用户类型
	private Integer delFlag;  //软删除标识 0表示未删除，1表示已软删除
	private Long version;  //版本号
	
	private Long opId;  //操作者
	private String createTime;  //创建时间
	private String updateTime;  //修改时间
	private String wechatId;  //
	private String extra2;  //
	private String extra3;  //
	private String extra4;  //
	private String extra5;  //
	
	private String sessionId;//存放session,
	
	private Integer signUpType;//注册类型
	private Long roleId;  //作为关联主键
	private String isRemb;//是否记住用户名
	private String checkCode;//验证码
	private String groupAliasName;  //权限组别名
	private Long oldVersion;  //原版本号，防止并发修改书籍
	
	private String cmpyName;
	
	
	
	
	public String getCmpyName() {
		return cmpyName;
	}

	public void setCmpyName(String cmpyName) {
		this.cmpyName = cmpyName;
	}

	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getGroupAliasName() {
		return groupAliasName;
	}
	public void setGroupAliasName(String groupAliasName) {
		this.groupAliasName = groupAliasName;
	}
	/**
	 * 原版本号，作为修改条件，防止并发修改书籍
	 */
	public Long getOldVersion() {
		return oldVersion;
	}
	/**
	 * 原版本号，作为修改条件，防止并发修改书籍
	 */
	public void setOldVersion(Long oldVersion) {
		this.oldVersion = oldVersion;
	}
	public Integer getSignUpType() {
		return signUpType;
	}
	public void setSignUpType(Integer signUpType) {
		this.signUpType = signUpType;
	}
	public Long getCmpyId() {
		return roleId;
	}
	public void setCmpyId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取验证码
	 */
	public String getCheckCode() {
		return checkCode;
	}
	/**
	 * 验证码
	 */
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	/**
	 * 是否记住用户名，0：不记住，1：记住
	 */
	public String getIsRemb() {
		return isRemb;
	}
	/**
	 * 是否记住用户名，0：不记住，1：记住
	 */
	public void setIsRemb(String isRemb) {
		this.isRemb = isRemb;
	}

	/**
	获取用户编号
	*/
	public Long getUserId(){
		return this.userId;
	}
	/**
	赋值用户编号
	*/
	public void setUserId(Long userId){
		this.userId=userId;
	}
	/**
	获取用户名
	*/
	public String getUserName(){
		return this.userName;
	}
	/**
	赋值用户名
	*/
	public void setUserName(String userName){
		this.userName=userName== null||userName.equals("") ? null : userName.trim();
	}
	/**
	获取用户登录密码
	*/
	public String getPassword(){
		return this.password;
	}
	/**
	赋值用户登录密码
	*/
	public void setPassword(String password){
		this.password=password== null||password.equals("") ? null : password.trim();
	}
	/**
	获取用户类型 1：编辑者，2：企业用户，3：企业内部用户，100：系统用户
	*/
	public Integer getRoleType(){
		return this.roleType;
	}
	/**
	赋值用户类型,1：编辑者，2：企业用户，3：企业内部用户，100：系统用户
	*/
	public void setRoleType(Integer roleType){
		this.roleType=roleType;
	}
	/**
	获取软删除标识 0表示未删除，1表示已软删除
	*/
	public Integer getDelFlag(){
		return this.delFlag;
	}
	/**
	赋值软删除标识 0表示未删除，1表示已软删除
	*/
	public void setDelFlag(Integer delFlag){
		this.delFlag=delFlag;
	}
	/**
	获取版本号
	*/
	public Long getVersion(){
		return this.version;
	}
	/**
	赋值版本号
	*/
	public void setVersion(Long version){
		this.version=version;
	}
	/**
	获取操作者
	*/
	public Long getOpId(){
		return this.opId;
	}
	/**
	赋值操作者
	*/
	public void setOpId(Long opId){
		this.opId=opId;
	}
	/**
	获取创建时间
	*/
	public String getCreateTime(){
		return this.createTime;
	}
	/**
	赋值创建时间
	*/
	public void setCreateTime(String createTime){
		this.createTime=createTime== null||createTime.equals("") ? null : createTime.trim();
	}
	/**
	获取修改时间
	*/
	public String getUpdateTime(){
		return this.updateTime;
	}
	/**
	赋值修改时间
	*/
	public void setUpdateTime(String updateTime){
		this.updateTime=updateTime== null||updateTime.equals("") ? null : updateTime.trim();
	}
	/**
	获取微信ID
	*/
	public String getWechatId(){
		return this.wechatId;
	}
	/**
	赋值微信ID	
	*/
	public void setWechatId(String wechatId){
		this.wechatId=wechatId== null||wechatId.equals("") ? null : wechatId.trim();
	}
	/**
	获取
	*/
	public String getExtra2(){
		return this.extra2;
	}
	/**
	赋值
	*/
	public void setExtra2(String extra2){
		this.extra2=extra2== null||extra2.equals("") ? null : extra2.trim();
	}
	/**
	获取
	*/
	public String getExtra3(){
		return this.extra3;
	}
	/**
	赋值
	*/
	public void setExtra3(String extra3){
		this.extra3=extra3== null||extra3.equals("") ? null : extra3.trim();
	}
	/**
	获取
	*/
	public String getExtra4(){
		return this.extra4;
	}
	/**
	赋值
	*/
	public void setExtra4(String extra4){
		this.extra4=extra4== null||extra4.equals("") ? null : extra4.trim();
	}
	/**
	获取
	*/
	public String getExtra5(){
		return this.extra5;
	}
	/**
	赋值
	*/
	public void setExtra5(String extra5){
		this.extra5=extra5== null||extra5.equals("") ? null : extra5.trim();
	}
	public String toString() {
		 return " 验证码:"+ getCheckCode()+ ",  用户名:"+ getUserName()+ ",  用户登录密码:"+ getPassword()+ ",  用户类型:"+ getRoleType()+ ",  软删除标识 0表示未删除，1表示已软删除:"+ getDelFlag()+ ",  版本号:"+ getVersion()+ ",  操作者:"+ getOpId()+ ",  创建时间:"+ getCreateTime()+ ",  修改时间:"+ getUpdateTime();
	}

}