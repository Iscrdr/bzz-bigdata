package com.spark.scala.stream

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object TranformApp {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("TransformApp")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    //构建黑名单列表, 实际应用中可在外面读取列表, 并转成RDD, 用true标记为是黑名单元组(name, true)
    val blacks = List("zs", "ls")
    val blacksRDD = ssc.sparkContext.parallelize(blacks).map(x => (x, true))

    //获取每行
    val lines = ssc.socketTextStream("localhost", 6789)
    //把id, name => 元组(name, (id, name))
    //transform 的使用，对stream的每个RDD操作
    val filterResult = lines.map(x => (x.split(",")(1), x)).transform(rdd => {
      //与黑名单进行leftjoin => (name, ((id, name), true)), 并过滤出是true的项
      rdd.leftOuterJoin(blacksRDD)
        .filter(x => x._2._2.getOrElse(false) != true)   //过滤出不等于true的
        .map(x => x._2._1)
    })
    filterResult.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
