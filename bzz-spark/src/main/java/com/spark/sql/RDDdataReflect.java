package com.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.util.Iterator;
import java.util.List;

/**
 * @Title: RDDdataReflect
 * @ProjectName bzz-cloud
 * @Auther: clouder
 * @Date: 2018-07-17-1:38
 * @Description:
 */
public class RDDdataReflect {
	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("RDDdataReflect").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		
		JavaRDD<String> students = jsc.textFile("D:\\app\\student.txt");
		JavaRDD<Student> studentRdd = students.map(new Function<String, Student>() {
			
			@Override
			public Student call(String s) throws Exception {
				String[] lines = s.split(",");
				Student  student = new Student();
				student.setId(Integer.valueOf(lines[0]));
				student.setName(lines[1]);
				student.setAge(Integer.valueOf(lines[2]));
				
				return student;
			}
		});
		SQLContext sqlContext = new SQLContext(jsc);
		Dataset<Row> dataFrame = sqlContext.createDataFrame(studentRdd, Student.class);
		dataFrame.registerTempTable("students");
		
		Dataset<Row> sql = sqlContext.sql("select * from students");
		JavaRDD<Row> rowJavaRDD = sql.javaRDD();
		
		JavaRDD<Student> map = rowJavaRDD.map(new Function<Row, Student>() {
			@Override
			public Student call(Row row) throws Exception {
				Student  student = new Student();
				
				student.setId((Integer) row.get(1));
				student.setName("_name_"+(String) row.get(2));
				student.setAge((Integer) row.get(0));
				
				return student;
			}
		});
		
		List<Student> collect = map.collect();
		
		for(Iterator<Student> iterator = collect.iterator();iterator.hasNext();){
			Student next = iterator.next();
			System.out.println(next.getId()+","+next.getName()+","+next.getAge());
		}
		
	}
	
	
}
