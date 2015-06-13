package org.wechat.core.util;

/**
 * @author jasonXie
 * @date 2015年5月6日
 */
public class ConstantUrl {

	/** GET 方式创建菜单*/
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/** GET 方式删除菜单 */
	public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/** GET 方式查询菜单*/
	public static String menu_query_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	 /** 获取access_token的接口地址（GET） 限2000（次/天）  */
    public  static  String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
      
    /** GET 方式获取网页授权方式 */
 	public static String get_sns_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
 
 	/** GET 方式获取用户信息，网页授权方式 */
 	public static String get_userInfo_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    
 	/** GET 方式获取用户信息（包括UnionID机制） */
 	public static String get_userInfo_unionId="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
 	
 	
 	
}
