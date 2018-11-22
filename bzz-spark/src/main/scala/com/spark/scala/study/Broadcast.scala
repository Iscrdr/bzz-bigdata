package com.spark.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Title: package
  * @ProjectName bzz-cloud
  * @Auther: clouder
  * @Date: 2018-07-13-22:57
  * @Description:
  */
object Broadcast {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("BroadCast").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)

    val nums : List[Int] = List(1,2,3,4,5,6,7,8,9,10)
    val factor = 3;
    val broadcast = sparkContext.broadcast(factor)

    val numberList = sparkContext.parallelize(nums)

    val map = numberList.map(num => {
      num * broadcast.value
    })
    map.foreach(num => {
      println(num)
    })


  }

}
