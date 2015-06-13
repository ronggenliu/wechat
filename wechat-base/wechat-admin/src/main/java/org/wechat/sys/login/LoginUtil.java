package org.wechat.sys.login;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wechat.common.util.ReadFileUtil;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LoginUtil {
	public static List<LoginFree> freePathHolder=new ArrayList<LoginFree>();
	private static Logger log = LoggerFactory.getLogger(LoginUtil.class);
	
	public static void LoadFreeDoc(int isInit){
		if(isInit==0){
			freePathHolder.clear();
			log.info("初始化freePath……");
		}
		if(freePathHolder.size()>0){
			return;
		}
		else{
			String content = ReadFileUtil.getConfigFile("login/loginFree.json");
			content = content.replace("\r\n", "").replace("\t", "");
			JsonObject jo = new JsonParser().parse(content).getAsJsonObject();
			JsonArray freeArr=jo.get("freePath").getAsJsonArray();
//			System.out.println(freeArr.size());
//			System.out.println(freeArr.get(0).isJsonObject());
			for(int i=0;i<freeArr.size();i++){
				JsonObject obj =freeArr.get(i).getAsJsonObject();
				LoginFree free=new LoginFree();
				free.setCheckMethod(obj.get("checkMethod").getAsString());
				free.setFreeUrl(obj.get("freeUrl").getAsString());
				freePathHolder.add(free);
			}
//			log.info("重新加载freePath……");
		}
//		System.out.println(freePathHolder);
	}
	
	public static boolean isFreePath(String url){
		boolean isfree=false;
		LoadFreeDoc(1);
//		System.out.println(freePathHolder);
		for(int i=0;i<freePathHolder.size();i++){
			LoginFree free=freePathHolder.get(i);
			if(free.getCheckMethod().equals("endsWith")){
				if(url.endsWith(free.getFreeUrl())){
					isfree=true;
					return isfree;
				}
			}
			else if(free.getCheckMethod().equals("equals")){
				if(url.equals(free.getFreeUrl())){
					isfree=true;
					return isfree;
				}
			}
			else if(free.getCheckMethod().equals("contains")){
				if(url.contains(free.getFreeUrl())){
					isfree=true;
					return isfree;
				}
			}
		}
		
		return isfree;
	}
	
//	public static boolean isFreeLogin(LoginFree free){
//		boolean isfree=true;
//		
//		return isfree;
//	}
	
	public static void main(String[] args) {
		
	}

}
