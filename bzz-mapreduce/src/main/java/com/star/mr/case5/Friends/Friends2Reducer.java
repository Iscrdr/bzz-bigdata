package com.star.mr.case5.Friends;

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
public class Friends2Reducer extends Reducer<Text, Text,Text, Text> {
	private Text k = new Text();
	private Text v = new Text();
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		Iterator<Text> iterator = values.iterator();
		String friends ="";
		while (iterator.hasNext()){
			Text next = iterator.next();
			friends += next.toString()+",";
		}
		if(friends.endsWith(",")){
			friends = friends.substring(0,friends.length()-1);
		}
		v.set(friends);
		context.write(key,v);
		
		
		
	}
}
