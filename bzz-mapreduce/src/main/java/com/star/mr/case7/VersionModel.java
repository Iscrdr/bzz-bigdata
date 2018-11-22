package com.star.mr.case7;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 版本信息变动
 * @author: Yang qianli
 * @create: 2018-11-06 10:53
 * @version: 1.0.0
 */
public class VersionModel implements WritableComparable<VersionModel> {
	private String date;//日期
	private String name;//姓名
	private String game;//游戏
	private String hour;//小时
	private String source;//来源
	private String version;//版本
	private String city;//城市
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public String getHour() {
		return hour;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	public VersionModel() {
	}
	
	public VersionModel(String date, String name, String game, String hour, String source, String version, String city) {
		this.date = date;
		this.name = name;
		this.game = game;
		this.hour = hour;
		this.source = source;
		this.version = version;
		this.city = city;
	}
	
	
	@Override
	public int compareTo(VersionModel o) {
		if (this.name.equals(o.name)) {
			//先按照时间排序
			if (this.date.equals(o.date)) {
				//再次按照时间排序
				int hour1 = Integer.valueOf(this.hour);
				int hour2 = Integer.valueOf(o.getHour());
				if (hour1 == hour2) {
					return 0;
				}else {
					return hour1 - hour2 > 0 ? 1 : -1;
				}
				
			}else {
				return this.date.compareTo(o.date) > 0 ? 1 : -1;
			}
		}else {
			return this.name.compareTo(o.name) > 0 ? 1 : -1;
		}
		
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(date);
		out.writeUTF(name);
		out.writeUTF(game);
		out.writeUTF(hour);
		out.writeUTF(source);
		out.writeUTF(version);
		out.writeUTF(city);
		
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.date = in.readUTF();
		this.name = in.readUTF();
		this.game = in.readUTF();
		this.hour = in.readUTF();
		this.source = in.readUTF();
		this.version = in.readUTF();
		this.city = in.readUTF();
	}
	
	@Override
	public String toString() {
		return  date +" "+ name +" "+  game +" "+ hour +" "+  source +" "+  version +" "+ city ;
	}
}
