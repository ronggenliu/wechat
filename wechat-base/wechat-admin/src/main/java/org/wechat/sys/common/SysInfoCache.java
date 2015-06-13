package org.wechat.sys.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wechat.sys.menu.Menu;
import org.wechat.sys.model.Dict;
import org.wechat.sys.model.RolePrivGroup;

/**
 * 
 * 系统缓存数据.
 * @author xiejiesheng
 * 2015年4月21日
 */
public class SysInfoCache {


	/**
	 * 登录菜单缓存，根据角色的不同而缓存.
	 */
	public static final Map<String, List<Menu>> groupMenuCache=new HashMap<String, List<Menu>>();//登录菜单缓存
	
	public static String comOpBtn=null;//页面数据操作按钮
	
	
	public static Map<Integer,String> userPrivMapCache=new HashMap<Integer,String>();
	
	/**
	 * 菜单信息.
	 */
	public static List<Menu> menus=new ArrayList<Menu>();
	
	
	/**
	 * 用户权限组信息.
	 */
	public static final List<RolePrivGroup> rolePrivGroup=new ArrayList<RolePrivGroup>();//用户权限组信息
	
	/** 
	 * 字典数据缓存.
	 * 其结构为，一个字典类型对应多个字典数据值/对。
	 */
	public static final Map<String, Map<String, Dict>> dictCache =
			new HashMap<String, Map<String, Dict>>();
	

}
