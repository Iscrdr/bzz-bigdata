package com.spark.scala.study

import org.apache.spark.{SparkConf, SparkContext}


object WordCountScala {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("WordCountScala").setMaster("local");

    val sparkContext = new SparkContext(conf);
    val lines = sparkContext.textFile("D:\\app\\word.txt",1)
    val words = lines.flatMap(line => line.split(" "))
    val pairs = words.map{word => (word,1)}
    val wordCount = pairs.reduceByKey{ _ + _}
    wordCount.foreach(wordCount => println(wordCount._1 + ": " + wordCount._2))
  }
}
