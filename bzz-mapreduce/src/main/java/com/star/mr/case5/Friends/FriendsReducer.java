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
public class FriendsReducer extends Reducer<Text, Text,Text, Text> {
	private Text k1 = new Text();
	private Text value = new Text();
	
	/**合并相同key的value
	 * A-B:C,D,F,E,O,  C,E,K
	 * @param key
	 * @param values
	 * @param context
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		Iterator<Text> iterator = values.iterator();
		String friends = "";
		while (iterator.hasNext()){
			Text next = iterator.next();
			friends += next.toString()+",";
		}
		if (friends.endsWith(",")){
			friends = friends.substring(0,friends.length()-1);
		}
		value.set(friends);
		context.write(key,value);
	}
	
	

}
