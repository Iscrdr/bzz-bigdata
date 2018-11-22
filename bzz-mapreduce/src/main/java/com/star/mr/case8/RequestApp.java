package com.star.mr.case8;

import com.star.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description:
 * @author: Yang qianli
 * @create: 2018-11-06 23:28
 * @version: 1.0.0
 */
public class RequestApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "RequestApp");
		job.setJarByClass(RequestApp.class);
		
		job.setMapperClass(RequestMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setReducerClass(RequestReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		String inPath = "D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case8\\input";
		String outPath = "D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case8\\output";
		
		File file = new File(outPath);
		FileUtils.delete(file);
		
		FileInputFormat.addInputPath(job,new Path(inPath));
		FileOutputFormat.setOutputPath(job,new Path(outPath));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
