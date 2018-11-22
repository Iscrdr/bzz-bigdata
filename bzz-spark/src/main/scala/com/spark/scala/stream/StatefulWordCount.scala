package com.spark.scala.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StatefulWordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")

    val streamingContext = new StreamingContext(sparkConf,Seconds(5))

    streamingContext.checkpoint(".")

    val lines = streamingContext.socketTextStream("localhost",80)

    val result  = lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)




    streamingContext.start()
    streamingContext.awaitTermination()


  }

}
