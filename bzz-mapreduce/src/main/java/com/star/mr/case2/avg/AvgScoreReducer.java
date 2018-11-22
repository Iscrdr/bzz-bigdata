package com.star.mr.case2.avg;

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
public class AvgScoreReducer extends Reducer<Text, IntWritable,Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		
		Iterator<IntWritable> iterator = values.iterator();
		int total = 0;
		int count = 0;
		
		int max1 = 0;
		int min1 = 0;
		
		while (iterator.hasNext()){
			IntWritable next = iterator.next();
			int score = next.get();
			
			if(count == 0){
				max1 = score;
				min1 = score;
			}else{
				if(score >= max1){
					max1 = score;
				}
				if(score <= min1){
					min1 = score;
				}
			}
			total += score;
			count++;
		}
		int avg = total / count; //平均成绩
		
		/*List<IntWritable> list = IteratorUtils..toList(iterator);
		IntWritable max1 = Collections.max(list);//最大值
		IntWritable min1 = Collections.min(list);//最小值*/
		
		context.write(key,new Text("max="+max1+" min="+min1+" avg="+avg));
	}
}
