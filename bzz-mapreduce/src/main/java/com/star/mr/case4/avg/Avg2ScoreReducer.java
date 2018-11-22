package com.star.mr.case4.avg;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @program: bzz-bigdata
 * @description: 求平均成绩Reducer
 * @author: Yang qianli
 * @create: 2018-10-23 20:39
 * @version: 1.0.0
 */
public class Avg2ScoreReducer extends Reducer<Text, IntWritable,Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		
		Iterator<IntWritable> iterator = values.iterator();
		int total = 0;
		int count = 0;
		while (iterator.hasNext()){
			IntWritable next = iterator.next();
			int score = next.get();
			total += score;
			count++;
		}
		int avg = total / count; //平均成绩
		context.write(key,new Text("   "+avg));
	}
}
