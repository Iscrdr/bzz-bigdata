package com.bzz.cloud.flink.counter

import org.apache.flink.api.common.accumulators.LongCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.{ExecutionEnvironment, _}
import org.apache.flink.configuration.Configuration

/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-06 18:09
  *
  */
object DataSetCounter {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val data = env.fromElements("hadoop","spark","flink","storm","hadoop")


   /* data.map(new RichMapFunction[String,Long]() {
      var counter  = 0
      override def map(value: String): Long = {
        counter = counter + 1
        println("counter: "+counter)
        counter
      }
    }).setParallelism(3).print()*/

    val value: DataSet[String] = data.map(new RichMapFunction[String, String]() {
      //step1:定义计数器
      val counter = new LongCounter()

      //注册计数器
      override def open(parameters: Configuration): Unit = {
        getRuntimeContext.addAccumulator("ele-count", counter)
      }

      override def map(value: String): String = {
        counter.add(1)
        value
      }
    }).setParallelism(3)
    value.writeAsText("D:\\test")



    val jobExecutionResult = env.execute("DataSetCounter")

    //获取计数器
    val counterResult = jobExecutionResult.getAccumulatorResult[Long]("ele-count")
    println("counterResult: " + counterResult)



  }





}
