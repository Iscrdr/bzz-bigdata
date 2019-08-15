package com.bzz.common.Utils;

import java.io.File;
import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 文件夹递归
 * @author: 624003618@qq.com
 * @create: 2019-08-14 03:27
 */
public class FileUtils {

    public static void main(String[] args) throws IOException {
        //浏览F盘a文件夹下的所有内容
        File dir=new File("D:\\BaiduNetdiskDownload\\新一代大数据计算引擎 Flink从入门到实战\\第1章 初识Flink\\1-3 课程目录-.mp4");
        listFile(dir,"");
    }
    public static void listFile(File dir,String spance) throws IOException {
        if(dir == null){
            System.out.println("您传入的文件或文件夹无效");
            return;
        }
        if(dir.isFile()){
            System.out.println("您传入的是文件,绝对路径为："+dir.getAbsolutePath());
            return;
        }

        //列出所有的子文件
        File[] files=dir.listFiles();
        for(File file :files){
            if(file.isFile()){
                System.out.println(spance+file.getName());
            }else if(file.isDirectory()){
                System.out.println(spance+file.getName());
                //递归遍历
                listFile(file,"|--"+spance);
            }
        }
    }


}
