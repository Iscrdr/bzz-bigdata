package com.spark.scala

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object FileWorlCount {
  def main(args: Array[String]): Unit = {
    var sparkConf = new SparkConf().setMaster("local[2]").setAppName("FileWorlCount")

    var streamingContext = new StreamingContext(sparkConf,Seconds(5))

    val lines = streamingContext.textFileStream("file:///D://app/spark/")
    val result = lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _)

    result.print()

    streamingContext.start()
    streamingContext.awaitTermination()

  }

}
