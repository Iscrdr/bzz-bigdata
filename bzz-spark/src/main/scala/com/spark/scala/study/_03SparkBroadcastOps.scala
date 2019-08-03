package com.spark.scala.study

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ProjectName bzz-bigdata
  * @Description: TODO
  * @author Yang qianli
  * @email 624003618@qq.com
  * @version 1.0.0
  * @date 2019-01-07 17:54:48
  */
object _03SparkBroadcastOps {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName(_01SparkPersistOps.getClass.getSimpleName())
    val sc = new SparkContext(conf)


    val userList = List(
      "001,刘向前,18,0",
      "002,冯  剑,28,1",
      "003,李志杰,38,0",
      "004,郭  鹏,48,2"
    )

    val genderMap = Map("0" -> "女", "1" -> "男")

    val genderMapBC:Broadcast[Map[String, String]] = sc.broadcast(genderMap)

    val userRDD = sc.parallelize(userList)
    val retRDD = userRDD.map(info => {
      val prefix = info.substring(0, info.lastIndexOf(","))   // "001,刘向前,18"
      val gender = info.substring(info.lastIndexOf(",") + 1)
      val genderMapValue = genderMapBC.value
      val newGender = genderMapValue.getOrElse(gender, "男")
      prefix + "," + newGender
    })
    retRDD.foreach(println)
    sc.stop()

  }

}
