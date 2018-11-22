package com.star.mr.case5.Friends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @program: bzz-bigdata
 * @description: 求平均成绩 Mapper
 * @author: Yang qianli
 * @create: 2018-10-23 20:30
 * @version: 1.0.0
 */
public class FriendsMapper extends Mapper<LongWritable, Text, Text, Text> {
	private Text keys = new Text();
	private Text values = new Text();
	
	/**
	 * 输入：
	 * A:B,C,D,F,E,O
	 * B:A,C,E,K
	 * 输出：
	 * A-B:C,D,F,E,O
	 * A-C:B,D,F,E,O
	 * A-D:B,C,F,E,O
	 * A-F:B,C,D,E,O
	 * A-E:B,C,D,F,O
	 * A-O:B,C,D,F,E
	 *
	 * B-A:C,E,K
	 * B-C:A,E,K
	 * B-E:A,C,K
	 * B-K:A,C,E
	 *
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
		String friendA = split[0];
		String friends = split[1];
		
		String[] split1 = friends.split(",");
		for(int i=0;i<split1.length;i++){
			
			/*if(friendA.compareTo(split1[i]) <=0 ){
				keys.set(friendA+"-"+split1[i]);
			}else {
				keys.set(split1[i]+"-"+friendA);
			}*/
			/*String fis ;
			if(friends.endsWith(split1[i])){
				fis = friends.replace(","+split1[i], "");
			}else{
				fis = friends.replace(split1[i]+",", "");
			}
			values.set(fis);*/
			keys.set(split1[i]);
			values.set(friendA);
			
			//System.out.println(keys+":"+fis);
			context.write(keys,values);
		}
	}
}
