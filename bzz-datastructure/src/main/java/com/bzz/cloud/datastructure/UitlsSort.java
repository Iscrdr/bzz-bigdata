package com.bzz.cloud.datastructure;

/**
 * @author ：Iscrdr
 * @description：排序工具
 * @email ：624003618@qq.com
 * @date ：2021-01-18 22:34
 * @modified By：
 * @version: 1.0.0
 */
public class UitlsSort {
    public static void printArr(int [] arr){
        String a = "[";
        for (int i = 0;i<arr.length;i++){
            a = a +  arr[i] + ",";
        }
        if(a.endsWith(",")){
            a =  a.substring(0,a.length()-1);
        }
        a = a+"]";
        System.out.println(a);
    }
}
