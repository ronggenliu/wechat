package org.wechat.sys.login;

public class LoginFree {
	private String freeUrl;
	private String checkMethod;
	public String getFreeUrl() {
		return freeUrl;
	}
	public void setFreeUrl(String freeUrl) {
		this.freeUrl = freeUrl;
	}
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	@Override
	public String toString() {
		return "LoginFree [getFreeUrl()=" + getFreeUrl() + ", getCheckMethod()=" + getCheckMethod() + "]";
	}
	

}
