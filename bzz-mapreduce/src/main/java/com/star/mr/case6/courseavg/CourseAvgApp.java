package com.star.mr.case6.courseavg;

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
 * @description:统计每门课程的参考人数和课程平均分
 * @author: Yang qianli
 * @create: 2018-11-06 07:56
 * @version: 1.0.0
 */
public class CourseAvgApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "CourseCountApp");
		job.setJarByClass(CourseAvgApp.class);
		
		job.setMapperClass(CourseAvgMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setReducerClass(CourseAvgReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		String outPath = "D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case6\\output2";
		File file = new File(outPath);
		FileUtils.delete(file);
		
		FileInputFormat.addInputPath(job,new Path("D:\\code\\bzz-bigdata\\bzz-mapreduce\\case\\case6\\input"));
		FileOutputFormat.setOutputPath(job,new Path(outPath));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
