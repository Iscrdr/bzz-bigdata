package com.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @Title: SortWord
 * @ProjectName bzz-cloud
 * @Auther: clouder
 * @Date: 2018-07-13-23:40
 * @Description:
 */
public class SortWord {
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local").setAppName("SortWord");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		JavaRDD<String> wordJavaRDD = jsc.textFile("D:\\app\\word.txt");
		
		JavaRDD<String> flatJavaRDD = wordJavaRDD.flatMap(new FlatMapFunction<String, String>() {
			@Override
			public Iterator<String> call(String s) throws Exception {
				String[] s1 = s.split(" ");
				return Arrays.asList(s1).iterator();
			}
		});
		
		JavaPairRDD<String, Integer> wordCountJavaPairRDD = flatJavaRDD.mapToPair(new PairFunction<String, String, Integer>() {
			@Override
			public Tuple2<String, Integer> call(String s) throws Exception {
				return new Tuple2<String, Integer>(s,1);
			}
		});
		JavaPairRDD<String, Integer> stringJavaPairRDD = wordCountJavaPairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
			@Override
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1+v2;
			}
		});
		stringJavaPairRDD.foreach(new VoidFunction<Tuple2<String, Integer>>() {
			@Override
			public void call(Tuple2<String, Integer> v) throws Exception {
				System.out.println(v._1+" : "+v._2);
			}
		});
		
		
		JavaPairRDD<Integer, String> integerStringJavaPairRDD = stringJavaPairRDD.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
			@Override
			public Tuple2<Integer, String> call(Tuple2<String, Integer> v) throws Exception {
				return new Tuple2<Integer, String>(v._2,v._1);
			}
		});
		JavaPairRDD<Integer, String> integerStringJavaPairRDD1 = integerStringJavaPairRDD.sortByKey(false);
		JavaPairRDD<String, Integer> resultRdd = integerStringJavaPairRDD1.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
			@Override
			public Tuple2<String, Integer> call(Tuple2<Integer, String> v) throws Exception {
				return new Tuple2<String, Integer>(v._2, v._1);
			}
		});
		resultRdd.foreach(new VoidFunction<Tuple2<String, Integer>>() {
			@Override
			public void call(Tuple2<String, Integer> v) throws Exception {
				System.out.println(v._1+":"+v._2);
			}
		});
		
	}
}
