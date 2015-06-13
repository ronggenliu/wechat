package org.wechat.model.common;

import java.io.Serializable;
import java.util.Arrays;

import org.wechat.common.util.JsonUtil;
import org.wechat.common.util.ReadFileUtil;


/**
 * 
 * 微信返回用户信息.
 * @author xiejiesheng
 * 2015年4月30日
 */
public class WechatUser implements Serializable{
	private static final long serialVersionUID = 6964980127558910238L;

	/**
	 * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	 */
	private Integer subscribe;
	
	private String openid;	
	
	private String nickname;
	
	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	private Integer sex;
	
	private String language;
	
	private String city;
	
	private String province;
	
	private String country;
	
	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，
	 * 0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	private String headimgurl;
	/**
	 * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	private Integer subscribe_time;
	
	/**
	 * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	 */
	private String[] privilege;
	
	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 * 详见：获取用户个人信息（UnionID机制）
	 */
	private String unionid;
	private String remark;
	
	
	
	
	/**
	 * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	 */
	public Integer getSubscribe() {
		return subscribe;
	}
	/**
	 * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	 */
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，
	 * 0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
	
	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，
	 * 0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	/**
	 * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	public Integer getSubscribe_time() {
		return subscribe_time;
	}
	
	/**
	 * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	public void setSubscribe_time(Integer subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String[] getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}
	
	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 * 详见：获取用户个人信息（UnionID机制）
	 */
	public String getUnionid() {
		return unionid;
	}
	
	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 * 详见：获取用户个人信息（UnionID机制）
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String toString() {
		return "WechatUser [subscribe=" + subscribe + ", openid=" + openid + ", nickname="
				+ nickname + ", sex=" + sex + ", language=" + language + ", city=" + city
				+ ", province=" + province + ", country=" + country + ", headimgurl=" + headimgurl
				+ ", subscribe_time=" + subscribe_time + ", privilege="
				+ Arrays.toString(privilege) + ", unionid=" + unionid + ", remark=" + remark + "]";
	}
	
	public static void main(String[] args) {
		String json=ReadFileUtil.getConfigFile("test/wechatUser.json");
		System.out.println(json);
		WechatUser user=JsonUtil.jsonToObj(json, WechatUser.class);
		System.out.println(user);
		
//		String userJson=JsonUtil.toJson(user);
//		WechatUserInfo userInfo=JsonUtil.jsonToObj(userJson, WechatUserInfo.class);
//		System.out.println(userInfo);
		
	}
	

}
