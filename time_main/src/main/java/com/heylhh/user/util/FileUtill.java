package com.heylhh.user.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件帮助类
 *
 */
public class FileUtill {
	/**
	 * 文件上传
	 * @param file 文件流
	 * @param uploadPath 文件存放路径
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String fileUpload(MultipartFile file, String uploadPath) throws IllegalStateException, IOException {
		if(file == null || StringUtils.isEmpty(uploadPath)) {
			return null;
		}
		//目录存在则创建目录
		File life1 = new File(uploadPath);
		if(!life1.exists()) {
			life1.mkdirs();
		}
		
		String fileName=file.getOriginalFilename();// 文件原名称
		//获取文件类型
		String type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
		
		if(!StringUtils.isEmpty(type)) {
			String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;
			// 设置存放图片文件的路径
			// 转存文件到指定的路径
			file.transferTo(new File(uploadPath + "/" + trueFileName));
			return uploadPath + "/" + trueFileName;
		}
		return null;
	}
	/**
	 * 删除文件
	 * @param path 文件地址
	 */
	public static void delFile(String path) {
		File life1 = new File(path);
		
		if(life1.exists()) {
			life1.delete();
		}
	}
}
