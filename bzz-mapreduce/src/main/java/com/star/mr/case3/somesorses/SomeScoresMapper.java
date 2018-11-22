package com.star.mr.case3.somesorses;

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
public class SomeScoresMapper extends Mapper<LongWritable, Text,Text, Text> {
	private Text couresText = new Text();
	private Text couresInt = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] split = line.split(",");
		String course = split[0];
		String name = split[1];
		String score = split[2];
		couresText.set(course+"_"+score);
		couresInt.set(name);
		context.write(couresText,couresInt);
		
	}
}
