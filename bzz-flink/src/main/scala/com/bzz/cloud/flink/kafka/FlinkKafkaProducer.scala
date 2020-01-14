package com.bzz.cloud.flink.kafka

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.util.serialization.KeyedSerializationSchema
/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-18 18:31
  *
  */
object FlinkKafkaProducer {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //从socket接收数据，通过Flink,将数据sink到kafka
    val socketData = env.socketTextStream("localhost",9999)

    val topic = "test"
    val properties = new Properties()
    properties.setProperty("bootstrap.servers",
      "hadoop-01:9092," +
        "hadoop-02:9092," +
        "hadoop-03:9092," +
        "hadoop-04:9092," +
        "hadoop-05:9092")
    properties.setProperty("group.id","gtest")

    val kafkaSink = new FlinkKafkaProducer[String](topic,new SimpleStringSchema,properties)
    socketData.addSink(kafkaSink)
    socketData.print()
    env.execute("FlinkKafkaProducer")
  }

}
