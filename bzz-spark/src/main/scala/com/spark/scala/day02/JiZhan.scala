package com.spark.scala.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ProjectName bzz-bigdata
  * @Description: TODO
  * @author Yang qianli
  * @email 624003618@qq.com
  * @version 1.0.0
  * @date 2019-01-04 01:06:18
  */
object JiZhan {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("JiZhan").setMaster("local")
    val sc = new SparkContext(conf)
    val lines1: RDD[String] = sc.textFile("D:\\app\\jizhan.txt")

    val word1: RDD[((String, String), Long)] = lines1.map(line => {
      val wordArr = line.split(",")
      val phone = wordArr(0)
      val time = wordArr(1).toLong
      val jiZhanid = wordArr(2)
      val lianjieType = wordArr(3)
      var ltype = 0
      if (lianjieType.equals("1")) {
        ltype = -lianjieType.toInt
      } else {
        ltype = lianjieType.toInt
      }

      ((phone, jiZhanid), time)
    })
    val times: RDD[((String, String), Long)] = word1.reduceByKey(_+_)
    val times1: RDD[(String, (String, Long))] = times.map(x=>(x._1._2,(x._1._1,x._2)))

    val lines2: RDD[String] = sc.textFile("D:\\app\\jizhanweizhi.txt")
    val word2: RDD[(String, (String, String))] = lines2.map(line1 => {
      val wordArr1 = line1.split(",")
      val id = wordArr1(0)
      val x = wordArr1(1)
      val y = wordArr1(2)
      (id, (x, y))
    })

    val joined: RDD[(String, ((String, Long), (String, String)))] = times1.join(word2)
    val rdd_all = joined.map(t => {
      val lac = t._1
      val mobile = t._2._1._1
      val time = t._2._1._2
      val x = t._2._2._1
      val y = t._2._2._2
      (mobile, lac, time, x, y)
    })

    val rdd_mobile = rdd_all.groupBy(_._1)
    //取出停留时间最长的前两个基站
    val rdd_topTwo= rdd_mobile.mapValues(it =>{
      it.toList.sortBy(_._3).reverse.take(2)
    })
    println(rdd_mobile.collect().toBuffer)
    println(rdd_topTwo.collect().toBuffer)
    sc.stop()
  }

}
