package com.star.mr.case5.fensi;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
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
public class FensiReducer extends Reducer<Text, IntWritable,Text, NullWritable> {
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
	protected void reduce(Text key, Iterable< IntWritable> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		for(IntWritable text : values){
			int i = text.get();
			count += i;
		}
		if(count > 1){
			context.write(key,NullWritable.get());
		}
		
	}
	
	

}
