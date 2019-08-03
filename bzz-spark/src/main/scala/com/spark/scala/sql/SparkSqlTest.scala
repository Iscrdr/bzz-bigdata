package com.spark.scala.sql

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * SparkSession使用
  */
object SparkSqlTest {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SparkSqlTest")
      .master("local")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()

    //import spark.implicits._

    val df: DataFrame = spark.read.json("file:/C:/Users/cloud/Desktop/spark/data/person.json ")
    df.show()
    //df.filter($"age" > 21).show()
    df.createOrReplaceTempView("persons1")
    spark.sql("SELECT * FROM persons1 where age > 21").show()

    spark.stop()
    
  }

}
