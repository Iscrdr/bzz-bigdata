package com.star.mr.case7;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @program: bzz-bigdata
 * @description: 版本变动信息mapper
 * @author: Yang qianli
 * @create: 2018-11-06 10:46
 * @version: 1.0.0
 */
public class VersionReducer extends Reducer<VersionModel, NullWritable, Text, NullWritable> {
	private Text kout = new Text();
	
	@Override
	protected void reduce(VersionModel key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		String firstVersion = "";//记录初始版本号
		
		for(NullWritable niv : values){
			count++;
			if (count == 1) {
				firstVersion = key.getVersion();//第一次版本不需比较
				String kk = key.toString();
				kout.set(kk);
				context.write(kout, NullWritable.get());
			}else {
				if (!(firstVersion.equals(key.getVersion()))) {
					String kk = key.toString() + "\t" + firstVersion;
					kout.set(kk);
					context.write(kout, NullWritable.get());
				}else {
					String kk = key.toString();
					kout.set(kk);
					context.write(kout, NullWritable.get());
				}
				firstVersion = key.getVersion();//将比较完后的版本作为初始版本
			}
		}
		
	}
}
