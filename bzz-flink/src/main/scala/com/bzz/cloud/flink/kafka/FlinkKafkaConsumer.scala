package com.bzz.cloud.flink.kafka

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-18 17:56
  *
  */
object FlinkKafkaConsumer {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val topic = "pktest"
    val properties = new Properties()
    properties.setProperty("bootstrap.servers",
        "hadoop-01:9092," +
        "hadoop-02:9092," +
        "hadoop-03:9092," +
        "hadoop-04:9092," +
        "hadoop-05:9092")
    properties.setProperty("group.id","test")

    val data = env.addSource(new FlinkKafkaConsumer[String](topic,new SimpleStringSchema(),properties))


    env.execute("FlinkKafkaConsumer")
  }

}
