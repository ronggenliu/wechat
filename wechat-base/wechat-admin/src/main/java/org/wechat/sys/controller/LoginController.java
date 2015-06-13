package org.wechat.sys.controller;
//package org.wechat.webapp.sys.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.wechat.common.util.DataUtil;
//import org.wechat.common.util.DateUtil;
//import org.wechat.webapp.sys.common.SysInfoCache;
//import org.wechat.webapp.sys.model.RolePrivGroup;
//import org.wechat.webapp.sys.model.UserInfo;
//import org.wechat.webapp.util.CommonUtil;
//import org.wechat.webapp.util.CookieUtil;
//
///**
// * 服务：登录注销
// * 
// * @author xiejason
// * @date 2015年4月9日
// */
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//	private static Logger log = LoggerFactory.getLogger(LoginController.class);
//	
//	@Autowired
//	IUserInfoSV userInfoSV;
//	
//
//	/**
//	 * 登陆验证
//	 */
//	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
//	public @ResponseBody void getuserInfo(@RequestBody UserInfo user, HttpServletResponse response, HttpServletRequest request) {
//		String res = "Y";
//		String mes = "登陆成功！";
//		
//		try {
//
////			if (!user.getCheckCode().equalsIgnoreCase(CommonConst.imgCode.get(sessionId))) {
////				res = "N";
////				mes = "验证码错误！";
////				return;
////			}
////			else{
////				CommonConst.imgCode.remove(sessionId);//移除验证码缓存
////			}
//			// System.out.println(sysUserSV);
//			UserInfo checkUser = getUsers(user);
//			// 判断用户名和密码是否正确，如果不正确则返回提示消息
//			if (checkUser !=null) {
//				
////				将用户的详细信息存入session
//				if (checkUser.getRoleType() == 1) {//编辑者登陆
//					EditorInfo editor=editorInfoSV.fetch(checkUser.getCmpyId());
//					checkUser.setEditor(editor);
//				} else if (checkUser.getRoleType() == 2) {//企业用户登陆
//					CompanyInfo cmpy=companyInfoSV.fetch(checkUser.getCmpyId());
//					checkUser.setCmpy(cmpy);
//				}
//				
//				for(RolePrivGroup role:SysInfoCache.rolePrivGroup){//给登陆用户添加用户权限别名，方便程序判断
//					if(checkUser.getRoleType().intValue()==role.getGroupNo().intValue()){
//						checkUser.setGroupAliasName(role.getGroupAliasName());
//					}
//				}
//				
//				HttpSession session = request.getSession();
//				String sessionId = request.getSession().getId();// 让JSESSIONID的值成为session
//				checkUser.setSessionId(sessionId);
//				session.setAttribute(sessionId, checkUser);// 添加session，存储sysUser
//				
//
//				dealWithCookie(user, response, request);
//				log.info("登陆者：" + checkUser.getUserName() +"-----"+ DateUtil.getNowDateTime());
//				// mes = users.get(0).getUserPrivGroup();
//			} else {
//				res = "N";
//				mes = "亲，用户名或密码错误,或者账号类型选择错误！";
//			}
//		} catch (Exception e) {
//			res = "N";
//			mes = "不好意思！登陆系统异常，请联系系统管理员！";
//		} finally {
//			CommonUtil.sendTip(res, mes, response);// 返回提示信息
//
//		}
//
//	}
//
//	/**
//	 * 
//	 * 更加用户名和密码获取用户信息
//	 * 
//	 * @auther xiejason
//	 * @param username
//	 * @param userPwd
//	 */
//	public UserInfo getUsers(UserInfo user) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (user.getUserName().contains(".com") && user.getUserName().contains("@")) {
//			map.put("email", DataUtil.getStr(user.getUserName()));
//		}
//		map.put("userName", DataUtil.getStr(user.getUserName()));
//		map.put("roleType", user.getRoleType());
//		map.put("password", DataUtil.getStr(user.getPassword()));
//		return userInfoSV.checkLogin(map);
//	}
//
//	@RequestMapping(value = "/logout", method = RequestMethod.POST)
//	public @ResponseBody void logout(HttpServletResponse response, HttpServletRequest request) {
//
//		HttpSession session = request.getSession();
//		UserInfo user = (UserInfo) session.getAttribute(session.getId());
//		if(user!=null){
//			session.removeAttribute(session.getId());// 清除session
//			// session.invalidate();
//			// CommonConst.imgCode.remove(JSESSIONID);//去掉验证码缓存
//			// SysInfoCache.menuInfo.remove(JSESSIONID);//清除权限菜单
//			log.debug(user.getUserName() + " 退出-----"+ DateUtil.getNowDateTime());
//			CommonUtil.sendTip("Y", "", response);
//		}else{
//			CommonUtil.sendTip("N", "您已经处于登出状态，请重新登录！", response);
//		}
//
//	}
//
//	/**
//	 * 处理cookie数据
//	 */
//	public void dealWithCookie(UserInfo user, HttpServletResponse response, HttpServletRequest request) {
//		Cookie[] cookies = request.getCookies();
//		// CookieUtil.testCookies(cookies);
//		String isremb = user.getIsRemb();
//		String username = user.getUserName();
//		// 凡是以login为开始的，都是登陆cookie
//		Cookie cookie = CookieUtil.getCookie("login" + username, cookies);
//		// 如果没有cookie，且选择了记住账号，则说明cookie过期，或者第一次登陆，则添加cookie
//		if (cookie == null && DataUtil.isValidStr(isremb) && isremb.equals("1")) {
//			// String date=DateUtil.getToday();
//			Cookie c = new Cookie("login" + username, username);
//			c.setMaxAge(60 * 60 * 24 * 365 * 5);// 记住5年
//			c.setPath("/");
//			response.addCookie(c);
//			log.debug("添加cookie");
//		}
//		// 如果为0，则说明用户取消记住账号
//		if (DataUtil.isValidStr(isremb) && isremb.equals("0")) {
//			CookieUtil.deleteCookie("login" + username, cookies, response);
//			log.debug("删除cookie");
//		}
//	}
//
//}
