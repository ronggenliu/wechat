package org.wechat.model.menu;

import org.wechat.common.util.JsonUtil;



public class MenuManager {
	
	
	public static void main(String[] args) {
		 // 第三方用户唯一凭证  
//        String appId = "wxbbe1d020b92a8a8e";   
//        // 第三方用户唯一凭证密钥  
//        String appSecret = "11623dfa862b61ff9edc60113c963fda";  
  
        // 调用接口获取access_token  
//        AccessToken at = WeiXinUtil.getAccessToken(appId, appSecret);  
//  
//        if (null != at) {  
//        	log.debug(at.getToken());
//            // 调用接口创建菜单  
//            int result = WeiXinUtil.createMenu(getMenu(), at.getToken());  
//  
//            // 判断菜单创建结果  
//            if (0 == result)  
//                log.info("菜单创建成功！");  
//            else  
//                log.info("菜单创建失败，错误码：" + result);  
//        }
		String json=JsonUtil.toJson(getMenu());
        System.out.println(json);
//        Menu menu=JsonUtil.jsonToObj(json, Menu.class);
//        System.out.println(menu.getButton());
//        TestUtil.print(getMenu().getButton());
      
	}
	
	/** 
     * 组装菜单数据 
     *  
     * @return 
     */  
    private static Menu getMenu() {  
        CommonButton btn11 = new CommonButton();  
        btn11.setName("天气预报");  
        btn11.setType("click");  
        btn11.setKey("11");  
  
        CommonButton btn12 = new CommonButton();  
        btn12.setName("翻译");  
        btn12.setType("click");  
        btn12.setKey("12");  
  
        CommonButton btn13 = new CommonButton();  
        btn13.setName("我的主页");  
        btn13.setType("view");  
//        btn13.setKey("13");  
        btn13.setUrl("http://www.digit0902.com/weixin_jason/pages/mybookmark.html");
  
        CommonButton btn14 = new CommonButton();  
        btn14.setName("历史上的今天");  
        btn14.setType("click");  
        btn14.setKey("14");  
  
        CommonButton btn21 = new CommonButton();  
        btn21.setName("歌曲点播");  
        btn21.setType("click");  
        btn21.setKey("21");  
  
        CommonButton btn22 = new CommonButton();  
        btn22.setName("弹出微信相册");  
        btn22.setType("pic_weixin");  
        btn22.setKey("22");  
  
        CommonButton btn23 = new CommonButton();  
        btn23.setName("弹出位置");  
        btn23.setType("location_select");  
        btn23.setKey("23");  
  
        CommonButton btn24 = new CommonButton();  
        btn24.setName("人脸识别");  
        btn24.setType("click");  
        btn24.setKey("24");  
  
        CommonButton btn25 = new CommonButton();  
        btn25.setName("扫描");  
        btn25.setType("scancode_push");  
        btn25.setKey("25");  
  
        CommonButton btn31 = new CommonButton();  
        btn31.setName("弹出扫描");  
        btn31.setType("scancode_waitmsg");  
        btn31.setKey("31");  
  
        CommonButton btn32 = new CommonButton();  
        btn32.setName("拍照发图");  
        btn32.setType("pic_sysphoto");  
        btn32.setKey("32");  
  
        CommonButton btn33 = new CommonButton();  
        btn33.setName("弹出发图");  
        btn33.setType("pic_photo_or_album");  
        btn33.setKey("33");  
  
        ComplexButton mainBtn1 = new ComplexButton();  
        mainBtn1.setName("生活助手");  
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });  
  
        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("休闲驿站");  
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });  
  
        ComplexButton mainBtn3 = new ComplexButton();  
        mainBtn3.setName("更多体验");  
        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });  
  
        /** 
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
         *  
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
         */  
        Menu menu = new Menu();  
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
  
        return menu;  
    }  

}
