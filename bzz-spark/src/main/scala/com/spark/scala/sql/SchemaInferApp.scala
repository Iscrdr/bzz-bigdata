package com.spark.scala.sql

import org.apache.spark.sql.SparkSession

object SchemaInferApp {

  def main(args: Array[String]): Unit = {
    val sc = SparkSession.builder().appName("SchemaInferApp").master("local[2]").getOrCreate()

    val df = sc.read.format("json").load("file:/C:/Users/cloud/Desktop/spark/data/person.json")
    df.printSchema()
    df.show()





    sc.stop()


  }

}
