package com.bzz.cloud.flink.timeAndWindow

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-18 16:28
  *
  */
object WindowsReduceApp {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text: DataStream[String] = env.socketTextStream("localhost",9999)
    text.flatMap(_.split(","))
      .map(x => (1,x.toInt))
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .reduce{(v1,v2)=>(v1._1,v1._2+ v2._2)}
      .print()
      .setParallelism(1)

    env.execute("WindowsReduceApp")


  }

}
