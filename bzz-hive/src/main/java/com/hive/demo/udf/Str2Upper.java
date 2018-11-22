package com.hive.demo.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
public class Str2Upper extends UDF {


    public  String evaluate(String str){


        String result = "";

        if(StringUtils.isNotBlank(str)){
            result= StringUtils.upperCase(str);
        }
        return result;

    }
    public static void main(String[] args) {
        String str = new Str2Upper().evaluate("abc");
        System.out.println(str);
    }


}
