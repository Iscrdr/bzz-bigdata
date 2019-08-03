package com.bzz.cloud.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;


/**
 * @author cloud
 */
public class SocketWindowWordCountJava {
    public static void main(String[] args) throws Exception {

        String hostname = "192.168.132.150";
        int port = 0;
        try {
            ParameterTool parameterTool = ParameterTool.fromArgs(args);
            port = parameterTool.getInt("port");
        } catch (Exception e) {
            System.out.println("使用默认的端口号：9090");
            port = 9090;
            e.printStackTrace();
        }


        //获取flink的运行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> text = env.socketTextStream(hostname, port, "\n");
        SingleOutputStreamOperator<WordWithCount> wordWithCounts = text.flatMap(new FlatMapFunction<String, WordWithCount>() {
            @Override
            public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                String[] split = value.split("\\s");
                for (String word : split) {
                    out.collect(new WordWithCount(word, 1L));
                }
            }
        })
                .keyBy("word")
                .timeWindow(Time.seconds(2), Time.seconds(1))
                .sum("count");


        //打印到控制台，并设置并行度
        wordWithCounts.print().setParallelism(1);
        env.execute("Socket window count");
    }

    public static class WordWithCount {

        public String word;
        public long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }
        @Override
        public String toString() {
            return "WordWithCount{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
