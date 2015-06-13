package org.wechat.core.util;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) {
		Map<String,String> test=new HashMap<String,String>();
		test.put("1", "123");
		test.put("1", "2342");
		System.out.println(test);
	}

}
