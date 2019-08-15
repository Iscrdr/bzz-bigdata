package com.bzz.cloud.flink

import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.windowing.time.Time


object SocketWindowWordCountScala {
  def main(args: Array[String]): Unit = {

    case class WordWithCount (word:String,count:Long)

    val hostName = "192.168.132.150"
    val port = 9090
    val delimiter = '\n'
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream(hostName,port,delimiter)

    val windowCounts = text.flatMap(line =>line.split("\\s"))
      .map(word => WordWithCount(word,1))
      .keyBy("word")
      .timeWindow(Time.seconds(2), Time.seconds(1))
      .sum(1)
      //.sum("word")

    windowCounts.print().setParallelism(1)

    env.execute("SocketWindowWordCountScala")

  }


}
