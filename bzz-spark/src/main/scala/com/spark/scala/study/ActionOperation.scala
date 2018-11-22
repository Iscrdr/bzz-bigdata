package com.spark.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Title: package
  * @ProjectName bzz-cloud
  * @Auther: clouder
  * @Date: 2018-07-13-18:50
  * @Description:
  */
object ActionOperation {

  /**
   * @param $ {tags}
   * @return ${return_type}
   * @throws
   * @author ${USER}
   * @date 2018-07-13 18:59
   */

  def reduce(): Unit ={
    var sparkConf = new SparkConf().setAppName("ActionOperation").setMaster("local")

    var sparkContext = new SparkContext(sparkConf)

    val nums : List[Int] = List(1, 2, 3, 4,5,6,7,8,9,10)
    val numbers = sparkContext.parallelize(nums)
    val sum = numbers.reduce((v1, v2) => {
      v1 + v2
    })
    println(sum)
  }

  def map(): Unit ={
    val sparkConf = new SparkConf().setMaster("local").setAppName("ActionOperation")
    val sparkContext = new SparkContext(sparkConf)

    val nums : List[Int] = List(1, 2, 3, 4,5,6,7,8,9,10)

    val number = sparkContext.parallelize(nums)
    val map = number.map( v => v*2 )
    map.foreach(v => println(v))
  }
  def count(): Unit ={
    val sparkConf = new SparkConf().setMaster("local").setAppName("ActionOperation")
    val sparkContext = new SparkContext(sparkConf)

    val nums : List[Int] = List(1, 2, 3, 4,5,6,7,8,9,10)

    val number = sparkContext.parallelize(nums)
    var count = number.count()
    var ints = number.take(5)
    println(count)
    ints.foreach(num => println(num))

  }
  def main(args: Array[String]): Unit = {
    /*map()*/
    count()
  }

}
