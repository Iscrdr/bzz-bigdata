package com.spark.scala.sql

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSQLMysqlApp {

  def main(args: Array[String]): Unit = {

    val session: SparkSession = SparkSession.builder().appName("SparkSQLMysqlApp").master("local[4]").getOrCreate()
    session.sqlContext.setConf("spark.sql.shuffle.partitions","10")





    /*val df1 = session.read.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/rcsjfx")
      .option("dbtable", "c_cust_sale_2014")
      .option("user", "root")
      .option("password", "root")
      .option("driver", "com.mysql.jdbc.Driver")
      .load()
    println("df1共有数据："+df1.count())

    val df2 = session.read.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/rcsjfx")
      .option("dbtable", "c_cust_sale_2015")
      .option("user", "root")
      .option("password", "root")
      .option("driver", "com.mysql.jdbc.Driver")
      .load()
    println("df2共有数据："+df2.count())

    val df3 = session.read.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/rcsjfx")
      .option("dbtable", "c_cust_sale_2016")
      .option("user", "root")
      .option("password", "root")
      .option("driver", "com.mysql.jdbc.Driver")
      .load()
    println("df3共有数据："+df3.count())

    val df4 = session.read.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/rcsjfx")
      .option("dbtable", "c_cust_sale_2017")
      .option("user", "root")
      .option("password", "root")
      .option("driver", "com.mysql.jdbc.Driver")
      .load()
    println("df4共有数据："+df4.count())
*/
    val df5 = session.read.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/rcsjfx")
      .option("dbtable", "c_cust_sale_2018")
      .option("user", "root")
      .option("password", "root")
      .option("driver", "com.mysql.jdbc.Driver")
      .load()
    println("df5共有数据：" + df5.count())



    /*df1.createTempView("c_cust_sale_2014")
    df2.createTempView("c_cust_sale_2015")
    df3.createTempView("c_cust_sale_2016")
    df4.createTempView("c_cust_sale_2017")*/
    df5.createTempView("c_cust_sale_2018")

    val prop = new Properties();

    prop.setProperty("user","root")
    prop.setProperty("password","root")

    val frame: DataFrame = session.sql("SELECT cust_name,DATE_FORMAT(fapiaoshiqi, '%Y-%m') AS yuefen, SUM(xiaoshoushouru) AS xsed FROM c_cust_sale_2018 WHERE fapiaoshiqi BETWEEN '2018-01-01 00:00:00' AND '2018-12-31 00:00:00' GROUP BY cust_name,DATE_FORMAT( fapiaoshiqi, '%Y-%m')")
    frame.write.jdbc("jdbc:mysql://localhost:3306/rcsjfx","c_custsale_tj_1",prop)



    //df.show(20)

    session.stop()

  }

}
