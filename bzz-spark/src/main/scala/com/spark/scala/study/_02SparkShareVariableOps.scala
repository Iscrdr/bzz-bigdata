package com.spark.scala.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ProjectName bzz-bigdata
  * @Description: TODO
  * @author Yang qianli
  * @email 624003618@qq.com
  * @version 1.0.0
  * @date 2019-01-07 17:50:12
  */
object _02SparkShareVariableOps {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName(_01SparkPersistOps.getClass.getSimpleName())
    val sc = new SparkContext(conf)


    val linesRDD = sc.textFile("D:/app/word1.txt",3)
    val wordsRDD = linesRDD.flatMap(_.split(" "))
    var num = 0
    val parisRDD = wordsRDD.map(word => {
      num += 1
      println("map--->num = " + num)
      (word, 1)
    })
    val retRDD = parisRDD.reduceByKey(_ + _)

    println("num = " + num)
    retRDD.foreach(println)
    println("num = " + num)
    sc.stop()
  }

}
