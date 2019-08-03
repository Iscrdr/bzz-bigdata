package com.bzz.cloud;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Yang qianli
 * @version 1.0.0
 * @ProjectName bzz-bigdata
 * @Description: TODO
 * @email 624003618@qq.com
 * @date 2019-01-09 19:00:10
 */
public class KafkaApiImageTest {


    @Test
    public void testProducerImage(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "hadoop-01:9092,hadoop-02:9092,hadoop-03:9092,hadoop-04:9092,hadoop-05:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("max.request.size", 33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());

        Producer<String, byte[]> producer = null;

        try {
            producer = new KafkaProducer<>(properties);
            byte [] buf ;
            FileInputStream in = null;
            File file ;
            for (int i =3; i < 6; i++) {
                String src = "D:\\app\\image" + "\\" + i+".jpg";
                file = new File(src);
                in = new FileInputStream(file);
                //ByteArraySerializer bas = new ByteArraySerializer();
                buf = new byte[(int)file.length()];
                in.read(buf,0,(int)file.length());
                /*while(in.read(buf) != -1){
                }*/
                producer.send(new ProducerRecord<>("fileimage",buf));

                System.out.println(src);

            }
            if(in!=null){
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }
    }

    @Test
    public void testConsumerImage() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "hadoop-01:9092,hadoop-02:9092,hadoop-03:9092,hadoop-04:9092,hadoop-05:9092");
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("fetch.message.max.bytes", "10485760");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        KafkaConsumer<String, byte[]> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("fileimage"));
        String src = "D:\\app\\image1";
        int i =0;
        while (true) {
            ConsumerRecords<String, byte[]> records = kafkaConsumer.poll(100);
            System.out.println(records.count());
            for (ConsumerRecord<String, byte[]> record : records) {
                i++;
                String topic = record.topic();
                byte[] value = record.value();
                String dst1 = "D:\\app\\image1" + "\\" + i+".jpg";
                    try {

                        File file = new File(dst1);
                        if(file.exists()){
                            System.out.println(dst1);
                            file.delete();
                        }
                        file.createNewFile();
                        FileOutputStream out = new FileOutputStream(dst1,true);
                        out.write(value);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                System.out.println(topic);


            }

        }
    }
}
