package com.spark.scala.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

object HiveContextApp {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    sparkConf.setMaster("local[2]")
    sparkConf.setAppName("HiveContextApp")

    val sc = new SparkContext(sparkConf)

    val hiveContext = new HiveContext(sc)

    hiveContext.table("emp")

    sc.stop()


  }

}
