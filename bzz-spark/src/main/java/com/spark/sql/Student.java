package com.spark.sql;

import java.io.Serializable;

/**
 * @Title: Student
 * @ProjectName bzz-cloud
 * @Auther: clouder
 * @Date: 2018-07-17-1:47
 * @Description:
 */

public class Student implements Serializable {
	private int id;
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
