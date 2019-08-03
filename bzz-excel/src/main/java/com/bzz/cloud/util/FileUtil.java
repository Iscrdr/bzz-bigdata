package com.bzz.cloud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author : yang qianli
 * @email: 624003618@qq.com
 * @Date: 2019-05-30 13-21
 * @Modified by:
 * @Description:
 */
public class FileUtil {
    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static InputStream getFileInputStream(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(new File(fileName));
        return fis;
    }
}
