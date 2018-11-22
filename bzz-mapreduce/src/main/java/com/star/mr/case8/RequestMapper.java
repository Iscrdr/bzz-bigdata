package com.star.mr.case8;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 要求编写MapReduce程序算出高峰时间段（如上午10点）哪张表被访问的最频繁，以及这段时间访问这张表最多的用户，以及这个用户的总时间开销
 * @author: Yang qianli
 * @create: 2018-11-06 23:04
 * @version: 1.0.0
 */
public class RequestMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	private Text kout = new Text();
	private Text vout = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] s = line.split("\t");
		
		kout.set(s[0]+"\t"+s[1]);//表名和时间为key
		vout.set(s[2]+"\t"+s[3]);
		context.write(kout,vout);
	}
}
