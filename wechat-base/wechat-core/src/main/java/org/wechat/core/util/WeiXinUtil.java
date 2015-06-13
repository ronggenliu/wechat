package org.wechat.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wechat.common.util.DataUtil;
import org.wechat.common.util.JsonUtil;
import org.wechat.model.common.WechatUser;
import org.wechat.model.menu.Menu;
import org.wechat.model.token.SnsToken;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;


/**
 * @author Xie Jason 公众平台通用接口工具类
 * @date 2014年11月22日
 */
public class WeiXinUtil {

	private static final Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
	

	/**
	 * 创建菜单.
	 * @param menu   菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;
		// 拼装创建菜单的url
		String url = ConstantUrl.menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = new Gson().toJson(menu).toString();
		// 调用接口创建菜单
		JsonObject jsonObject = AccessTokenUtil.httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.get("errcode").getAsInt()) {
				result = jsonObject.get("errcode").getAsInt();
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.get("errcode"),
						jsonObject.get("errmsg"));
			}
		}

		return result;
	}

	/**
	 * 删除菜单
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int deleteMenu(String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = ConstantUrl.menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		// 调用接口创建菜单
		JsonObject jsonObject = AccessTokenUtil.httpRequest(url, "GET", null);

		if (null != jsonObject) {
			if (0 != jsonObject.get("errcode").getAsInt()) {
				result = jsonObject.get("errcode").getAsInt();
				log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.get("errcode"),
						jsonObject.get("errmsg"));
			}
		}

		return result;
	}

	
	/**
	 * 
	 * @author xiejs
	 * @date 2015年4月30日
	 * @param openId:用户的唯一标识
	 * @param token:调用接口凭证
	 * @param lang 语言：可选，默认为中文
	 * @return WechatUser :微信返回的用户信息，异常则返回null
	 */
	public static WechatUser getWechatUserInfo(String openId,String token,String lang){
		WechatUser userInfo=null;
		String url = ConstantUrl.get_userInfo_unionId.replace("ACCESS_TOKEN", token).replace("OPENID", openId);
		if(DataUtil.isValidStr(lang)&&!lang.equals("zh_CN")){
			url=url.replace("zh_CN", lang);
		}
		// 调用接口
		JsonObject jsonObject = AccessTokenUtil.httpRequest(url, "GET", null);
		try {
			if (null != jsonObject && jsonObject.has("openid")) {
				userInfo=JsonUtil.jsonToObj(jsonObject.toString(), WechatUser.class);
			}
		} catch (JsonIOException e) {
			e.printStackTrace();
			return null;
		}
		return userInfo;
	}
	
	/**
	 * 
	 * @author xiejs
	 * 2015年4月30日
	 * @param openId:用户的唯一标识
	 * @param token:网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * @param lang 语言：可选，默认为中文
	 * @return WechatUser :微信返回的用户信息，异常则返回null
	 */
	public static WechatUser getWechatUserInfoByPage(String openId,String token,String lang){
		WechatUser userInfo=null;
		String url = ConstantUrl.get_userInfo_url.replace("ACCESS_TOKEN", token).replace("OPENID", openId);
		if(DataUtil.isValidStr(lang)&&!lang.equals("zh_CN")){
			url=url.replace("zh_CN", lang);
		}
		// 调用接口
		JsonObject jsonObject = AccessTokenUtil.httpRequest(url, "GET", null);
		try {
			if (null != jsonObject && jsonObject.has("openid")) {
				userInfo=JsonUtil.jsonToObj(jsonObject.toString(), WechatUser.class);
			}
		} catch (JsonIOException e) {
			e.printStackTrace();
			return null;
		}
		return userInfo;
	}
	
	/**
	 * 直接通过页面返回的code来获取点击用户信息.
	 * @author xiejs
	 * @date 2015年4月30日
	 * @param code:页面通过授权后，返回的code
	 * @return WechatUser :微信返回的用户信息，异常则返回null
	 */
	public static WechatUser getWechatUserInfoByPageAuth(String code){
		WechatUser userInfo=null;
		SnsToken token=AccessTokenUtil.getSnsToken(code);
		if(null==token){
			return null;
		}
		String url = ConstantUrl.get_userInfo_url.replace("ACCESS_TOKEN", token.getAccess_token()).replace("OPENID", token.getOpenid());
		// 调用接口
		JsonObject jsonObject = AccessTokenUtil.httpRequest(url, "GET", null);
		try {
			if (null != jsonObject && jsonObject.has("openid")) {
				userInfo=JsonUtil.jsonToObj(jsonObject.toString(), WechatUser.class);
			}
		} catch (JsonIOException e) {
			e.printStackTrace();
			return null;
		}
		return userInfo;
	}
	
	
}
