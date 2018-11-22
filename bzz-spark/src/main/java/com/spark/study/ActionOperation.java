package com.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

/**
 * @Title: ActionOperation
 * @ProjectName bzz-cloud
 * @Auther: clouder
 * @Date: 2018-07-13-18:10
 * @Description:
 */
public class ActionOperation {
	/**
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 * @author 624003618@qq.com
	 * @date 2018-07-13 18:23
	 */
	private static void reduce(){
		SparkConf conf = new SparkConf().setAppName("ActionOperation").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		JavaRDD<Integer> numbers = jsc.parallelize(numberList);
		Integer reduce = numbers.reduce(new Function2<Integer, Integer, Integer>() {
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1 + v2;
			}
		});
		System.out.println(reduce);
		
		
		
		jsc.close();
	}
	
	private static void map(){
		SparkConf conf = new SparkConf().setAppName("ActionOperation").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		JavaRDD<Integer> numbers = jsc.parallelize(numberList);
		JavaRDD<Integer> map = numbers.map(new Function<Integer, Integer>() {
			
			@Override
			public Integer call(Integer v) throws Exception {
				return v * 2;
			}
		});
		map.foreach(new VoidFunction<Integer>() {
			@Override
			public void call(Integer v) throws Exception {
				System.out.println(v);
			}
		});
		
		
		jsc.close();
	}
	
	private static void count(){
		SparkConf conf = new SparkConf().setAppName("ActionOperation").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		JavaRDD<Integer> numbers = jsc.parallelize(numberList);
		long count = numbers.count();
		System.out.println(count);
		
		jsc.close();
	}
	private static void take(){
		SparkConf conf = new SparkConf().setAppName("ActionOperation").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		JavaRDD<Integer> numbers = jsc.parallelize(numberList);
		List<Integer> lists = numbers.take(3);
		System.out.println(lists.toString());
		jsc.close();
	}
	private static void saveASText(){
		SparkConf conf = new SparkConf().setAppName("ActionOperation");
		conf.set("spark.testing.memory", "2147480000");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		JavaRDD<Integer> numbers = jsc.parallelize(numberList);
		Integer reduce = numbers.reduce(new Function2<Integer, Integer, Integer>() {
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1 + v2;
			}
		});
		numbers.saveAsTextFile("hdfs://hadoop-cloud:8020/spark/numbers.txt");
		
		System.out.println(reduce);
		
		jsc.close();
	}
	public static void main(String[] args) {
		/*reduce();*/
		/*map();*/
		/*count();*/
		saveASText();
	}
	
}
