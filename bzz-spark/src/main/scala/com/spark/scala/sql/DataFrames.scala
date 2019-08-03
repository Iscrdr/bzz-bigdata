package com.spark.scala.sql

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @ProjectName bzz-bigdata
  * @Description: TODO
  * @author Yang qianli
  * @email 624003618@qq.com
  * @version 1.0.0
  * @date 2019-01-08 23:22:57
  */
object DataFrames {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").appName("MyDataFrame").getOrCreate()

    var url = "jdbc:mysql://192.168.132.150:3306/rcsjfx"
    val table = "c_cust_sale_2018"
    val properties = new Properties()
    properties.setProperty("user","root")
    properties.setProperty("password","root")

    //需要传入Mysql的URL、表明、properties（连接数据库的用户名和密码）
    val df: DataFrame = spark.read.jdbc(url,table,properties)
    df.createOrReplaceTempView("c_cust_sale_2018")
    spark.sql("select * from c_cust_sale_2018 limit 10").show()


  }

}
