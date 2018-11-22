package com.star.mr.case6.avg;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @program: bzz-bigdata
 * @description: 统计每门课程的参考人数和课程的平均分
 * @author: Yang qianli
 * @create: 2018-11-06 07:47
 * @version: 1.0.0
 */
public class CourseCountReducer extends Reducer<Text,Text,Text,Text> {
	private Text kout = new Text();
	private Text vout = new Text();
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		Iterator<Text> iterator = values.iterator();
		int count = 0;
		int scoreTotal = 0;
		while (iterator.hasNext()){
			Text next = iterator.next();
			String s = next.toString();
			String[] split = s.split(",");
			String student = split[0];
			String avg = split[1];
			count ++;
			scoreTotal += Integer.valueOf(avg);
		}
		int avg = scoreTotal/count;
		
		kout.set(key);
		vout.set(count+","+avg);
		context.write(kout,vout);
	}
}
