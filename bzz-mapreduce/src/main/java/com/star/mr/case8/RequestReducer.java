package com.star.mr.case8;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.*;

/**
 * @program: bzz-bigdata
 * @description: 要求编写MapReduce程序算出高峰时间段（如上午10点）哪张表被访问的最频繁，以及这段时间访问这张表最多的用户，以及这个用户的总时间开销
 * @author: Yang qianli
 * @create: 2018-11-06 23:10
 * @version: 1.0.0
 */
public class RequestReducer extends Reducer<Text, Text, Text, Text> {
	private Text kout = new Text();
	private Text vout = new Text();
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		Iterator<Text> iterator = values.iterator();
		int count = 0;
		Map<String,Double> map = new HashMap<String,Double>();
		while (iterator.hasNext()){
			count ++;
			Text next = iterator.next();
			String s = next.toString();
			String[] split = s.split("\t");
			String s1 = split[0];//用户
			double time = Double.valueOf(split[1]) ;
			
			Double aDouble = map.get(s1);
			if(aDouble == null || aDouble == 0){
				map.put(s1,time);
			}else {
				aDouble += time;
				map.put(s1,aDouble);
			}
			
		}
		List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			// 降序排序
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return -o1.getValue().compareTo(o2.getValue());
			}
		});
		Map.Entry<String, Double> me = list.get(0);
		String key1 = me.getKey();
		Double value1 = me.getValue();
		
		vout.set(count+"\t"+key1+"\t"+value1);
		context.write(key,vout);
		
	}
}
