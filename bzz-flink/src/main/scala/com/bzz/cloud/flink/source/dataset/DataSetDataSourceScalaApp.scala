package com.bzz.cloud.flink.source.dataset

import com.bzz.cloud.flink.api.dataset.Person2
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration


/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-01 14:35
  *
  */
object DataSetDataSourceScalaApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    //csvFile(env)
    //fromCollection(env)
    //textFile(env)

    readCompressFile(env)

  }

  def readCompressFile(env:ExecutionEnvironment): Unit ={

    val filePath = "D:\\testdata\\compress"
    env.readTextFile(filePath).print();
  }

  def csvFile(env:ExecutionEnvironment):Unit = {
    import org.apache.flink.api.scala._

    val filePath = "D:\\\\testdata\\\\student.csv";

    /*
     * ignoreFirstLine = true，忽略第一行
     */
    println("===============ignoreFirstLine = true，忽略第一行==================")
    env.readCsvFile[(Int,String,Int)](filePath,ignoreFirstLine = true).print()

    /*
     只读前两列
     */
    println("===============只读前两列==================")
    env.readCsvFile[(Int,String)](filePath,ignoreFirstLine = true).print()


    /*
      case class
     */
    println("===============case class==================")
    env.readCsvFile[Person](filePath,ignoreFirstLine = true).print()
    case class Person(id:Int,name:String,age:Int)

    /*
      pojoFields
    */
    println("===============pojoFields==================")
    env.readCsvFile[Person2](filePath,ignoreFirstLine = true,pojoFields = Array("id","name","age")).print()


    /*
      includedFields
    */
    println("===============includedFields==================")
    env.readCsvFile[Person](filePath,ignoreFirstLine = true,includedFields = Array(0,1,2)).print()


  }


  /**
    * 从文件中创建datasource
    * @param env
    */
  def textFile(env:ExecutionEnvironment): Unit ={

    //从文件中读数据
    //val filePath = "D:\\testdata\\student.txt"

    //从文件夹中读数据
    val filePath = "D:\\testdata"

    //从递归文件夹中读数据

    val conf = new Configuration
    conf.setBoolean("recursive.file.enumeration",true);
    env.readTextFile(filePath).withParameters(conf).print()
  }


  /**
    * 从集合中创建 datasource
    * @param env
    */
  def fromCollection(env:ExecutionEnvironment): Unit ={
    import org.apache.flink.api.scala._
    val data = 1 to 10

    env.fromCollection(data).print()
  }



}
