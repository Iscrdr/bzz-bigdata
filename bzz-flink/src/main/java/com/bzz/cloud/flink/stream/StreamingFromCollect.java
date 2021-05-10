package com.bzz.cloud.flink.stream;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bzz-bigdata
 * @description: 从集合中获取数据源
 * @author: 624003618@qq.com
 * @create: 2019-08-04 18:44
 */
public class StreamingFromCollect {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        DataStreamSource<Integer> listData = environment.fromCollection(list);
        SingleOutputStreamOperator<Integer> filter = listData.filter(new FilterFunction<Integer>() {

            @Override
            public boolean filter(Integer value) throws Exception {
                if (value % 2 == 0) {
                    return false;
                }
                return true;
            }
        });
        filter.print().setParallelism(1);
        environment.execute("StreamingFromCollect");
    }
}
