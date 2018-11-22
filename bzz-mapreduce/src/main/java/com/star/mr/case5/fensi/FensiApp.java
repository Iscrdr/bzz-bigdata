package com.star.mr.case5.fensi;

import com.star.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 每一个course的最高分，最低分，平均分。格式：course	max=95	min=22	avg=55
 * @author: Yang qianli
 * @create: 2018-10-23 20:54
 * @version: 1.0.0
 */
public class FensiApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "FriendsApp");
		job.setJarByClass(FensiApp.class);
		
		job.setMapperClass(FensiMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setReducerClass(FensiReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		File file = new File("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case5\\output1");
		FileUtils.delete(file);
		
		FileInputFormat.addInputPath(job,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case5\\input"));
		FileOutputFormat.setOutputPath(job,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case5\\output1"));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
	}
}
