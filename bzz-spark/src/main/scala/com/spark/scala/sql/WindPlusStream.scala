package com.spark.scala.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.immutable


/**
 * @Author : yang qianli
 * @email: 624003618@qq.com
 * @Date: 2019-三月-18 14-42
 * @Modified by:
 * @Description:
 */
object WindPlusStream {
  def main(args: Array[String]) {
        // 1、创建sparkConf
        val sparkConf: SparkConf = new SparkConf()

            .setAppName("SparkStreamingKafka_Receiver")
            .setMaster("local[2]")
            .set("spark.streaming.receiver.writeAheadLog.enable","true")
        //开启wal预写日志，保存数据源的可靠性
    // 2、创建sparkContext
        val sc = new SparkContext(sparkConf)
      sc.setLogLevel("WARN")
      //3、创建StreamingContext
        val ssc = new StreamingContext(sc,Seconds(5))
        //设置checkpoint
        ssc.checkpoint("./Kafka_Receiver")
      //4、定义zk地址
       val zkQuorum="hadoop-01:2181,hadoop-02:2181,hadoop-03:2181";
        //5、定义消费者组
        val groupId = "windplus_receiver"

        val topics=Map("windplus" -> 2)
        val receiverDstream: immutable.IndexedSeq[ReceiverInputDStream[(String, String)]] = (1 to 3).map(x => {
        val stream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, zkQuorum, groupId, topics)
        stream
    })
       val unionDStream: DStream[(String, String)] = ssc.union(receiverDstream)
    val topicData: DStream[String] = unionDStream.map(_._2)
    val wordAndOne: DStream[(String, Int)] = topicData.flatMap(_.split(" ")).map((_,1))
    val result: DStream[(String, Int)] = wordAndOne.reduceByKey(_+_)
    //11、打印输出
    result.print()
    //开启计算
    ssc.start();
    ssc.awaitTermination();
  }

}
