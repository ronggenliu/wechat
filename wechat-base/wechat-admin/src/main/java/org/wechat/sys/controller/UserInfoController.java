package	org.wechat.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.common.sys.CommonUtil;
import org.wechat.common.util.DataUtil;
import org.wechat.common.util.DateUtil;
import org.wechat.sys.common.SessionUtil;
import org.wechat.sys.model.UserInfo;
import org.wechat.sys.service.IUserInfoSV;

@RestController
@RequestMapping("/sys/userInfo")
public class UserInfoController {
	private static Logger log = LoggerFactory.getLogger(UserInfoController.class);   
	
	@Autowired
	IUserInfoSV userInfoSV;
	
	
	
	@RequestMapping(
			value="/selectUserInfo",
			method=RequestMethod.POST,
			name="条件分页查询用户信息"
			
			)
	public void selectObject(HttpServletRequest request,@RequestBody final String requestBody,HttpServletResponse response) {
		Map<String,Object> map=DataUtil.getRequestBody(requestBody,UserInfo.class);
		List<UserInfo> data=userInfoSV.queryPage(map);
		int total=userInfoSV.count(map);
		CommonUtil.sendJson(data, response, total);
	}
	
	

	
	@RequestMapping(value="/addUserInfo",method=RequestMethod.POST)
	public void addUserInfo(@RequestBody UserInfo userInfo,HttpServletResponse resp,HttpServletRequest request){
		String status="Y";
		String msg="添加成功！";
		
		try{
			userInfo.setOpId(SessionUtil.getUserId(request));
			userInfo.setVersion(1L);
			userInfo.setDelFlag(0);
			userInfo.setCreateTime(DateUtil.getNowDateTime());
			userInfoSV.insert(userInfo);
			log.debug("添加 成功！data="+userInfo);
			
		}
		catch (Exception e) {
			status="N";
			msg="数据异常！添加失败！";
			log.error("系统异常！添加失败！");
			e.printStackTrace();
		}
		finally{
			CommonUtil.sendTip( status,msg,resp);
		}
	}
	
	@RequestMapping(value="/updateUserInfo",method=RequestMethod.POST)
	public void updateUserInfo(@RequestBody UserInfo userInfo,HttpServletResponse resp,HttpServletRequest request){
		String status="Y";
		String msg="修改成功！";
		try{
			userInfo.setOldVersion(userInfo.getVersion());
			userInfo.setUpdateTime(DateUtil.getNowDateTime());
			userInfo.setVersion(userInfo.getVersion()+1);
			userInfo.setOpId(SessionUtil.getUserId(request));
			//修改数据
			int updateNum=userInfoSV.update(userInfo);
			if(updateNum>0){
				log.debug("修改 成功！"+userInfo);
			}
			else{
				 status="N";
				 msg="亲,修改失败！请刷新页面再试！";
			}
			
		}
		catch (Exception e) {
			 status="N";
			 msg="修改失败！";
			 log.error("系统异常！修改失败！");
			 e.printStackTrace();
		}
		finally{
			CommonUtil.sendTip( status,msg,resp);
		}
		
	}

	@RequestMapping(value="/deleteUserInfo",method=RequestMethod.POST)
	@ResponseBody
	public void deleteUserInfo(@RequestBody List<UserInfo> list, HttpServletResponse resp,HttpServletRequest request) {
		String status="Y";
		String msg="删除成功！";
		try{
			
			Long opId=SessionUtil.getUserId(request);
			for(int i=0;i<list.size();i++){
				Map<String,Object> condition=new HashMap<String, Object>();
				condition.put("id",list.get(i).getUserId());
				condition.put("opId",opId);
				condition.put("version",list.get(i).getVersion());
				condition.put("oldVersion",list.get(i).getVersion()+1);
				userInfoSV.deleteByLogicId(condition);
			}
			
		}
		catch (Exception e) {
			status="N";
			msg="数据异常，删除失败！";
			log.error("系统异常！删除失败！");
			e.printStackTrace();
		}
		finally{
			CommonUtil.sendTip( status,msg,resp);
		}
		
	}


}
