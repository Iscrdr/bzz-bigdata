package com.spark.scala.sql

import com.spark.sql.Student
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Title: RDDdataReflect
  * @ProjectName bzz-cloud
  * @Auther: clouder
  * @Date: 2018-07-17-2:08
  * @Description:
  */
object RDDdataReflect extends App {
    val sparkConf = new SparkConf().setAppName("RDDdataReflect").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)

    val studentRdd = sparkContext.textFile("D:\\app\\student.txt")

    case class Student(id: Int, name: String, age: Int)

    val studentsRdd = studentRdd.map(line => line.split(",")).map(arr => Student(arr(0).toInt,arr(1),arr(2).toInt))



}
