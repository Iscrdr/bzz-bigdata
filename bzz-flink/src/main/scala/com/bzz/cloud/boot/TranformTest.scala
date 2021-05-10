package com.bzz.cloud.boot

import org.apache.flink.api.common.functions.{FilterFunction, RichMapFunction}
import org.apache.flink.api.scala._
import org.apache.flink.runtime.state.memory.MemoryStateBackend
import org.apache.flink.streaming.api.scala.{ConnectedStreams, DataStream, StreamExecutionEnvironment}
/**
  * @description：Flink Tranform
  * @author ：Iscrdr
  * @email ：624003618@qq.com
  * @date ：2020/01/17 14:12
  * @modified By：
  * @version: 1.0.0
  */
object TranformTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    env.setStateBackend(new MemoryStateBackend())

    val lines = env.readTextFile("D:\\testdata\\student.txt")

   val data:DataStream[Student] =  lines.map(data => {
      val strings = data.split(",")
      Student(strings(0).toInt,strings(1),strings(2).toInt)
    })

    /**
     * keyBy,reduce
     */
    val data1 = data
    data1.keyBy(1).reduce((x,y) => Student(x.id,x.name,x.age+1))
    val data2 = data
    data2.keyBy(1).sum(2)

    /*data1.print("keyby reduce")
    data1.print("keyby sum")*/

    /**
     * split 分流
     */
     val splitStream = data.split((s: Student) => {
       (s.age > 30) match {
         case true => List("hight")
         case false => List("low")
       }
     })
    val hight = splitStream.select("hight")
    val low = splitStream.select("low")
    /*hight.print("hight")
    low.print("low")*/

    /**
     *  connect 和coMap，只能合并两条流
     */
    val connectedStreams: ConnectedStreams[Student, Student] = hight.connect(low)
    connectedStreams.map((s1:Student)=>{
      if(s1.age<50){
        s1
      }

    } ,(s2:Student)=>{
      if(s2.age>20){
        s2
      }
    }) //.print()

    /**
      * union 合并多条流，数据结构必须相同，hight.union(connectedStreams)
      *
      * Connect只能操作两个流
      *
      */
    val valueunion: DataStream[Student] = hight.union(low)
    //valueunion.print("union")

    valueunion.filter(new Myfilter()).print("myfilter")

    env.execute("TranformTest")




  }

}
case class Student(id:Int,name:String,age:Int)

class Myfilter() extends FilterFunction[Student]{
  override def filter(value: Student): Boolean = {
    value.age > 30
  }
}

/*
class MyMapper() extends RichMapFunction[Student]{
  override def map(value: Student): Student = {
    value
  }
}*/
