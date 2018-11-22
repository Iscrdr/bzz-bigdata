package com.star.mr.case7;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @program: bzz-bigdata
 * @description: 排序
 * @author: Yang qianli
 * @create: 2018-11-06 11:32
 * @version: 1.0.0
 */
public class VersionGroup extends WritableComparator {
	public VersionGroup() {
		super(VersionModel.class,true);//创建对象
	}
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		VersionModel v1 = (VersionModel)a;
		VersionModel v2 = (VersionModel)b;
		return v1.getName().compareTo(v2.getName());
	}
}
