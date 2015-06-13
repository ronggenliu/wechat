package org.wechat.core.util;

import java.util.ArrayList;
import java.util.List;

import org.wechat.common.util.JsonUtil;
import org.wechat.common.util.ReadFileUtil;
import org.wechat.model.menu.Button;
import org.wechat.model.menu.CommonButton;
import org.wechat.model.menu.ComplexButton;
import org.wechat.model.menu.Menu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MenuUtil {

	public static void main(String[] args) {
//		System.out.println(getBtnArr().size());
//		CommonButton btn = new CommonButton();
//		btn.setKey("123");
//		btn.setName("btn");
//		btn.setType("fdasf");
//		btn.setUrl("1231");
		String json = JsonUtil.toJson(getMenu());
		System.out.println(json);
//		CommonButton btn2 = JsonUtil.jsonToObj(json, CommonButton.class);
//		System.out.println(btn2);
//		getMenu();
	}

	public static Menu getMenu() {
		JsonArray jArr = getBtnArr();
//		Map<String, List<CommonButton>> menus = new HashMap<String, List<CommonButton>>();
		List<Button> btns = new ArrayList<Button>();
		String key = null;
		for (int i = 0; i < jArr.size(); i++) {
			JsonObject jo = jArr.get(i).getAsJsonObject();
			List<CommonButton> cbtns = new ArrayList<CommonButton>();
			key = getCommonBtn(cbtns, jo);
			if (null != key) {
				ComplexButton mainBtn = new ComplexButton();
				mainBtn.setName(key);
				CommonButton[] cBtnArr=new CommonButton[cbtns.size()];
				mainBtn.setSub_button((CommonButton[]) cbtns.toArray(cBtnArr));
				btns.add(mainBtn);
			} else {
				btns.add(cbtns.get(0));
			}
		}
		

		Button[] cBtnArr=new Button[btns.size()];
		Menu menu = new Menu();
		menu.setButton((Button[]) btns.toArray(cBtnArr));
		
//		String json=JsonUtil.toJson(menu);
//        System.out.println(json);
		return menu;  

	}

	public static String getCommonBtn(List<CommonButton> btns, JsonObject jo) {
		Object isGroup = jo.get("sub_button");
		if (isGroup != null) {
			JsonArray jArr = jo.getAsJsonArray("sub_button");
//			System.out.println(jArr.size());
			for (int i = 0; i < jArr.size(); i++) {
				JsonObject obj = jArr.get(i).getAsJsonObject();
//				System.out.println(obj.toString());
				CommonButton btn = JsonUtil.jsonToObj(obj.toString(), CommonButton.class);
				btns.add(btn);
			}
			return jo.get("name").getAsString();
		} else {
			CommonButton btn = JsonUtil.jsonToObj(jo.toString(), CommonButton.class);
			btns.add(btn);
			return null;
		}

	}

	public static JsonArray getBtnArr() {

		String content = ReadFileUtil.getConfigFile("wechat/menu.json");
		content = content.replace("\r\n", "").replace("\t", "");
		JsonObject jo = new JsonParser().parse(content).getAsJsonObject();
		return jo.get("button").getAsJsonArray();
		// menuInfoFilled( menus,btnArr);
		// filterListMenu(menus);
		// filterUserPriv(menus, priv);
	}

}
