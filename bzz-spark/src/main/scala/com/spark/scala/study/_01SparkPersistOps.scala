package com.spark.scala.study

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ProjectName bzz-bigdata
  * @Description: TODO
  * @author Yang qianli
  * @email 624003618@qq.com
  * @version 1.0.0
  * @date 2019-01-07 17:35:03
  */
object _01SparkPersistOps {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("_01SparkPersistOps")
    val sc = new SparkContext(conf)
    var start = System.currentTimeMillis()
    val linesRDD = sc.textFile("D:/app/word1.txt")
    //linesRDD.cache()
    linesRDD.persist(StorageLevel.MEMORY_ONLY)

    val retRDD = linesRDD.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    //retRDD.cache()
    retRDD.persist(StorageLevel.DISK_ONLY)
    var res1 =retRDD.count()
    println("第一次计算消耗的时间：" + (System.currentTimeMillis() - start) + "ms,"+res1)
    // 执行第二次RDD的计算
    start = System.currentTimeMillis()
    linesRDD.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).count()
    var res2 = retRDD.count()
    println("第二次计算消耗的时间：" + (System.currentTimeMillis() - start) + "ms,"+res2)

    // 持久化使用结束之后，要想卸载数据
    linesRDD.unpersist()

    sc.stop()

  }

}
