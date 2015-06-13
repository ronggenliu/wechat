package org.wechat.sys.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.wechat.common.sys.CommonUtil;
import org.wechat.common.sys.DocItem;
import org.wechat.common.sys.WriteUtil;
import org.wechat.common.util.PathUtil;
import org.wechat.common.util.ReadFileUtil;
import org.wechat.sys.common.SessionUtil;
import org.wechat.sys.common.SysInfoCache;
import org.wechat.sys.common.SysMenu;
import org.wechat.sys.login.LoginUtil;
import org.wechat.sys.menu.Menu;
import org.wechat.sys.model.UserInfo;
import org.wechat.sys.util.ControllerUtil;

@RestController
@RequestMapping("/sys/init")
public class InitController {

	private static Logger log = LoggerFactory.getLogger(InitController.class);

	/**
	 * 初始化每个页面的菜单.
	 */
	@RequestMapping(value = "/getMenu", method = RequestMethod.POST)
	public  void getMenu(HttpServletResponse response, HttpServletRequest request) {

		UserInfo user = (UserInfo) SessionUtil.getSession(request);
		if (null != user) {

			List<Menu> menus = SysInfoCache.groupMenuCache.get(user.getGroupAliasName());
			// DataUtil.print(menus);
			CommonUtil.sendJson(menus, response, 0);
		} else {
			CommonUtil.sendTip("N", "获取菜单异常！请联系系统管理员！", response);// 返回提示信息
		}
	}

	/**
	 * 初始化每个页面的菜单.
	 */
	@RequestMapping(value = "/getDoc", method = RequestMethod.POST)
	
	public void getDoc(HttpServletResponse response, HttpServletRequest request) {

		List<DocItem> docs=ControllerUtil.getAllDocItem();
		CommonUtil.sendJson(docs, response, 0);
	}

	/**
	 * 以下的文件操作都属于全局操作，一旦update将影响整个系统
	 */
	// ------菜单文件的操作-------------------------------------------
	@RequestMapping(value = "/showTextMenu", method = RequestMethod.POST)
	public  void showTextMenu(String groupAliasName, HttpServletResponse response) {
		String content = ReadFileUtil.getConfigFile("html/" + groupAliasName + ".json");
		CommonUtil.sendJson(content, response, 0);
	}

	@RequestMapping(value = "/getTextMenu", method = RequestMethod.GET)
	public  void getTextMenu(String groupAliasName, HttpServletResponse response) {
		// String content = ReadFileUtil.getConfigFile("html/menu.json");
		String fileName = "html/" + groupAliasName + ".json";
		String fileFullName = PathUtil.getRealClassPath() + fileName;
		WriteUtil.sendLoadDownFile(fileFullName, groupAliasName + ".json", response);
	}

	@RequestMapping(value = "/updateTextMenu", method = RequestMethod.POST)
	public  void updateTextMenu(MultipartFile file, HttpServletRequest request, HttpServletResponse resp) throws IOException {
		if (file == null) {
			log.info("数据为空，菜单文件上传失败！");
			return;
		} else {
			String groupAliasName = file.getOriginalFilename().split("\\.")[0];
			String url = PathUtil.getRealClassPath() + "html";
			String originName = groupAliasName + ".json";
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(url, originName));

			// 重置系统菜单缓存，先干掉，在重新加载
			SysInfoCache.groupMenuCache.remove(groupAliasName);
			List<Menu> menus = new ArrayList<Menu>();
			SysMenu.getUserPriv(menus, groupAliasName);
			SysInfoCache.groupMenuCache.put(groupAliasName, menus);
			log.debug("菜单文件上传成功！ 重置菜单缓存……");
			// if(DataUtil.isValidStr(groupAliasName)){
			// }
		}
	}

	// ------菜单文件的操作-------------------------------------------
	@RequestMapping(value = "/showTextOpBtn", method = RequestMethod.POST)
	public  void showTextOpBtn(HttpServletResponse response) {
		if (SysInfoCache.comOpBtn == null) {
			SysInfoCache.comOpBtn = ReadFileUtil.getConfigFile("html/opBtn.txt");
		}
		CommonUtil.sendJson(SysInfoCache.comOpBtn, response, 0);
	}

	@RequestMapping(value = "/getTextOpBtn", method = RequestMethod.GET)
	public  void getTextOpBtn(HttpServletResponse response) {
		// String content = ReadFileUtil.getConfigFile("html/menu.json");
		String fileName = "html/opBtn.txt";
		String fileFullName = PathUtil.getRealClassPath() + fileName;
		WriteUtil.sendLoadDownFile(fileFullName, "opBtn.txt", response);
	}

	@RequestMapping(value = "/updateTextOpBtn", method = RequestMethod.POST)
	public  void updateTextOpBtn(MultipartFile file, HttpServletRequest request, HttpServletResponse resp) throws IOException {
		if (file == null) {
			log.info("数据为空，菜单文件上传失败！");
			return;
		} else {
			String url = PathUtil.getRealClassPath() + "html";
			String originName = "opBtn.txt";
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(url, originName));
			// SysInfoCache.menuInfo.clear();
			SysInfoCache.comOpBtn = ReadFileUtil.getConfigFile("html/opBtn.txt");
			log.debug("操作按钮文件上传成功！ 重置操作按钮缓存……");
		}
	}

	// ------登录文件的操作-------------------------------------------
	@RequestMapping(value = "/showTextLogin", method = RequestMethod.POST)
	public  void showTextLogin(HttpServletResponse response) {
		String content = ReadFileUtil.getConfigFile("login/loginFree.json");
		CommonUtil.sendJson(content, response, 0);
	}

	@RequestMapping(value = "/getTextLogin", method = RequestMethod.GET)
	public  void getTextLogin(HttpServletResponse response) {
		String fileName = "login/loginFree.json";
		String fileFullName = PathUtil.getRealClassPath() + fileName;
		WriteUtil.sendLoadDownFile(fileFullName, "loginFree.json", response);
	}

	@RequestMapping(value = "/updateTextLogin", method = RequestMethod.POST)
	public  void updateTextLogin(MultipartFile file, HttpServletRequest request, HttpServletResponse resp) throws IOException {
		if (file == null) {
			log.info("数据为空，登陆权限文件上传失败！");
			return;
		} else {
			String url = PathUtil.getRealClassPath() + "login";
			String originName = "loginFree.json";
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(url, originName));
			LoginUtil.LoadFreeDoc(0);
			log.debug("登陆权限文件上传成功！ 登陆权限缓存……");
		}
	}

}
