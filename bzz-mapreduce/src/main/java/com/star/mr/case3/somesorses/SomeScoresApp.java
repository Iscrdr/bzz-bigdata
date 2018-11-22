package com.star.mr.case3.somesorses;

import com.star.mr.case2.avg.AvgScoreApp;
import com.star.mr.case2.avg.AvgScoreReducer;
import com.star.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 求该成绩表当中科目出现了相同分数（人数大于1）的，求分数，次数，以及该分数的人。格式：computer	85	3	huangzitao,liujialing,huangxiaoming
 * @author: Yang qianli
 * @create: 2018-10-23 20:54
 * @version: 1.0.0
 */
public class SomeScoresApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "SomeScoresApp");
		job.setJarByClass(AvgScoreApp.class);
		
		job.setMapperClass(SomeScoresMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setReducerClass(SomeScoresReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		File file = new File("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case3\\output");
		FileUtils.delete(file);
		
		FileInputFormat.addInputPath(job,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case2\\input"));
		FileOutputFormat.setOutputPath(job,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case3\\output"));
		
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
		
	}
}
