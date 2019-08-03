package com.spark.scala.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

object DataFrameRddApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrameRddApp").master("local[2]").getOrCreate()

    //inferReflection(spark)

    program(spark)


    spark.stop()
  }

  def program(spark: SparkSession): Unit = {
    val rdd: RDD[String] = spark.sparkContext.textFile("file:/C:/Users/cloud/Desktop/spark/data/infos.txt")

    val infoRdd = rdd.map(_.split(",")).map(line => Row(line(0).toInt, line(1), line(2).toInt))

    val structType = StructType(Array(StructField("id",IntegerType,true),
      StructField("name",StringType,true),
      StructField("age",IntegerType,true)
    ));

    val infoDF = spark.createDataFrame(infoRdd,structType)
    infoDF.printSchema()
    infoDF.show()
    infoDF.createOrReplaceTempView("person")
    spark.sql("select * from person where age > 10").show()



  }

  def inferReflection(spark: SparkSession): Unit = {
    val rdd: RDD[String] = spark.sparkContext.textFile("file:/C:/Users/cloud/Desktop/spark/data/infos.txt")

    import spark.implicits._
    val infoDF = rdd.map(_.split(",")).map(line => Info(line(0).toInt, line(1), line(2).toInt)).toDF()
    infoDF.show()
    infoDF.filter("age > 20").show()

    infoDF.filter(infoDF.col("age") >= 20).show()

    infoDF.createOrReplaceTempView("person")
    spark.sql("select * from person where age > 10").show()
  }

  case class Info(id:Int, name:String, age:Int)

}
