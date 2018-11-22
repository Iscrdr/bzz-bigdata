package com.star.mr.case2.avg;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 求平均成绩 Mapper
 * @author: Yang qianli
 * @create: 2018-10-23 20:30
 * @version: 1.0.0
 */
public class AvgScoreMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
	private Text couresText = new Text();
	private IntWritable couresInt = new IntWritable(0);
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] split = line.split(",");
		String course = split[0];
		String name = split[1];
		String score = split[2];
		
		couresText.set(course);
		couresInt.set(Integer.valueOf(score));
		
		context.write(couresText,couresInt);
		
	}
}
