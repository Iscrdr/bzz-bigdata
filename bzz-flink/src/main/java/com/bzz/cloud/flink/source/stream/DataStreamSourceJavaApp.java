package com.bzz.cloud.flink.source.stream;


import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;

/**
 * @program: bzz-bigdata
 * @description: 根据给定的 fileInputFormat 和读取路径读取文件。根据提供的 watchType，这个 source 可以定期（每隔 interval 毫秒）监测给定路径的新数据
 * @author: 624003618@qq.com
 * @create: 2019-10-15 07:56
 */
public class DataStreamSourceJavaApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String filePath = "D:\\testdata";
        /*
         * 监控的是文件中的新数据，非文件夹,如果文件有新数据，则重新读取所有的数据
         */
        DataStreamSource<String> dataSource = env.readFile(
                new TextInputFormat(new Path(filePath)),
                filePath,
                FileProcessingMode.PROCESS_CONTINUOUSLY, 100
        );
        dataSource.print().setParallelism(2);
        env.execute("DataStreamSourceJavaApp");
    }

}

