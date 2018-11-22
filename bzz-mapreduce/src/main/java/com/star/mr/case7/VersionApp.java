package com.star.mr.case7;

import com.star.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
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
 * @create: 2018-11-06 11:14
 * @version: 1.0.0
 */
public class VersionApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "CourseCountApp");
		job.setJarByClass(VersionApp.class);
		
		job.setMapperClass(VersionMapper .class);
		job.setMapOutputKeyClass(VersionModel .class);
		job.setMapOutputValueClass(NullWritable .class);
		
		job.setReducerClass(VersionReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		job.setGroupingComparatorClass(VersionGroup.class);
		String inPath = "D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case7\\input";
		String outPath = "D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case7\\output";
		
		File file = new File(outPath);
		FileUtils.delete(file);
		
		FileInputFormat.addInputPath(job,new Path(inPath));
		FileOutputFormat.setOutputPath(job,new Path(outPath));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
