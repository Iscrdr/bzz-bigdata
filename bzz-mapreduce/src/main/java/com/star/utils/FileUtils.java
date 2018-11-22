package com.star.utils;

import java.io.File;

/**
 * @program: bzz-bigdata
 * @description: 文件工具类
 * @author: Yang qianli
 * @create: 2018-10-23 21:01
 * @version: 1.0.0
 */
public class FileUtils {
	/**
	 * 删除文件夹以及该文件夹下的所有文件夹和文件
	 * @param file
	 */
	public static void delete(File file) {
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				delete(f);
				f.delete();
			}
		}
		file.delete();
	}
}
