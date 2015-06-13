package org.wechat.common.sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wechat.common.util.JsonUtil;

public class CommonUtil {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	private static CommonUtil pu;

	private CommonUtil() {

	}

	public static CommonUtil getInstacne() {
		if (pu == null) {
			pu = new CommonUtil();
			return pu;
		}
		return pu;
	}

	/**
	 * 获取链接内容，一般用于调用接口内容
	 * 
	 * @param urlRequest
	 * @return
	 */
	public static String httpRequest(String urlRequest) {
		StringBuffer buf = new StringBuffer();
		try {
			// 建立连接
			URL url = new URL(urlRequest);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setRequestMethod("GET");
			con.connect();

			// 获取输入流
			InputStream input = con.getInputStream();
			InputStreamReader reader = new InputStreamReader(input, "utf-8");
			BufferedReader bufRead = new BufferedReader(reader);

			// 读取返回结果
			String str = null;
			while ((str = bufRead.readLine()) != null) {
				buf.append(str);
			}

			// 关闭流和连接
			bufRead.close();
			reader.close();
			input.close();
			input = null;
			con.disconnect();

		} catch (java.lang.NullPointerException nullEx) {
			log.error("请求异常！请重新再试！");
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;

		}
		return buf.toString();
	}

	/**
	 * 将数据封装成json发送到前段页面 obj ,resp为必填 total为选填
	 */
	public static void sendJson(Object obj, HttpServletResponse resp, int total) {
		String json = null;
		// total为0，就意味着只是返回提示信息，如果不为0，则返回相关数据信息
		if (total == 0) {
			json = JsonUtil.toJson(obj);
		} else {
			json = "{\"total\":" + total + " , \"rows\":" + JsonUtil.toJson(obj) + "}";
		}
		if (json.length() > 100) {
			log.debug("ajax发送json数据：" + json.subSequence(0, 98) + "……");
		} else {

			log.debug("ajax发送json数据：" + json + "……");
		}
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			log.error("数据发送失败!");
			e.printStackTrace();
		} finally {
			out.println(json);
			out.flush();
			out.close();
		}
	}


	/**
	 * 根据对数据的操作，而选择发送的内容
	 * 
	 * @throws Exception
	 */
	public static void sendTip(String status, String msg, HttpServletResponse resp) {
		PageTip pageTip = PageTip.getInstance();
		pageTip.setStatus(status);
		pageTip.setMsg(msg);
		sendJson(pageTip, resp, 0);
	}

	static class PageTip {
		private String status;
		private String msg;
		private String result;

		public static PageTip instance = null;

		private PageTip() {
		}

		private static synchronized void syncInit() {
			if (instance == null) {
				instance = new PageTip();
			}
		}

		public static PageTip getInstance() {
			if (instance == null) {
				syncInit();
			}
			return instance;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

}
