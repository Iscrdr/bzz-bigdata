package com.spark.scala.sql

import org.apache.spark.sql.{DataFrame, SparkSession}

object ParquetApp {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[2]").appName("ParquetApp").getOrCreate()
    //val frame: DataFrame = spark.read.format("parquet").load("D:\\software_study\\spark-2.4.3-bin-hadoop2.7\\examples\\src\\main\\resources\\users.parquet")
    val frame: DataFrame = spark.read.format("parquet").load("D:\\software_study\\spark-2.4.3-bin-hadoop2.7\\examples\\src\\main\\resources\\users.parquet")

    frame.printSchema()
    frame.show()

    spark.stop()

  }
}
