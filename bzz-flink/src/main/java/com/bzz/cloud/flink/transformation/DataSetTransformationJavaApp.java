package com.bzz.cloud.flink.transformation;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.LocalEnvironment;
import org.apache.flink.api.java.operators.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bzz-bigdata
 * @description: dataset transformation api
 * @author: 624003618@qq.com
 * @create: 2019-09-01 16:26
 */
public class DataSetTransformationJavaApp {
    public static void main(String[] args) throws Exception {
        LocalEnvironment env = ExecutionEnvironment.createLocalEnvironment();
        mapFunction(env);
    }
    public static void mapFunction(ExecutionEnvironment env) throws Exception {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }

        DataSource<Integer> integerDataSource = env.fromCollection(list);
        integerDataSource.map((MapFunction<Integer, Integer>) value -> value * 2).print();
        integerDataSource.map(x -> x*2 ).print();

        integerDataSource.filter(new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer value) throws Exception {
                return value % 2==0?true:false;
            }
        }).print();

        integerDataSource.filter(x -> x%2==0).print();
        integerDataSource.filter((FilterFunction<Integer>)x -> x%2==0).print();


    }
}
