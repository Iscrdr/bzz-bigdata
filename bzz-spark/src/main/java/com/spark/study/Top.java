package com.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.List;

/**
 * @Title: Top
 * @ProjectName bzz-cloud
 * @Auther: clouder
 * @Date: 2018-07-16-11:00
 * @Description:
 */
public class Top {
	public static void main(String[] args) {
		SparkConf sc = new SparkConf().setMaster("local").setAppName("Top");
		JavaSparkContext jsc = new JavaSparkContext(sc);
		
		JavaRDD<String> textFile = jsc.textFile("D:\\app\\top.txt");
		JavaPairRDD<Integer, String> textLines = textFile.mapToPair(new PairFunction<String, Integer, String>() {
			@Override
			public Tuple2<Integer, String> call(String s) throws Exception {
				return new Tuple2<Integer, String>(Integer.valueOf(s), s);
			}
		});
		JavaPairRDD<Integer, String> sortText = textLines.sortByKey(false);
		List<Tuple2<Integer, String>> takes = sortText.take(3);
		for (int i = 0 ;i<takes.size();i++){
			Tuple2<Integer, String> v = takes.get(i);
			System.out.println(v._1+":"+v._2);
			
		}
		
		
	}
}
