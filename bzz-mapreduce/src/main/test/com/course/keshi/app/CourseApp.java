package com.course.keshi.app;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

public class CourseApp {
	public static void main(String[] args)  {
		try{
			System.setProperty("hadoop.home.dir","D:\\software_study\\hadoop-2.7.5");
			Configuration conf = new Configuration();
			//conf.set("mapreduce.framework.name","local");
			//conf.set("fs.defaultFS","file:///");
			Job job = Job.getInstance(conf,"CourseApp");
			//job.setJar("D:\\code\\bzz-cloud\\bzz-bigdata\\bzz-mapredce\\target\\CourseApp.jar");
			job.setJobName("CourseApp");
			job.setJarByClass(CourseApp.class);
			job.setMapperClass(CourseMapper.class);
			job.setReducerClass(CourseReducer.class);
			
			/*job.setInputFormatClass(TextInputFormat.class);
			
			job.setOutputFormatClass(TextOutputFormat.class);*/
			/*job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);*/
			
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			
			File file = new File("D:\\code\\bzz-cloud\\bzz-bigdata\\bzz-mapreduce\\output");
			delete(file);
			FileInputFormat.addInputPath(job, new Path("D:\\code\\bzz-cloud\\bzz-bigdata\\bzz-mapreduce\\intput"));
			FileOutputFormat.setOutputPath(job, new Path("D:\\code\\bzz-cloud\\bzz-bigdata\\bzz-mapreduce\\output"));
			
			System.exit(job.waitForCompletion(true)?0:1);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	private static void delete(File file) {
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				delete(f);
				f.delete();
			}
		}
		file.delete();
	}

	
	public static class CourseMapper extends Mapper<Object, Text, Text, Text> {
		private Text keyText = new Text();
		private Text valueText = new Text();
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			int sec = 0;
			String  line  = value.toString();
			System.out.println(line);
			int endIndex = line.indexOf("分钟");
			int startIndex = endIndex - 6;
			if(endIndex > 6){
				String str = line.substring(startIndex, endIndex);
				if (str.contains(":")){
					String[] split = str.split(":");
					String secStr = split[1];
					sec = Integer.parseInt(secStr);
					if(!split[2].equals("00")) {
						sec = sec + 1;
					}
				}else {
					String sec2Str = str.substring(str.length() - 2, str.length());
					char c1 = sec2Str.charAt(0);
					char c2 = sec2Str.charAt(1);
					
					if(isInteger(c1+"")){
						sec = Integer.parseInt(sec2Str);
					}else {
						sec = Integer.parseInt(c2+"");
					}
					
				}
				keyText.set("course");
				valueText.set(""+sec);
				context.write(keyText,valueText);
			}
			
			
		}
	}
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	
	public static class CourseReducer extends Reducer<Text, Text, Text, Text> {
		private Text value = new Text();
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			int sec = 0;
			for(Iterator<Text> iterator = values.iterator(); iterator.hasNext();){
				
				int value = Integer.parseInt(iterator.next().toString());
				sec = sec + value;
			}
			System.out.println(sec+" : ========================");
			value.set(sec+"");
			context.write(key,value);
		}
	}
}
