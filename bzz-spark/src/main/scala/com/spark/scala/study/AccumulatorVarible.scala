package com.spark.scala.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Title: AccumulatorVarible
  * @ProjectName bzz-cloud
  * @Auther: clouder
  * @Date: 2018-07-13-23:20
  * @Description:
  */
object AccumulatorVarible {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("AccumulatorVarible")
    val sparkContext = new SparkContext(sparkConf)

    val nums : List[Int] = List(1,2,3,4,5,6,7,8,9,10)

    val accumulator = sparkContext.longAccumulator

    val numberList = sparkContext.parallelize(nums)
    numberList.foreach(num => {
      accumulator.add(num)
    })
    println(accumulator.value)
  }

}
