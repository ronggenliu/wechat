package org.wechat.model.token;

import java.io.Serializable;

import org.wechat.common.util.JsonUtil;
import org.wechat.common.util.ReadFileUtil;

/**
 * 这里通过code换取的是一个特殊的网页授权access_token,
 * 与基础支持中的access_token（该access_token用于调用其他接口）不同.
 * @author xiejiesheng
 * 2015年4月30日
 */
public class SnsToken implements Serializable{
	
	private static final long serialVersionUID = 4282013279922462323L;
	/**
	 * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	 */
	private String openid;
	
	/**
	 * 用户授权的作用域，使用逗号（,）分隔
	 */
	private String scope;
	/**
	 * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 */
	private String access_token;
	
	/**
	 * 用户刷新access_token
	 */
	private String refresh_token;
	
	private int expires_in;
	
	/**
	 * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	 */
	public String getOpenid() {
		return openid;
	}
	
	/**
	 * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	/**
	 * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((access_token == null) ? 0 : access_token.hashCode());
		result = prime * result + ((openid == null) ? 0 : openid.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SnsToken other = (SnsToken) obj;
		if (access_token == null) {
			if (other.access_token != null)
				return false;
		} else if (!access_token.equals(other.access_token))
			return false;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		return true;
	}
	
	
	
	public String toString() {
		return "SnsToken [openid=" + openid + ", scope=" + scope + ", access_token=" + access_token
				+ ", refresh_token=" + refresh_token + ", expires_in=" + expires_in + "]";
	}
	
	public static void main(String[] args) {
		String json=ReadFileUtil.getConfigFile("test/snsToken.json");
		System.out.println(json);
		SnsToken token=JsonUtil.jsonToObj(json, SnsToken.class);
		System.out.println(token);
	}
	
	
	
}
