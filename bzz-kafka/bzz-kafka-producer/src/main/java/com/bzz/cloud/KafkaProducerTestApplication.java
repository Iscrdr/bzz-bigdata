package com.bzz.cloud;


import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @description：springboot 整合 kafka,生产者
 * @author     ：Iscrdr
 * @date       ：2020/01/12 17:47
 * @email      ：624003618@qq.com
 * @modified By：
 * @version:     1.0.0
 */
@SpringBootApplication
public class KafkaProducerTestApplication implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(KafkaProducerTestApplication.class);

    public static void main( String[] args ) {
        SpringApplication.run(KafkaProducerTestApplication.class,args).close();
    }

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {

        for (int i=0;i<10;i++){
            RecordMetadata recordMetadata = kafkaTemplate.send("topic-test", "kafka_" + i).get().getRecordMetadata();

            logger.info("topic:{},partition:{},offset:{},timestamp:{}",
                    recordMetadata.topic(),recordMetadata.partition(),recordMetadata.offset(),recordMetadata.timestamp());
            Thread.sleep(1000);
        }
    }

}
