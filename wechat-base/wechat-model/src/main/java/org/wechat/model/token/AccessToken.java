package org.wechat.model.token;

public class AccessToken {  
    // 获取到的凭证  
    private String token;  
    // 凭证有效时间，单位：秒  
    private int expiresIn;
    
    private Long getTokenTime; 
  
    
    public Long getGetTokenTime() {
		return getTokenTime;
	}

	public void setGetTokenTime(Long getTokenTime) {
		this.getTokenTime = getTokenTime;
	}

	public String getToken() {  
        return token;  
    }  
  
    public void setToken(String token) {  
        this.token = token;  
    }  
  
    public int getExpiresIn() {  
        return expiresIn;  
    }  
  
    public void setExpiresIn(int expiresIn) {  
        this.expiresIn = expiresIn;  
    }  
}  
