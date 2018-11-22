package com.star.mr.case7;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 版本变动信息mapper
 * @author: Yang qianli
 * @create: 2018-11-06 10:46
 * @version: 1.0.0
 */
public class VersionMapper extends Mapper<LongWritable,Text,VersionModel, NullWritable> {
	private VersionModel v = null;
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		
		String[] split = line.split(",");
		//String version = split[5].replaceAll("版本", "");
		v = new VersionModel(split[0],split[1],split[2],split[3],split[4],split[5],split[6]);
		
		context.write(v,NullWritable.get());
		
	}
	
	
}
