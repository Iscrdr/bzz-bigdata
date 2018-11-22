package com.star.mr.case3.somesorses;

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
public class SomeScoresReducer extends Reducer<Text, Text,Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		Iterator<Text> iterator = values.iterator();
		int count = 0;
		String names = "";
		while (iterator.hasNext()){
			names += iterator.next().toString()+",";
			count ++;
		}
		
		String coure = "";
		String score = "";
		if(count > 1){
			String split  = key.toString();
			String[] s = split.split("_");
			coure = s[0];
			score = s[1];
			String substring = names.substring(0, names.length() - 1);
			context.write(new Text(coure),new Text(" "+score+" "+count+" "+substring));
		}
		
		
	}
}
