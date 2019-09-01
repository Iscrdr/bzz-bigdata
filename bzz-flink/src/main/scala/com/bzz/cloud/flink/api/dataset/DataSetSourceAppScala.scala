package com.bzz.cloud.flink.api.dataset

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

/**
  * @program: bzz-bigdata
  * @description: create DataSet use java
  * @author: 624003618@qq.com
  * @create: 2019-08-17 03:37
  */
object DataSetSourceAppScala {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    //fromCollect(env)

    val path = "D:\\test\\out.csv"
    //readTextFile(env,path)
    readCSVFile(env,path)

  }

  def fromCollect(env: ExecutionEnvironment): Unit ={
    val data = 1 to 10
    import org.apache.flink.api.scala._
    env.fromCollection(data).print()
  }


  /**
    * 读取文件夹的所有文件内容或者读取文件的所有内容
    * @param env
    * @param path
    */
  def readTextFile(env: ExecutionEnvironment,path:String): Unit ={
    env.readTextFile(path).print()
  }

  /**
    * 以递归的方式读取文件夹内容
    * @param env
    * @param path
    */
  def readTextFile2(env: ExecutionEnvironment,path:String): Unit ={

    val parameters = new Configuration
    parameters.setBoolean("recursive.file.enumeration",true)
    env.readTextFile(path).withParameters(parameters).print()
  }

  /**
    * 读取CSV文件内容
    * @param env
    * @param path
    */
  def readCSVFile(env: ExecutionEnvironment, path:String): Unit ={
    import org.apache.flink.api.scala._
    /* env.readCsvFile[MyCaseClass](path,includedFields = Array(0,1)).print() */
    env.readCsvFile[Person](path,includedFields = Array(0,1),pojoFields = Array("name","age")).print()
  }

  case class MyCaseClass(name:String,age:Int)
}
