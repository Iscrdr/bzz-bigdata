package com.bzz.cloud.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @program: bzz-bigdata
 * @description: kafka自定义分区
 * @author: 624003618@qq.com
 * @create: 2019-12-24 18:02
 */
public class MyPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer integer = cluster.partitionCountForTopic(topic);
        return key.toString().hashCode() % integer;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
