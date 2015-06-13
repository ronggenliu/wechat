package org.wechat.common.util;


import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 * @author 谢杰生
 * @datetime 2011-10-13
 */
public class EncodeUtil {

	/***
	 * MD5加码 生成32位md5码
	 */
	//不可逆的
	public final static String md5Encode(String s) {      
		  char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',      
		    'a', 'b', 'c', 'd', 'e', 'f' };      
		  try {      
			   byte[] strTemp = s.getBytes();      
			   MessageDigest mdTemp = MessageDigest.getInstance("MD5");      
			   mdTemp.update(strTemp);      
			   byte[] md = mdTemp.digest();      
			   int j = md.length;      
			   char str[] = new char[j * 2];      
			   int k = 0;      
			   for (int i = 0; i < j; i++) {      
			    byte byte0 = md[i];      
			    str[k++] = hexDigits[byte0 >>> 4 & 0xf];      
			    str[k++] = hexDigits[byte0 & 0xf];      
		   }      
			   return new String(str);      
		  } catch (Exception e) {    
			  return null;      
		  }      
		} 
	
	//可逆的
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */ 
	public static String convertMD5(String inStr){
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 测试主函数
	public static void main(String args[]) {
		String s = new String("46546");
		System.out.println("原始：" + s);
		System.out.println("不可逆：" + md5Encode(s));
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));

	}
}


