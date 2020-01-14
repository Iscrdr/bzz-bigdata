package com.bzz.cloud.flink.timeAndWindow

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-18 16:28
  *
  */
object WindowsApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text: DataStream[String] = env.socketTextStream("localhost",9999)
    text.flatMap(_.split(","))
      .map((_,1))
      .keyBy(0)
      //.timeWindow(Time.seconds(5))
      .timeWindow(Time.seconds(10),Time.seconds(5))
      .sum(1)
      .print()
      .setParallelism(1)

    env.execute("WindowsApp")


  }

}
