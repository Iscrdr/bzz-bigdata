package com.bzz.cloud.flink.transformation


import java.{lang, util}

import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-09 18:45
  *
  */
object DataStreamTransformationScalaApp {
  def main(args: Array[String]): Unit = {
    val env  = StreamExecutionEnvironment.getExecutionEnvironment

    //filterFunction(env)
    splitSelectFunction(env)
    env.execute("DataStreamTransformationScalaApp")
  }

  def splitSelectFunction(env:StreamExecutionEnvironment): Unit ={
    val data = env.socketTextStream("localhost",9999)

    val splits = data.split(new OutputSelector[String] {
      override def select(out: String): lang.Iterable[String] = {
        val arr = new util.ArrayList[String]()
        if (out.contains("flink")) {
          arr.add("flink")
        }else{
          arr.add("hadoop")
        }
        arr
      }
    })
    splits.select("flink","hadoop").print()
  }

  def filterFunction(env:StreamExecutionEnvironment): Unit ={
    val data = env.socketTextStream("localhost",9999)
    data.map(x => x.split(" ")).filter(!_.equals("flink")).print()
  }
}
