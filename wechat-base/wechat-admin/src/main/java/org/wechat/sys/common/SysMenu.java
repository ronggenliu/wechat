package org.wechat.sys.common;


import java.util.ArrayList;
import java.util.List;

import org.wechat.common.util.DataUtil;
import org.wechat.common.util.ReadFileUtil;
import org.wechat.sys.menu.Button;
import org.wechat.sys.menu.Menu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



/**
 * 服务：获取系统菜单
 * @author xiejason
 * @date 2015年4月9日
 */
public class SysMenu {
	
	/**
	 * 获取权限组系统菜单
	 * @param menus 菜单容器
	 * @param groupName，权限组名，对应系统菜单文件名称
	 */
	public static void getUserPriv(List<Menu> menus,String groupName){
		
		String content = ReadFileUtil.getConfigFile("html/"+groupName+".json");
		content = content.replace("\r\n", "").replace("\t", "");
		JsonObject jo = new JsonParser().parse(content).getAsJsonObject();
		JsonArray btnArr=jo.get("menu").getAsJsonArray();
		menuInfoFilled( menus,btnArr);
	}
	
	/**
	 * 获取权限组系统菜单，过滤掉比priv值小的菜单
	 * @param menus
	 * @param priv
	 */
	public static void getUserPriv(List<Menu> menus,Integer priv,String groupName){

		String content = ReadFileUtil.getConfigFile("html/"+groupName+".json");
		content = content.replace("\r\n", "").replace("\t", "");
		JsonObject jo = new JsonParser().parse(content).getAsJsonObject();
		JsonArray btnArr=jo.get("menu").getAsJsonArray();
		menuInfoFilled( menus,btnArr);
		filterUserPriv(menus, priv);
	}
	
	
	
	
	
	/**
	 *  去掉或者保留指定的菜单或者菜单组
	 * @param menus :菜单集合
	 * @param menuId: 要过滤的菜单id
	 * @param reOrSeve 如果是0:移除菜单为menuId的菜单，1：只保留菜单为menuId的菜单
	 */
	public static void filterUserPriv(List<Menu> menus,String menuId,int reOrSave){
		if(reOrSave==0){
			for(int i=0;i<menus.size();i++){
				if(null!=menus.get(i).getParent()){
					if(DataUtil.isValidStr(menuId)){//如果菜单组id为null，则不用过滤
						if(menus.get(i).getParent().getMenuId().equals(menuId)){
							menus.remove(i);
							i=i-1;
						}
					}
				}
			}
		}
		else if(reOrSave==1){
			for(int i=0;i<menus.size();i++){
				if(null!=menus.get(i).getParent()){
					if(DataUtil.isValidStr(menuId)){//如果菜单组id为null，则说明要移除所有菜单组，只保留一级菜单
						if(!menus.get(i).getParent().getMenuId().equals(menuId)){
							menus.remove(i);
							i=i-1;
						}
					}
					else{
						menus.remove(i);
						i=i-1;
					}
				}
			}
		}
	}
	
	/**
	 * 去掉比priv小，即权限级别比priv高的菜单项
	 */
	public static void filterUserPriv(List<Menu> menus,Integer priv){
		for(int i=0;i<menus.size();i++){
			if(menus.get(i).getPriv()<priv){
				menus.remove(i);
				i=i-1;
			}
		}
	}
	/**
	 * 去掉有menu为null的值，在迭代中出现的
	 */
	public static void filterListMenu(List<Menu> menus){
		for(int i=0;i<menus.size();i++){
			if(null==menus.get(i).getName()){
				menus.remove(i);
				i=i-1;
			}
		}
		
	}
	/**
	 * 迭代获取所有菜单
	 */
	public static void menuInfoFilled(List<Menu> menus,JsonArray btnArr){
		for(int i=0;i<btnArr.size();i++){
			JsonObject obj=btnArr.get(i).getAsJsonObject();
        	Object isGroup=obj.get("groupMenu");
//        	System.out.println(isGroup);
        	Menu menu=new Menu();
        	if(null!=isGroup){
        		Button btn=new Button();
        		btn.setName(obj.get("name").getAsString());
        		btn.setMenuId(obj.get("menuId").getAsString());
        		btn.setClassStyle(obj.get("classStyle").getAsString());
        		menu.setParent(btn);
        		menus.add(menu);
//        		System.out.println(cb);
        		JsonArray subArr=btnArr.get(i).getAsJsonObject().get("groupMenu").getAsJsonArray();
        		menuInfoFilled(menus, subArr);
        	}
        	else{
        		int level=obj.get("level").getAsInt();
        		if(menus.size()>0){
        			Button btn=menus.get(menus.size()-1).getParent();
        			if(null!=btn&&level!=1){
        				menu.setParent(btn);
//        				menus.remove(menus.size()-1);
        			}
        			else{
        				menu.setParent(null);
        			}
        		}
        		menu.setLevel(level);
        		menu.setMenuId(obj.get("menuId").getAsString());
        		menu.setName(obj.get("name").getAsString());
        		menu.setUrl(obj.get("url").getAsString());
        		menu.setPriv(obj.get("priv").getAsInt());
        		menu.setClassStyle(obj.get("classStyle").getAsString());
        		menus.add(menu);
//        		System.out.println(btnArr.get(i));
        	}
        	filterListMenu( menus);
        }
    	
	}
	
	public static void main(String[] args) {
		List<Menu> menus=new ArrayList<Menu>();
		getUserPriv(menus,"companyGroup");
//		filterUserPriv(menus, "systemResGroup", 0);
		DataUtil.print(menus);
	}

}
