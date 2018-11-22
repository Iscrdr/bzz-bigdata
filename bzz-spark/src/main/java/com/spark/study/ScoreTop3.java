package com.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.spark_project.guava.collect.Lists;
import scala.Tuple2;

import java.util.*;

/**
 * @Title: ScoreTop3
 * @ProjectName bzz-cloud
 * @Auther: clouder
 * @Date: 2018-07-16-11:19
 * @Description:
 */
public class ScoreTop3 {
	public static void main(String[] args) {
		SparkConf  sparkConf = new SparkConf().setMaster("local").setAppName("ScoreTop3");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		JavaRDD<String> textFile = jsc.textFile("D:\\app\\score.txt");
		
		
		JavaPairRDD<String, Integer> tupleText = textFile.mapToPair(new PairFunction<String, String, Integer>() {
			
			@Override
			public Tuple2<String, Integer> call(String s) throws Exception {
				String[] s1 = s.split(" ");
				return new Tuple2<String, Integer>(s1[0], Integer.valueOf(s1[1]));
			}
		});
		JavaPairRDD<String, Iterable<Integer>> groupClass= tupleText.groupByKey();
		
		JavaPairRDD<String, Iterable<Integer>> valuesRdd =
				groupClass.mapToPair(new PairFunction<Tuple2<String, Iterable<Integer>>, String, Iterable<Integer>>() {
					@Override
					public Tuple2<String,  Iterable<Integer>> call(Tuple2<String, Iterable<Integer>> v) throws Exception {
						ArrayList<Integer> values = Lists.newArrayList(v._2);
						
						Collections.sort(values);
						
						List<Integer> v1 = values.subList(values.size() - 3, values.size());
						Iterator<Integer> iterator = v1.iterator();
						
						Iterable<Integer> it = new Iterable<Integer>() {
							@Override
							public Iterator<Integer> iterator() {
								return iterator;
							}
						};
						return new Tuple2<String,  Iterable<Integer>>(v._1,it);
					}
				});
		valuesRdd.foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
			@Override
			public void call(Tuple2<String, Iterable<Integer>> v) throws Exception {
				String s = v._1+" : ";
				for(Iterator<Integer> iterator = v._2.iterator();iterator.hasNext();){
					s += iterator.next()+",";
				}
				String result = s.substring(0, s.length() - 1);
				System.out.println(result);
				
			}
		});
		
	}
}
