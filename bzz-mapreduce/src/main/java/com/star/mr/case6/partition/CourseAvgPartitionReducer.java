package com.star.mr.case6.partition;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 统计每门课程的参考人数和课程的平均分
 * @author: Yang qianli
 * @create: 2018-11-06 07:47
 * @version: 1.0.0
 */
public class CourseAvgPartitionReducer extends Reducer<Student, NullWritable,Student, NullWritable> {
	private Text kout = new Text();
	private Text vout = new Text();
	
	@Override
	protected void reduce(Student key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
		context.write(key, NullWritable.get());
	
	
	}
	
}
