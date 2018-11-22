package com.spark.scala.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Title: SortWord2
  * @ProjectName bzz-cloud
  * @Auther: clouder
  * @Date: 2018-07-14-1:35
  * @Description:
  */
object SortWord2 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("SortWord").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)

    val lines = sparkContext.textFile("D:\\app\\word.txt")

    val paidSort = lines.map(line => (
      new SecondSortByKey(line.split(" ")(0), line.split(" ")(1).toInt),
      line
      ))


    val pairSortRdd = paidSort.sortByKey(false,1).map(word=>word._2)
    pairSortRdd.foreach(word => println(word))
  }

}
