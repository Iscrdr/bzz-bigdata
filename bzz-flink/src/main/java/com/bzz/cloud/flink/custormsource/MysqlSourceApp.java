package com.bzz.cloud.flink.custormsource;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;
import java.util.Properties;

/**
 * @program: bzz-bigdata
 * @description: 测试flink之mysql数据源
 * @author: 624003618@qq.com
 * @create: 2019-08-12 19:58
 */
public class MysqlSourceApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //并行数据源
        SingleOutputStreamOperator<String> soso1 = env.addSource(new MysqlParalleSource()).setParallelism(2);

        //非并行数据源
        SingleOutputStreamOperator<String> soso2 = env.addSource(new MysqlSource());
        //DataStreamSource<List> listDataStreamSource = env.addSource(new ExcelSource("E:\\excetodb\\福临门2018-06月销售.xlsx"));

        /*String topic = "ccustsale";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers",
                "xiaostarstar:9092" );*/

        //properties.setProperty("group.id","gccustsale");

        //soso.addSink(new FlinkKafkaProducer(topic, new SimpleStringSchema(), properties)).setParallelism(2);

        soso2.print();
        env.execute("MysqlSourceApp");

    }
}
