package com.bzz.cloud.flink.source.dataset;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.LocalEnvironment;
import org.apache.flink.api.java.operators.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bzz-bigdata
 * @description: flink dataset java api
 * @author: 624003618@qq.com
 * @create: 2019-09-01 14:41
 */
public class DataSetDataSourceJavaApp {
    public static void main(String[] args) throws Exception {
        LocalEnvironment env = ExecutionEnvironment.createLocalEnvironment();
       // fromCollection(env);
        textFile(env);
    }

    public static void textFile(ExecutionEnvironment env) throws Exception {
        String filePath = "D:\\testdata";
        String filePath2 = "D:\\\\testdata\\\\student.txt";

        env.readTextFile(filePath).print();

        System.out.println("==============================================================");

        env.readTextFile(filePath2).print();
    }


    public static void fromCollection(ExecutionEnvironment env) throws Exception {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        env.fromCollection(list).print();
    }
}
