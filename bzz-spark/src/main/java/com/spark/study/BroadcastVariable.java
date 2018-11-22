package com.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;

/**
 * @Title: BroadcastVariable
 * @ProjectName bzz-cloud
 * @Auther: clouder
 * @Date: 2018-07-13-22:47
 * @Description: 广播变量
 */
public class BroadcastVariable {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local").setAppName("BroadcastVariable");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		final int  factor = 3;
		final Broadcast<Integer> broadcast = jsc.broadcast(factor);
		
		List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		JavaRDD<Integer> numbers = jsc.parallelize(numberList);
		
		JavaRDD<Integer> map = numbers.map(new Function<Integer, Integer>() {
			@Override
			public Integer call(Integer v) throws Exception {
				Integer factor1 = broadcast.value();
				return v * factor1;
			}
		});
		
		map.foreach(new VoidFunction<Integer>() {
			@Override
			public void call(Integer v) throws Exception {
				System.out.print(v+",");
			}
		});
		jsc.close();
		
	}
}
