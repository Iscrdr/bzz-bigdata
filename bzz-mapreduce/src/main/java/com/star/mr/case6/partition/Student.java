package com.star.mr.case6.partition;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description:
 * @author: Yang qianli
 * @create: 2018-11-06 08:52
 * @version: 1.0.0
 */
public class Student implements WritableComparable<Student> {
	private String course;//课程
	private String name;//姓名
	private String avg;//平均分
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAvg() {
		return avg;
	}
	
	public void setAvg(String avg) {
		this.avg = avg;
	}
	
	public Student() {
	}
	
	public Student(String course, String name, String avg) {
		this.course = course;
		this.name = name;
		this.avg = avg;
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(course);
		out.writeUTF(name);
		out.writeUTF(avg);
		
		
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		course = in.readUTF();
		name = in.readUTF();
		avg = in.readUTF();
	}
	
	@Override
	public int compareTo(Student student) {
		int num = Integer.valueOf(student.getAvg()) - Integer.valueOf(this.avg);
		if(num == 0){
			return 0;
		}
		if(num > 0){
			return 1;
		}
		if(num < 0){
			return -1;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return course +" "+ name +" "+ avg ;
	}
}
