package com.spark.scala.study

import org.apache.spark.{SparkConf, SparkContext}


object ParallizeCollection {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("local").setMaster("local")
    val sparkContext = new SparkContext(conf)

    val nums : List[Int] = List(1, 2, 3, 4,5,6,7,8,9,10)
    val numRDD = sparkContext.parallelize(nums,5)
    val num = numRDD.reduce(_ + _)
    println("和："+num)

  }
}
