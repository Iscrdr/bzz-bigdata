package com.star.mr.case5.Friends;

import com.star.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
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
public class FriendsApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "FriendsApp");
		job.setJarByClass(FriendsApp.class);
		
		job.setMapperClass(FriendsMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setReducerClass(FriendsReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		File file = new File("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case5\\output");
		FileUtils.delete(file);
		
		FileInputFormat.addInputPath(job,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case5\\input"));
		FileOutputFormat.setOutputPath(job,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case5\\output"));
		
		
		
		Job job2 = Job.getInstance(conf, "FriendsApp2");
		job2.setJarByClass(FriendsApp.class);
		
		job2.setMapperClass(Friends2Mapper.class);
		job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(Text.class);
		
		job2.setReducerClass(Friends2Reducer.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);
		
		File file1 = new File("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case5\\output1");
		FileUtils.delete(file1);
		
		FileInputFormat.addInputPath(job2,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case5\\output"));
		FileOutputFormat.setOutputPath(job2,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case5\\output1"));
		
		ControlledJob aJob = new ControlledJob(job.getConfiguration());
		ControlledJob bJob = new ControlledJob(job2.getConfiguration());
		
		aJob.setJob(job);
		bJob.setJob(job2);
		bJob.addDependingJob(aJob);//指定依赖关系
		
		JobControl jc = new JobControl("jcF");
		jc.addJob(aJob);
		jc.addJob(bJob);
		
		Thread thread = new Thread(jc);
		thread.start();
		while(!jc.allFinished()){
			thread.sleep(1000);
		}
		
		jc.stop();
		
		//System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
		
	}
}
