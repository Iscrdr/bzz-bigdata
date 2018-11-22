package com.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TransformationOperation {
	
	
	public static void map(){
		SparkConf conf = new SparkConf().setMaster("local").setAppName("TransformationOperation");
		SparkContext sc = new SparkContext(conf);
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		
		JavaSparkContext jsc = new JavaSparkContext(sc);
		JavaRDD<Integer> numbersRDD = jsc.parallelize(numbers, 4);
		JavaRDD<Integer> numbersRDDMap = numbersRDD.map(new Function<Integer, Integer>() {
			public Integer call(Integer v1) throws Exception {
				return v1 * 2;
			}
		});
		numbersRDDMap.foreach(new VoidFunction<Integer>() {
			public void call(Integer v1) throws Exception {
				System.out.println(v1 + ",");
			}
		});
		jsc.close();
	}
	
	public static void filter(){
		SparkConf conf = new SparkConf().setMaster("local").setAppName("TransformationOperation");
		SparkContext sc = new SparkContext(conf);
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		
		JavaSparkContext jsc = new JavaSparkContext(sc);
		
		JavaRDD<Integer> numbersRDD = jsc.parallelize(numbers, 4);
		JavaRDD<Integer> numbersRDDMap = numbersRDD.filter(new Function<Integer, Boolean>() {
			public Boolean call(Integer v1) throws Exception {
				if (v1 % 2 == 0) {
					return false;
				}
				return true;
			}
		});
		
		numbersRDDMap.foreach(new VoidFunction<Integer>() {
			public void call(Integer v1) throws Exception {
				System.out.println(v1 + ",");
			}
		});
		jsc.close();
	}
	
	public static void flatMap(){
		SparkConf conf = new SparkConf().setMaster("local").setAppName("flatMap");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		List<String> words = Arrays.asList("hello you hello me hello world");
		JavaRDD<String> lines = jsc.parallelize(words);
		JavaRDD<String> stringJavaRDD = lines.flatMap(new FlatMapFunction<String, String>() {
			
			public Iterator<String> call(String s) throws Exception {
				String[] split = s.split(" ");
				List<String> strings = Arrays.asList(split);
				return strings.iterator();
			}
		});
		stringJavaRDD.foreach(new VoidFunction<String>() {
			public void call(String s) throws Exception {
				System.out.println(s);
				
			}
		});
		jsc.close();
	}
	
	
	public static void groupByKey(){
		SparkConf conf = new SparkConf().setMaster("local").setAppName("groupByKey");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Tuple2<String,Integer>> scores = Arrays.asList(
				new Tuple2<String, Integer>("math", 80),
				new Tuple2<String, Integer>("english", 81),
				new Tuple2<String, Integer>("yuwen", 90),
				new Tuple2<String, Integer>("math", 85),
				new Tuple2<String, Integer>("english", 65),
				new Tuple2<String, Integer>("yuwen", 26)
		);
		
		JavaPairRDD<String, Integer> scoresRDD = jsc.parallelizePairs(scores);
		JavaPairRDD<String, Iterable<Integer>> scoresIterable = scoresRDD.groupByKey();
		scoresIterable.foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
			public void call(Tuple2<String, Iterable<Integer>> t) throws Exception {
				
				String str = "";
				for(Iterator<Integer> iterator = t._2.iterator();iterator.hasNext();){
					str += iterator.next()+",";
				}
				String substring = str.substring(0, str.length() - 1);
				System.out.println(t._1+" ==> "+substring);
				
			}
		});
		jsc.close();
	}
	public static void reduceByKey(){
		SparkConf conf = new SparkConf().setMaster("local").setAppName("groupByKey");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Tuple2<String,Integer>> scores = Arrays.asList(
				new Tuple2<String, Integer>("math", 80),
				new Tuple2<String, Integer>("english", 81),
				new Tuple2<String, Integer>("yuwen", 90),
				new Tuple2<String, Integer>("math", 85),
				new Tuple2<String, Integer>("english", 65),
				new Tuple2<String, Integer>("yuwen", 26)
		);
		JavaPairRDD<String, Integer> scoresRDD = jsc.parallelizePairs(scores);
		JavaPairRDD<String, Integer> totalRDD = scoresRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1 + v2;
			}
		});
		totalRDD.foreach(new VoidFunction<Tuple2<String, Integer>>() {
			public void call(Tuple2<String, Integer> t) throws Exception {
				
				System.out.println(t._1 +":" + t._2);
			}
		});
		jsc.close();
	}
	
