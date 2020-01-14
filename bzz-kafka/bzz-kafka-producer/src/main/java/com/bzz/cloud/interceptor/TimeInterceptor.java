package com.bzz.cloud.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @program: bzz-bigdata
 * @description: kafka生产者：时间拦截器
 * @author: 624003618@qq.com
 * @create: 2019-12-25 03:40
 */
public class TimeInterceptor implements ProducerInterceptor<String,String> {
    @Override
    public void configure(Map<String, ?> configs) {

    }

    @Override
    public ProducerRecord onSend(ProducerRecord<String,String> record) {
        String value = record.value();


        return new ProducerRecord(record.topic(),
                record.partition(),
                record.key(),
                System.currentTimeMillis()+","+value,
                record.headers()
        );
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }


}
