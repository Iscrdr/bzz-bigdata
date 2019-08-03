package com.spark.scala.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext}

object SQLContextApp {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("SQLContextApp")
    sparkConf.setMaster("local")




    val sc = new SparkContext(sparkConf);
    val sqlContext = new SQLContext(sc);

    val df: DataFrame = sqlContext.read.json("file:/C:/Users/cloud/Desktop/spark/data/person.json")
    df.printSchema()
    df.show()



    sc.stop()
  }

}