public static void sortByKey(){
		SparkConf conf = new SparkConf().setMaster("local").setAppName("groupByKey");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Tuple2<String,Integer>> scores = Arrays.asList(
				new Tuple2<String, Integer>("math", 80),
				new Tuple2<String, Integer>("english", 81),
				new Tuple2<String, Integer>("yuwen", 90),
				new Tuple2<String, Integer>("math", 85),
				new Tuple2<String, Integer>("english", 65),
				new Tuple2<String, Integer>("yuwen", 26)
		);
		JavaPairRDD<String, Integer> scoresRDD = jsc.parallelizePairs(scores);
		
		JavaPairRDD<Integer, String> sorts = scoresRDD.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
			public Tuple2<Integer, String> call(Tuple2<String, Integer> v) throws Exception {
				return new Tuple2<Integer, String>(v._2, v._1);
			}
		});
		JavaPairRDD<Integer, String> sorces = sorts.sortByKey();
		sorces.foreach(new VoidFunction<Tuple2<Integer, String>>() {
			public void call(Tuple2<Integer, String> t) throws Exception {
				System.out.println(t._2 +":" + t._1);
			}
		});
		jsc.close();
	}
	public static void join(){
		SparkConf conf = new SparkConf().setMaster("local").setAppName("groupByKey");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Tuple2<Integer,String>> students = Arrays.asList(
				new Tuple2<Integer,String>(1,"math"),
				new Tuple2<Integer,String>(2,"english"),
				new Tuple2<Integer,String>(3,"yuwen")
		);
		List<Tuple2<Integer,Integer>> scores = Arrays.asList(
				new Tuple2<Integer,Integer>(1,80),
				new Tuple2<Integer,Integer>(2,90),
				new Tuple2<Integer,Integer>(3,70)
		);
		
		JavaPairRDD<Integer, String> studentsRDD = jsc.parallelizePairs(students);
		JavaPairRDD<Integer, Integer> scoresRDD = jsc.parallelizePairs(scores);
		JavaPairRDD<Integer, Tuple2<String, Integer>> sorcesJoin = studentsRDD.join(scoresRDD);

		sorcesJoin.foreach(new VoidFunction<Tuple2<Integer, Tuple2<String, Integer>>>() {
			public void call(Tuple2<Integer, Tuple2<String, Integer>> v) throws Exception {
				System.out.println(v._1+" ===> "+v._2._1+" ===> " + v._2._2);
			}
		});
		jsc.close();
	}
	public static void cogroup(){
		SparkConf conf = new SparkConf().setMaster("local").setAppName("groupByKey");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		List<Tuple2<Integer,String>> students = Arrays.asList(
				new Tuple2<Integer,String>(1,"math"),
				new Tuple2<Integer,String>(2,"english"),
				new Tuple2<Integer,String>(3,"yuwen")
		);
		List<Tuple2<Integer,Integer>> scores = Arrays.asList(
				new Tuple2<Integer,Integer>(1,80),
				new Tuple2<Integer,Integer>(2,90),
				new Tuple2<Integer,Integer>(3,70)
		);
		
		JavaPairRDD<Integer, String> studentsRDD = jsc.parallelizePairs(students);
		JavaPairRDD<Integer, Integer> scoresRDD = jsc.parallelizePairs(scores);
		JavaPairRDD<Integer, Tuple2<Iterable<String>, Iterable<Integer>>> cogroupRDD = studentsRDD.cogroup(scoresRDD);
		
		cogroupRDD.foreach(new VoidFunction<Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>>>() {
			public void call(Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>> t) throws Exception {
				System.out.println(t._1+":"+t._2._1.iterator().next()+":"+t._2._2.iterator().next());
			}
		});
		jsc.close();
	}
	
	public static void main(String[] args) {
		//map();
		//filter();
		
		//flatMap();
		//groupByKey();
		//reduceByKey();
		/*sortByKey();*/
		//join();
		/*cogroup();*/
	}
}
