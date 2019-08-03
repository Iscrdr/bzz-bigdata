package com.spark.scala.study


import org.apache.commons.lang3.StringUtils
import org.apache.kafka.common.utils.Time
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @Author : yang qianli 
  * @email: 624003618@qq.com
  * @Date: 2019-三月-18 13-23
  * @Modified by:
  * @Description:
  */
object WindPlusStream {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    System.setProperty("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sparkConf = new SparkConf().setAppName("LauncherStreaming")

    //每60秒一个批次
    val ssc = new StreamingContext(sparkConf, Seconds(60))

    // 从Kafka中读取数据
    val kafkaStream = KafkaUtils.createStream(
      ssc,
      "hadoop-03:2181,hadoop-04:2181,hadoop-05:2181", // Kafka集群使用的zookeeper
      "launcher-streaming", // 该消费者使用的group.id
      Map[String, Int]("launcher_click" -> 0, "launcher_click" -> 1), // 日志在Kafka中的topic及其分区
      StorageLevel.MEMORY_AND_DISK_SER).map(_._2) // 获取日志内容

    kafkaStream.print()
    ssc.start()
    // 等待实时流
    ssc.awaitTermination();
  }

}
