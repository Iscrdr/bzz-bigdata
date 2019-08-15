package com.bzz.cloud.flink.batch;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.LocalEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.SortPartitionOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.FileSystem;

import java.util.Arrays;

/**
 * @program: bzz-flink
 * @description: 批处理
 * @author: 624003618@qq.com
 * @create: 2019-08-03 17:57
 */
public class BatchWordCountJava {
    public static void main(String[] args) throws Exception {
       // ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        LocalEnvironment env = ExecutionEnvironment.createLocalEnvironment();


        String path = "D:\\test\\in.txt";
        String outPath = "D:\\test\\out.csv";
        DataSource<String> text = env.readTextFile(path);

        /*DataSet<Tuple2<String, Integer>> wordCounts = text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String line, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = line.split(" ");
                for (String word : words) {
                    out.collect(new Tuple2<String, Integer>(word, 1));
                }
            }
        }).groupBy(0).sum(1).sortPartition(1,Order.DESCENDING);
*/

        /*
         * lambda方式,注意泛型擦除，.returns(Types.TUPLE(Types.STRING, Types.INT))
         */
        DataSet<Tuple2<String, Integer>> wordCounts1 = text.flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (line, out) -> {
            Arrays.stream(line.split(" ")).forEach(word -> out.collect(new Tuple2<>(word, 1)));
        }).returns(Types.TUPLE(Types.STRING, Types.INT)).groupBy(0).sum(1);

        wordCounts1.print();
        wordCounts1.writeAsCsv(outPath, FileSystem.WriteMode.OVERWRITE).setParallelism(1);
        env.execute("BatchWordCountJava");


    }

}
