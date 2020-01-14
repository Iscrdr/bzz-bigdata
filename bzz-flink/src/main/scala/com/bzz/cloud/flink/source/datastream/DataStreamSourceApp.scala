package com.bzz.cloud.flink.source.datastream

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-09 18:04
  *
  */
object DataStreamSourceApp {
  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    //socketFunction(environment)
    //custormNonParalleSourceFunction(environment)
    //custormParalleSourceFunction(environment)
    custormRichParallelSourceFuntion(environment)
    environment.execute("DataStreamSourceApp")



  }
  def custormRichParallelSourceFuntion(env:StreamExecutionEnvironment):Unit = {
    val data = env.addSource(new CustormRichParallelSourceFuntion())
    data.setParallelism(2).print()
  }

  def custormParalleSourceFunction(env:StreamExecutionEnvironment):Unit = {
    val data = env.addSource(new CustormParalleSourceFunction())
    data.setParallelism(2).print()
  }

  def custormNonParalleSourceFunction(env:StreamExecutionEnvironment):Unit = {
    val data = env.addSource(new CustormNonParalleSourceFunction())
    data.print()
  }

  def socketFunction(env: StreamExecutionEnvironment): Unit ={
    val data = env.socketTextStream("localhost",9999)
    data.setParallelism(1).print()

  }

}
