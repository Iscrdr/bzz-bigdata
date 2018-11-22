package com.spark.scala.stream

import java.sql.Connection

import com.bzz.common.jdbc.hikaricp.connect.HikariPoolManager
import com.zaxxer.hikari.HikariConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object ForEachRdd {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("ForEachRdd")

    val streaContext = new StreamingContext(sparkConf, Seconds(2))


    streaContext.checkpoint(".")

    val lines = streaContext.socketTextStream("localhost", 6789)

    val result = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    result.print()

    result.foreachRDD(rdd => {
      rdd.foreachPartition(partitionRddrecords => {
        if (partitionRddrecords.size > 0) {
          val connect = getConnection()
          partitionRddrecords.foreach {
            record =>
              val sql = "insert into wordcount(word,wordcount) values ('" + record._1 + "'," + record._2 + ")"
              connect.createStatement().addBatch(sql)
          }
          HikariPoolManager.releaseConnection(connect)
        }
      })
      streaContext.start()
      streaContext.awaitTermination()
    })
  }


  def getConnection(): Connection ={
    val hikaricpConfig = new HikariConfig()
    hikaricpConfig.setDriverClassName(driverName)
    hikaricpConfig.setJdbcUrl(jdbcurl)
    hikaricpConfig.setUsername(username)
    hikaricpConfig.setPassword(password)
    HikariPoolManager.getHikariDataSource(hikaricpConfig)
    HikariPoolManager.getConnection()
  }
  val driverName = "com.mysql.jdbc.Driver"
  val username = "root"
  val password = "root"
  val jdbcurl = "jdbc:mysql://127.0.0.1:3306/bzz-cloud-test?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true&useSSL=false"

}
