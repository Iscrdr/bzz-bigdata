package com.bzz.cloud.hbase.mapreducer;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class TestHBaseMapReduce extends Configured implements Tool {
    // 使用TableMapper读取HBase表的数据
    public static class ReadHBaseMapper extends TableMapper<ImmutableBytesWritable, Put> {
        protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
            //读取表，每行作为一个输入，并取出了rowkey
            Put put = new Put(key.get());
            //遍历结果集
            for (Cell cell : value.rawCells()) {
                //判断当前列簇是不是info，再判断列是不是name


                if ("info".equals(Bytes.toString(CellUtil.cloneFamily(cell)))){

                    if ("name".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))){
                        String columnFamily = Bytes.toString(CellUtil.cloneFamily(cell));
                        System.out.println(columnFamily+"===================================");
                        //将info:name列放入put
                        put.add(cell);
                    }
                }


            }
            //map输出
            context.write(key,put);
        }
    }
    public int run(String[] args) throws Exception {
        Configuration conf = this.getConf();
        Job job = Job.getInstance(conf, "hbase-mr");//job名称
        job.setJarByClass(TestHBaseMapReduce.class);
        //job.setJar("D:\\code\\bzz-bigdata\\bzz-hbase\\target\\bzz-hbase.jar");

        Scan scan = new Scan();
        scan.setCacheBlocks(false);//不需要缓存数据
        scan.setCaching(500);//每次从服务端读取的行数
        //设置任务参数
        TableMapReduceUtil.initTableMapperJob("stuinfo",
                scan,
                ReadHBaseMapper.class,
                ImmutableBytesWritable.class,
                Put.class,
                job);
        TableMapReduceUtil.initTableReducerJob("stuinfo_cp",
                null,
                job);
        //提交job
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        //读取配置文件
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.rootdir", "hdfs://hadoop-cloud:9000/hbase");
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        // the node of zookeeper
        conf.set("hbase.zookeeper.quorum", "hadoop-03:2181,hadoop-04:2181,hadoop-05:2181");
        int status = ToolRunner.run(conf, new TestHBaseMapReduce(), args);
        System.exit(status);
    }
}


