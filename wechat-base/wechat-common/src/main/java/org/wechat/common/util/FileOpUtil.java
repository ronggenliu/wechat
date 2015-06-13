package org.wechat.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 纯Java文件操作工具，支持文件、文件夹的复制、删除、移动操作。
 * 
 * @author Xie Jason
 * 
 * @date 2014年11月24日
 */
public class FileOpUtil {

	private static final Logger log = LoggerFactory.getLogger(FileOpUtil.class);

	public FileOpUtil() {
	}

	public static void main(String args[]) throws IOException {
		// delete(new File("C:/aaa"));
		// copy(new File("E:/pics/1.jpg"), new File("E:"));
		// move(new File("E:/pics/1.jpg"), new File("E:"));

	}

	/**
	 * 删除文件（夹）
	 * 
	 * @param file
	 *            文件（夹）
	 */
	public static void delete(File file) {
		if (!file.exists()) {
			log.info("要删除的文件（{}）不存在！", file.getPath());
			return;
		}

		if (file.isFile()) {
			file.delete();
		} else {
			for (File f : file.listFiles()) {
				delete(f);
			}
			file.delete();
			log.info(file.getPath() + " 文件删除成功！");
		}
	}

	

	/**
	 * 复制文件（夹）到一个目标文件夹
	 * 
	 * @param resFile
	 *            源文件（夹）
	 * @param objFolderFile
	 *            目标文件夹
 *      @param fileName
 *            目标文件名
	 * @throws IOException
	 *             异常时抛出
	 */
	public static void copy(File resFile, File objFolderFile,String fileName) throws IOException {
		if (!resFile.exists()) {
			log.info("要拷贝的源文件（{}）不存在！", resFile.getPath());
			return;
		}
		if (!objFolderFile.exists())
			objFolderFile.mkdirs();
		// 如果resFile是文件，则当中文件来拷贝，如果是文件夹，则当作文件夹拷贝
		if (resFile.isFile()) {
			File objFile=null;
			if(null==fileName||"".equals(fileName)){
				 objFile = new File(objFolderFile.getPath() + File.separator + resFile.getName());
			}
			else{
				objFile = new File(objFolderFile.getPath() + File.separator + fileName);
			}
			// 复制文件到目标地
			InputStream ins = new FileInputStream(resFile);
			FileOutputStream outs = new FileOutputStream(objFile);
			byte[] buffer = new byte[1024 * 512];
			int length;
			while ((length = ins.read(buffer)) != -1) {
				outs.write(buffer, 0, length);
			}
			ins.close();
			outs.flush();
			outs.close();
		} 
		else {
			String objFolder = objFolderFile.getPath() + File.separator + resFile.getName();
			File _objFolderFile = new File(objFolder);
			_objFolderFile.mkdirs();
			for (File sf : resFile.listFiles()) {
				copy(sf, new File(objFolder),null);
			}
		}
		log.info("文件{} 拷贝到 {} 文件夹下面", resFile.getPath(), objFolderFile.getPath());
	}

	/**
	 * 将文件（夹）移动到令一个文件夹
	 * 
	 * @param resFile
	 *            源文件（夹）
	 * @param objFolderFile
	 *            目标文件夹
	 * @throws IOException
	 *             异常时抛出
	 */
	public static void move(File resFile, File objFolderFile,String fileName) throws IOException {
		copy(resFile, objFolderFile,fileName);
		delete(resFile);
	}
}
