package com.star.mr.case6.partition;


import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @program: bzz-bigdata
 * @description:
 * @author: Yang qianli
 * @create: 2018-11-06 08:51
 * @version: 1.0.0
 */
public class CourseAvgPartition extends Partitioner<Student, NullWritable> {
	
	@Override
	public int getPartition(Student student, NullWritable nullWritable, int i) {
		if(student.getCourse().startsWith("math"))
			return 0;
		else if (student.getCourse().startsWith("english"))
			return 1;
		else if (student.getCourse().startsWith("computer"))
			return 2;
		return 3;
	}
}
