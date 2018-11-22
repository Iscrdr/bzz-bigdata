package com.star.mr.case5.fensi;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 求互粉好友对：例如A的粉丝有B，B的粉丝有A，则为一对互粉好友对
 * @author: Yang qianli
 * @create: 2018-10-23 20:30
 * @version: 1.0.0
 */
public class FensiMapper extends Mapper<LongWritable, Text, Text,  IntWritable> {
	private Text keys = new Text();
	private IntWritable v = new IntWritable();
	/**
	 * 输入：
	 * A:B,C,D,F,E,O
	 * B:A,C,E,K
	 * 输出：
	 * A-B
	 * @param key
	 * @param value
	 * @param context
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] split = line.split(":");
		String friends = split[1];
		
		String[] split1 = friends.split(",");
		for(int i=0;i<split1.length;i++){
			if(split[0].compareTo(split1[i]) < 0 ){
				keys.set(split[0]+"-"+split1[i]);
			}else {
				keys.set(split1[i]+"-"+split[0]);
			}
			v.set(1);
			context.write(keys, v);
		}
	}
}
