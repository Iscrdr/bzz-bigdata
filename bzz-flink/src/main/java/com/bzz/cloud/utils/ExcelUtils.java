package com.bzz.cloud.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @description：Excel解析工具
 * @author     ：Iscrdr
 * @email      ：624003618@qq.com
 * @date       ：2020/01/16 3:00
 * @modified By：
 * @version: 1.0.0$
 */
public class ExcelUtils {

   public static List<List<Object>> getExcelData(String path){

       FileInputStream fileInputStream = null;
       try {
           fileInputStream = new FileInputStream(path);
           ExcelListener listener = new ExcelListener();
           EasyExcelFactory.readBySax(fileInputStream,new Sheet(1, 1), listener);
           List<List<Object>> data = listener.getData();

            return data;
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }

       return null;
   }

}
