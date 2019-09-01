package com.bzz.cloud.flink.api.dataset;

import org.apache.flink.api.java.ExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bzz-bigdata
 * @description: create DataSet use java
 * @author: 624003618@qq.com
 * @create: 2019-08-17 03:37
 */
public class DataSetSourceAppJava {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        fromCollect(env);

    }
   public static void  fromCollect(ExecutionEnvironment env) throws Exception {
        List<Integer> data = new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            data.add(i);
        }
        env.fromCollection(data).print();

    }

}
