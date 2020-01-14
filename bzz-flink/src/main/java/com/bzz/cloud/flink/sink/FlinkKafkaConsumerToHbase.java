package com.bzz.cloud.flink.sink;

import com.bzz.cloud.flink.custormsource.CCustSale;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.calcite.shaded.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @program: bzz-bigdata
 * @description: flink消费kafka中的数据，批量插入hbase
 * @author: 624003618@qq.com
 * @create: 2019-10-24 18:44
 */
public class FlinkKafkaConsumerToHbase {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String topic = "ccustsale";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers",
                "xiaostarstar:9092" );


        properties.setProperty("zookeeper.connect",
                "xiaostarstar:2181");

        properties.setProperty("group.id","gccustsale");

        DataStreamSource dataStreamSource = env.addSource(new FlinkKafkaConsumer(topic, new SimpleStringSchema(), properties));


        SingleOutputStreamOperator<CCustSale> map = dataStreamSource.map((MapFunction<String, CCustSale>) value -> {
            String[] split = value.split(",");
            CCustSale cCustSale = new CCustSale();
            cCustSale.setId(split[0]);
            cCustSale.setGongchang(split[1]);
            cCustSale.setDaqu(split[2]);
            cCustSale.setChengshi(split[3]);
            cCustSale.setYewuyuan(split[4]);
            cCustSale.setCustNo(split[5]);
            cCustSale.setCustName(split[6]);
            cCustSale.setDapinleimiaoshu(split[7]);
            cCustSale.setYijipinleimiaoshu(split[8]);
            cCustSale.setErjipinleimiaoshu(split[9]);
            cCustSale.setSanjipinleimiaoshu(split[10]);
            cCustSale.setChanpinxianmiaoshu(split[11]);
            cCustSale.setWuliaobianma(split[12]);
            cCustSale.setWuliaomiaoshu(split[13]);
            cCustSale.setXiang(split[14]==null?0.00:Double.valueOf(split[14]));
            cCustSale.setDun(split[15]==null?0.00:Double.valueOf(split[15]));
            cCustSale.setXiaoshoushouru(split[16]==null?0.00:Double.valueOf(split[16]));
            cCustSale.setJingzhi(split[17]==null?0.00:Double.valueOf(split[17]));
            cCustSale.setShuie(split[18]==null?0.00:Double.valueOf(split[18]));
            cCustSale.setZhanlvjine(split[19]==null?0.00:Double.valueOf(split[19]));
            cCustSale.setZhekoujine(split[20]==null?0.00:Double.valueOf(split[20]));
            cCustSale.setZhekoubili(split[21]==null?0.00:Double.valueOf(split[21]));
            cCustSale.setShoudafangjiancheng(split[22]);
            String s = split[23];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fapiaoshiqi = sdf.parse(s);
            cCustSale.setFapiaoshiqi(fapiaoshiqi);
            cCustSale.setDingdanbianhao(split[24]);
            Date danjuriqi = sdf.parse(split[25]);
            cCustSale.setDanjuriqi(danjuriqi);
            cCustSale.setKucundidian(split[26]);
            cCustSale.setCaigoubianhao(split[27]);


            cCustSale.setRemarks(split[28]);
            cCustSale.setCreateBy(split[29]);
            //Date createDate = sdf.parse(split[30]);
            cCustSale.setCreateDate(fapiaoshiqi);
            cCustSale.setUpdateBy(split[31]);
            //Date updateDate = sdf.parse(split[32]);
            cCustSale.setUpdateDate(fapiaoshiqi);
            cCustSale.setDelFlag("0");
            return cCustSale;
        });

        SingleOutputStreamOperator<List<CCustSale>> cc = map.timeWindowAll(Time.seconds(5)).apply(new AllWindowFunction<CCustSale, List<CCustSale>, TimeWindow>() {

            @Override
            public void apply(TimeWindow window, Iterable<CCustSale> values, Collector<List<CCustSale>> out) throws Exception {
                ArrayList<CCustSale> cCustSales = Lists.newArrayList(values);
                if (cCustSales.size() > 0) {
                    System.out.println("5 秒内收集到 CCustSale 的数据条数是：" + cCustSales.size());
                    out.collect(cCustSales);
                }
            }
        });
        cc.addSink(new SinkToHbase());

      /* map.timeWindowAll(Time.seconds(5)).apply((AllWindowFunction<CCustSale, List<CCustSale>, TimeWindow>) (window, values, out) -> {
            ArrayList<CCustSale> cCustSales = Lists.newArrayList(values);
            if (cCustSales.size() > 0) {
                System.out.println("1 分钟内收集到 employee 的数据条数是：" + cCustSales.size());
                out.collect(cCustSales);
            }
        }).print();*/
                //.addSink(new SinkToHbase());


        env.execute("FlinkKafkaConsumerToHbase");

    }
}
