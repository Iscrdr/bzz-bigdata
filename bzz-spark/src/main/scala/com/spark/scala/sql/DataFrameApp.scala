package com.spark.scala.sql

import org.apache.spark.sql.{DataFrame, SparkSession}

object DataFrameApp {
  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .appName("")
      .master("local[2]")
      .getOrCreate()

    val dataFrame:DataFrame = sparkSession.read.format("json").load("file:/C:/Users/cloud/Desktop/spark/data/person.json")

    dataFrame.printSchema()

    dataFrame.show()

    dataFrame.select("name").show()

    dataFrame.select(dataFrame.col("age")).show()

    dataFrame.select(dataFrame.col("name"),(dataFrame.col("age") + 10).alias("ages")).show()

    dataFrame.filter(dataFrame.col("age")>19).show()

    dataFrame.groupBy("age").count().show()

    sparkSession.stop()


  }

}
