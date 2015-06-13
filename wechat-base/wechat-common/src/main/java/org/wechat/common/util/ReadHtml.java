package org.wechat.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ReadHtml {
	public static String fileToString(File file) {

		BufferedReader reader = null;
		StringBuffer buff = new StringBuffer();
		try {
//			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;

//			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				buff.append(tempString + "/r/n");
//				 System.out.println("line " + line + ": " + tempString);
//				line++;
			}
			reader.close();
			return buff.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return null;

	}
	
	public static List<ArticleList> getCollections(File file){
		List<ArticleList> articles = new ArrayList<ArticleList>();
		String s =fileToString(file);
//		取出a元素
		String regex = "<a.*?/a>";

		Pattern pt = Pattern.compile(regex);
//		 System.out.println(regex);
		Matcher mt = pt.matcher(s);
		// String includeString=".*baidu.com.*";//必须包含 字符串"baidu.com"

		while (mt.find()) {
			if (true) {

				// System.out.println(mt.group());

				String title_s = ">[^<].*?[^>]</a>";// 标题部分
				String href_s = "href=\".*?\"";

				Pattern pt_title = Pattern.compile(title_s);
				Matcher mt_title = pt_title.matcher(mt.group());
				Pattern pt_href = Pattern.compile(href_s);
				Matcher mt_href = pt_href.matcher(mt.group());
				while (mt_title.find() && mt_href.find()) {
					String title = mt_title.group().replaceAll(">|</a>", "");
					String href = mt_href.group().replaceAll("href=|>|\"", "");
					ArticleList article = new ArticleList(title, href);
					articles.add(article);
				}

			}

		}
		return articles;
	}
	

}
/**
 *定义一个文章列表类。包含文章的网址和文章标题
 */
class ArticleList {
	String URLs;
	String title;
	
	public ArticleList(){}
	public ArticleList(String t,String u)
	{
		title=t;
		URLs=u;
		
	}
	
	public String toString()
	{
		return ("标题："+title+"\t 网址："+URLs);
		
	}
	
}
