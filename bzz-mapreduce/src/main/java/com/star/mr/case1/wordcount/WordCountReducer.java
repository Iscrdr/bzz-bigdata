package com.star.mr.case1.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;


/**
 * @program: bzz-bigdata
 * @description: WordCountReducer
 * @author: Yang qianli
 * @create: 2018-10-23 12:48
 * @version: 1.0.0
 */
public class WordCountReducer extends Reducer<Text, IntWritable,Text, IntWritable>  {
	private IntWritable countWritable = new IntWritable();
	@Override
	public  void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		Iterator<IntWritable> iterator = values.iterator();
		int count = 0;
		while (iterator.hasNext()){
			IntWritable next = iterator.next();
			count += next.get();
		}
		countWritable.set(count);
		context.write(key,countWritable);
	}
}
