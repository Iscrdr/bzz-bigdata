package com.star.mr.case6.partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * computer,huangbo,85,42,96,38
 * english,zhaobenshan,54,52,86,91,42,85,75
 * @program: bzz-bigdata
 * @description: 统计每门课程的参考人数和课程平均分
 * @author: Yang qianli
 * @create: 2018-10-28 14:54
 * @version: 1.0.0
 */
public class CourseAvgPartitionMapper extends Mapper<LongWritable, Text,Student, NullWritable> {
	
	private Text kout = new Text();
	private Text vout = new Text();
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		String line = value.toString();
		String[] split = line.split(",");
		
		String course = split[0];
		String student = split[1];
		int scoreTotal = 0;
		for(int i=2;i<split.length;i++){
			scoreTotal += Integer.valueOf(split[i]);
		}
		int avg = scoreTotal/(split.length-2);
		
		kout.set(course+","+student);
		vout.set(avg+"");
		Student s = new Student(course,student,avg+"");
		context.write(s,NullWritable.get());
	}
}
