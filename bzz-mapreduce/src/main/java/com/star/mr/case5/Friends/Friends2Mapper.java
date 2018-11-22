package com.star.mr.case5.Friends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * @program: bzz-bigdata
 * @description: 求平均成绩 Mapper
 * @author: Yang qianli
 * @create: 2018-10-23 20:30
 * @version: 1.0.0
 */
public class Friends2Mapper extends Mapper<LongWritable, Text, Text, Text> {
	private Text k1 = new Text();
	private Text values = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		
		String[] split = line.split("\t");
		String[] split1 = split[1].split(",");
		
		Arrays.sort(split1);
		
		for(int i=0;i<split1.length-1;i++){
			for(int j = i+1;j<split1.length;j++){
				String k = split1[i] + "-" + split1[j];
				k1.set(k);
				values.set(split[0]);
				context.write(k1,values);
			}
		}
		
		
		
	}
}
