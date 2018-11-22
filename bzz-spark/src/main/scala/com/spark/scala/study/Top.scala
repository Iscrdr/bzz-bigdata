package com.spark.scala.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Title: Top
  * @ProjectName bzz-cloud
  * @Auther: clouder
  * @Date: 2018-07-16-11:08
  * @Description:
  */
object Top {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("top")
    val sparkContext = new SparkContext(sparkConf)

    val textFile = sparkContext.textFile("D:\\app\\top.txt")
    val lines = textFile.map(word => (word.toInt,word))
    val line = lines.sortByKey(false)
    val tuples = line.take(3)
    tuples.foreach(tuple => println(tuple._1+":" + tuple._2))
  }
}
