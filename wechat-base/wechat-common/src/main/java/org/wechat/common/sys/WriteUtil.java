package org.wechat.common.sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wechat.common.util.DataUtil;



public class WriteUtil {
	private static Logger log = LoggerFactory.getLogger(WriteUtil.class);
	
	/**
	 * @param url:文件所在的文件夹路径
	 * @param content:文件内容
	 * @param fileName:文件名称
	 *
	 */
	public static void writeFile(String url,String content,String fileName) {
		File file=null;
		
		File filePath = new File(url);
		FileWriter fw = null;
		try {
			file = new File(url, fileName);
			if (!filePath.exists()) {
				filePath.mkdirs();
				if (!file.exists() && filePath.mkdirs()) {
					file.createNewFile();
				}
			}
			fw = new FileWriter(file);

			fw.write(content);
			fw.flush();
			log.debug("写入成功！file=" + fileName);
		} catch (IOException e) {
			log.debug("写入失败！file=" + fileName);
			e.printStackTrace();
		} finally {
			try {
				fw.close();
				log.debug("写入 的IO关闭成功！file=" +fileName );
			} catch (IOException e) {
				log.error("写入的IO关闭失败！file= " +fileName );
				e.printStackTrace();
			}
		}
	}
	
	/**
     * 下载文件
     * @param fileFullName 文件所在目录
     * @param fileName 下载时显示的文件名称
     * @param response
     */
    public static boolean sendLoadDownFile(String fileFullName,String fileName, HttpServletResponse response) {
        if (fileFullName == null || "".equals(fileFullName)) {
            return false;
        }
        File file=new File(fileFullName);
        if(!file.exists()){
            return false;
        }
        ServletOutputStream os=null;
        FileInputStream fis=null;
        try {
//            HttpServletResponse response=ServletActionContext.getResponse();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename="+DataUtil.urlEncodeUTF8(fileName));
            response.addHeader("Content-Length", file.length()+"");
            response.setContentLength(((Long)file.length()).intValue());
            
             os=response.getOutputStream();
             fis=new FileInputStream(file);
            
            int size=0;
            byte[] buffer=new byte[4096];
            while((size=fis.read(buffer))!=-1){
                os.write(buffer,0,size);
            }
            log.debug("下载文件，数据发送成功……fiie={}",fileFullName);
            return true;
            
        } catch (IOException e) {
        	log.error("下载文件，数据发送异常……fiie={}",fileFullName);
            e.printStackTrace();
        }
        finally{
        	try {
				os.flush();
				os.close();
				fis.close();
				log.debug("操作文件流关闭成功……");
			} catch (IOException e) {
				log.error("操作文件流关闭失败……");
				e.printStackTrace();
			}
        	
        }
        return false;
    }
	
	

}
