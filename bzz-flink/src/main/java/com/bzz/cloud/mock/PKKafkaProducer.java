package com.bzz.cloud.mock;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * @program: bzz-bigdata
 * @description: mock数据到kafka
 * @author: 624003618@qq.com
 * @create: 2019-09-19 16:13
 */
public class PKKafkaProducer {
    public static void main(String[] args) throws InterruptedException {

        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers",
                        "hadoop-01:9092," +
                        "hadoop-02:9092," +
                        "hadoop-03:9092," +
                        "hadoop-04:9092," +
                        "hadoop-05:9092");
        prop.setProperty("key.serializer", StringSerializer.class.getName());
        prop.setProperty("value.serializer", StringSerializer.class.getName());
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(prop);
        String topic = "pktest";
        //发送数据
        while (true){
            StringBuilder builder = new StringBuilder();
            builder.append("imooc").append("\t")
                    .append("CN").append("\t")
                    .append(getLevels()).append("\t")
                    .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\t")
                    .append(getIps()).append("\t")
                    .append(getDomains()).append("\t")
                    .append(getTraffic()).append("\t")
                    ;
            System.out.println(builder.toString());
            producer.send(new ProducerRecord<String,String>(topic,builder.toString()));
            Thread.sleep(1000);
        }




    }
    public static long getTraffic() {
        return  new Random().nextInt(10000);

    }
    public static String getDomains() {
        String [] domains = new String[]{
                "v1.go2yd.com",
                "v2.go2yd.com",
                "v3.go2yd.com",
                "v4.go2yd.com",
                "vmi.go2yd.com"
        };


        return domains[ new Random().nextInt(domains.length)];

    }

    public static String getIps() {
        String [] ips = new String[]{
                "223.104.18.110",
                "113.101.75.194",
                "27.17.127.135",
                "183.225.139.16",
                "112.1.66.34",
                "175.148.211.190",
                "183.227.58.21",
                "59.83.197.84",
                "117.28.38.28",
                "117.59.39.169"
        };


        return ips[ new Random().nextInt(ips.length)];

    }

    public static String getLevels(){
        String [] levels = new String[]{"M","E"};
        return levels[ new Random().nextInt(levels.length)];
    }

}
