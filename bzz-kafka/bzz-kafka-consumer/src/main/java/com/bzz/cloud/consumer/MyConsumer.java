package com.bzz.cloud.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * @program: bzz-bigdata
 * @description: kafka消费者
 * @author: 624003618@qq.com
 * @create: 2019-12-24 18:27
 */
public class MyConsumer {
    public static void main(String[] args) {
        //1,设置kafka生产者配置信息
        Properties prop = new Properties();
        //2,设置kafka集群
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.132.150:9092");
        //3,开启自动提交
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        //4,设置自动提交延迟 1000ms
        prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");

        //5,指定key和value反序列化类型
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        //6,消费者组
        prop.put(ConsumerConfig.GROUP_ID_CONFIG,"myConsumer11");

        //7,设置offset,earliest从最早的消息开始消费，latest从没有消费过的消息开始消费
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);

        //7,订阅主题
        consumer.subscribe(Arrays.asList("first", "second"));

        //8.拉取数据
        ConsumerRecords<String, String> data = consumer.poll(1000);
        Iterable<ConsumerRecord<String, String>> it = data.records("first");
        Iterator<ConsumerRecord<String, String>> iterator = it.iterator();
        while (iterator.hasNext()){
            ConsumerRecord<String, String> next = iterator.next();
            System.out.println(next.key()+":"+next.value());
        }

        //consumer.close();
    }
}
