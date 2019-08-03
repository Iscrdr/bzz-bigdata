package com.spark.scala.day02;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;


/**
 * @author Yang qianli
 * @version 1.0.0
 * @ProjectName bzz-bigdata
 * @Description: TODO
 * @email 624003618@qq.com
 * @date 2019-01-02 09:30:24
 */
public class JavaWorkCount {
    public static void main(String[] args) {

        int i = 5 / 0;

        SparkConf conf = new SparkConf();
        conf.setAppName("JavaWorkCount");
        conf.setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> rDD = sc.textFile("D:\\app\\word1.txt");
        JavaRDD<String> rDD1 = rDD.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")).iterator();
            }
        });
        JavaPairRDD<String, Integer> rDD2 = rDD1.mapToPair(new PairFunction<String, String, Integer>() {

            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });
        JavaPairRDD<String, Integer> rDD3 = rDD2.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        JavaPairRDD<Integer, String> rDD4 = rDD3.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(Tuple2<String, Integer> tuple2) throws Exception {
                return tuple2.swap();
            }
        });

        JavaPairRDD<Integer, String> rDD5 = rDD4.sortByKey(false);

        JavaPairRDD<String, Integer> rDD6 = rDD5.mapToPair(new PairFunction<Tuple2<Integer, String>, String,Integer >() {

            @Override
            public Tuple2<String, Integer> call(Tuple2<Integer, String> tuple2) throws Exception {
                return tuple2.swap();
            }
        });

        rDD6.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> wordCount) throws Exception {
                System.out.println(wordCount._1 + " : "+wordCount._2 );
            }
        });


        sc.close();

    }
}
