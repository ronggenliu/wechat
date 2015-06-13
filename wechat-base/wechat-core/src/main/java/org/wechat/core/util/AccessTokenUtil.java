package org.wechat.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wechat.common.util.DataUtil;
import org.wechat.common.util.JsonUtil;
import org.wechat.model.token.AccessToken;
import org.wechat.model.token.SnsToken;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 获取各种token的工具类
 * @author xiejs
 * @date 2015年5月6日
 */
public class AccessTokenUtil {

	private static final Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
	public static Map<String, AccessToken> atCache = new HashMap<String, AccessToken>();
	
	 /**
	  * 第三方用户唯一凭证
	  */
	 public static final String appId = "wxb1b9b3dbf703cdbd";
	 /**
	  * 第三方用户唯一凭证密钥
	  */
	 public static final String appSecret = "5e2cbad03a4e5d4a4aa1e9818c7dca1a";

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl    请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr  提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JsonObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JsonObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = new JsonParser().parse(buffer.toString()).getAsJsonObject();
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * 获取 系统默认的access_token
	 * 
	 * @return
	 */
	public static AccessToken getAccessToken() {
		AccessToken accessToken = null;
		String requestUrl = ConstantUrl.access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
		JsonObject jsonObject = httpRequest(requestUrl, "GET", null);
		// System.out.println(jsonObject);
		// 如果请求成功
		if (null != jsonObject.get("access_token")) {
			try {
				Long getTokenTime = System.currentTimeMillis();
				String at = jsonObject.get("access_token").getAsString();
				accessToken = new AccessToken();
				accessToken.setToken(at);
				accessToken.setGetTokenTime(getTokenTime);
				accessToken.setExpiresIn(jsonObject.get("expires_in").getAsInt());
			} catch (JsonIOException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.get("errcode"), jsonObject.get("errmsg"));
			}
		}
		return accessToken;
	}

	/**
	 * 获取 微信一般的access_token
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return AccessToken
	 */
	public static AccessToken getAccessToken(String appId, String appSecret) {
		AccessToken accessToken = null;
		String requestUrl = ConstantUrl.access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);

		JsonObject jsonObject = httpRequest(requestUrl, "GET", null);
		// System.out.println(jsonObject);
		// 如果请求成功
		if (null != jsonObject.get("access_token")) {
			try {
				Long getTokenTime = System.currentTimeMillis();
				String at = jsonObject.get("access_token").getAsString();
				accessToken = new AccessToken();
				accessToken.setToken(at);
				accessToken.setGetTokenTime(getTokenTime);
				accessToken.setExpiresIn(jsonObject.get("expires_in").getAsInt());
			} catch (JsonIOException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.get("errcode"), jsonObject.get("errmsg"));
			}
		}
		return accessToken;
	}

	/**
	 * 根据userid,存储access_token
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return AccessToken
	 */
	public static AccessToken getAccessToken(String userId, String appId, String appSecret) {
		AccessToken accessToken = null;

		String requestUrl = ConstantUrl.access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);

		if (DataUtil.isValidStr(userId)) {
			if (isExpired(userId)) {// 如果过期了，则重新获取

				JsonObject jsonObject = httpRequest(requestUrl, "GET", null);
				// System.out.println(jsonObject);
				// 如果请求成功
				if (null != jsonObject.get("access_token")) {
					try {
						Long getTokenTime = System.currentTimeMillis();
						String at = jsonObject.get("access_token").getAsString();
						accessToken = new AccessToken();
						accessToken.setToken(at);
						accessToken.setGetTokenTime(getTokenTime);
						accessToken.setExpiresIn(jsonObject.get("expires_in").getAsInt());
						atCache.put(userId, accessToken);
					} catch (JsonIOException e) {
						accessToken = null;
						// 获取token失败
						log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.get("errcode"), jsonObject.get("errmsg"));
					}
				}
			} else {
				// 如果没有过期，则使用全局静态变量at和getTokenTime的值
				atCache.get(userId).setExpiresIn((int) (System.currentTimeMillis() - atCache.get(userId).getGetTokenTime()) / 1000);
				accessToken = atCache.get(userId);
				log.info("距离凭证过期还有{}秒", accessToken.getExpiresIn());
			}
		} else {
			log.info("用户id，userId为空，不能准确获取数据！");
		}
		return accessToken;
	}

	/**
	 * 凭证是否过期，即取得凭证的时间和此刻相差的时间是否大于7200秒.
	 * @param userId
	 * @return boolean
	 */
	public static boolean isExpired(String userId) {
		AccessToken accessToken = atCache.get(userId);
		if (accessToken == null) {// 第一次请求
			return true;
		} else {
			return (System.currentTimeMillis() - accessToken.getGetTokenTime()) / 1000 > 7200 ? true : false;
		}
		// return flag;

	}
	
	
	/**
	 * 根据网页授权获取的code，来获取
	 * @author xiejs
	 * 2015年4月30日
	 * @param code：从网页确认授权获取的code
	 * @return SnsToken
	 */
	public static SnsToken getSnsToken(String code) {
		String url = ConstantUrl.get_sns_token_url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
		// 调用接口
		JsonObject jsonObject = AccessTokenUtil.httpRequest(url, "GET", null);
		SnsToken token = null;
		try {
			if (null != jsonObject && jsonObject.has("openid")) {
				token = new SnsToken();
				jsonObject.toString();
				token=JsonUtil.jsonToObj(jsonObject.toString(), SnsToken.class);
			} 
		} catch (JsonIOException e) {
			e.printStackTrace();
			return null;
		}
		return token;
	}

}
