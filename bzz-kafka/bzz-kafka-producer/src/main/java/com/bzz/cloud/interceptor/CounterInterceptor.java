package com.bzz.cloud.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @program: bzz-bigdata
 * @description: kafka计数拦截器
 * @author: 624003618@qq.com
 * @create: 2019-12-25 03:45
 */
public class CounterInterceptor implements ProducerInterceptor {

    int successCount;
    int errorCount;

    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (metadata != null){
            successCount++;
        }else {
            errorCount++;
        }

    }

    @Override
    public void close() {
        System.out.println("success:"+successCount);
        System.out.println("errorCount:"+errorCount);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
