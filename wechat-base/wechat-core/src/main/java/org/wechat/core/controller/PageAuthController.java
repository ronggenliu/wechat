package org.wechat.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.common.sys.CommonUtil;
import org.wechat.core.util.WeiXinUtil;
import org.wechat.model.common.WechatUser;

/**
 * @author xiejs
 * @date 2015年5月7日
 */
@RequestMapping("/pageAuth")
public class PageAuthController {

	private static Logger log = LoggerFactory.getLogger(PageAuthController.class);
	
	/**
	 * 根据网页授权获取点击用户信息.
	 * @param code：授权码
	 * @param state
	 */
	@RequestMapping(value = "/getWechatUserByCode", method = RequestMethod.GET)
	@ResponseBody
	public void getWechatUserByCode(HttpServletResponse resp, HttpServletRequest request,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "state", required = false) String state) {
		log.warn("添加企业家微信 信息----;code:" + code + ";state:" + state);
//		SnsToken oauth2AccessToken = WeiXinUtil.getSnsToken(code);
//		String fileName=GetPath.getSrcPath("test/wechatUser.json");
//		String json=ReadFileUtil.getFileContent(fileName);
//		System.out.println(json);
//		WechatUser userInfo=JsonUtil.jsonToObj(json, WechatUser.class);
		String status = "Y";
		String msg = "绑定成功！";
		try{
			WechatUser userInfo=WeiXinUtil.getWechatUserInfoByPageAuth(code);
			if (userInfo != null) {
//				wechatUserInfoSV.checkByOpenId(userInfo.getOpenid(), userInfo);//核实微信用户信息表
//				
//				WechatEnterpriser enp2=wechatEnterpriserSV.selectByOpenId(userInfo.getOpenid());
//				if(null!=enp2&&DataUtil.isValidStr(enp2.getOpenId())){
//					if(DataUtil.isValidStr(enp2.getUserName())){
//						log.info("{}已经存在！无需重复获取",enp2.getUserName());
//						status = "N";
//						msg = "您已经在微信企业家中！";
//						return;
//					}
//					else{
//						status = "Y";
//						msg = "请完善您绑定信息！";
//						return;
//					}
//				}
//				else{
//					WechatEnterpriser enp=new WechatEnterpriser();
//					enp.setNickname(userInfo.getNickname());
//					enp.setSex(userInfo.getSex());
//					enp.setOpenId(userInfo.getOpenid());
//					enp.setVersion(1L);
//					enp.setDelFlag(0);
//					enp.setCreateTime(DateUtil.getNowDateTime());
//					wechatEnterpriserSV.insert(enp);
//					msg = enp.getEnpId().toString();//返回主键编号，用户前台填写微信企业家信息时，做修改处理
//				}
				
			}
		}
		catch(Exception e){
			log.error("企业家绑定微信号出错！");
			status = "N";
			msg = "企业家绑定微信号出错！";
			e.printStackTrace();
		}
		finally{
			CommonUtil.sendTip(status, msg, resp);
		}
	}
}
