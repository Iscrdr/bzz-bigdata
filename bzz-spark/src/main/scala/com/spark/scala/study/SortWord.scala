package com.spark.scala.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Title: SortWord
  * @ProjectName bzz-cloud
  * @Auther: clouder
  * @Date: 2018-07-14-0:59
  * @Description:
  */
object SortWord {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("SortWord").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)

    val sparkRdd = sparkContext.textFile("D:\\app\\word.txt")

    val wordRdd = sparkRdd.flatMap(word => { word.split(" ")})
    val wordCountRdd = wordRdd.map(word => (word,1))
    val wordReduceRdd = wordCountRdd.reduceByKey(_ + _)
    wordReduceRdd.foreach(word =>{
      println(word._1 + ":" +word._2)
    })
    val converRDD = wordReduceRdd.map(word => (word._2,word._1))
    val secondRdd = converRDD.sortByKey(false)
    val resultRdd = secondRdd.map(word => (word._2,word._1))
    resultRdd.foreach(word =>println(word._1 + ":" +word._2))
  }

}
