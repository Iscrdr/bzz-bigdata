package com.star.mr.case1.wordcount;

import com.star.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

import java.io.File;
import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: WordCountDriver
 * @author: Yang qianli
 * @create: 2018-10-23 12:54
 * @version: 1.0.0
 */
public class WordCountApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf, "word count");
		job.setJarByClass(WordCountApp.class);
		
		job.setMapperClass(WordCountMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setCombinerClass(IntSumReducer.class);
		
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		File file = new File("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case1\\output");
		FileUtils.delete(file);
		
		FileInputFormat.addInputPath(job,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case1\\input"));
		FileOutputFormat.setOutputPath(job,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case1\\output"));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}
	
	
}
