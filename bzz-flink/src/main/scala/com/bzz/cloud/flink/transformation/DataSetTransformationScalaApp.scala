package com.bzz.cloud.flink.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

import scala.collection.mutable.ListBuffer
import org.apache.flink.api.common.operators.Order

/**
 * @program: bzz-bigdata
 * @description: dataset transformation api
 * @author: 624003618@qq.com
 * @create: 2019-09-01 15:58
 */
object DataSetTransformationScalaApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    distinctFunction(env:ExecutionEnvironment)
  }
  def joinFunction(env:ExecutionEnvironment):Unit = {
    import org.apache.flink.api.scala._
    val info = ListBuffer[String]()
    info.append("hadoop spark")
    info.append("hadoop flink")
    info.append("flink spark")
    info.append("storm flink")

    val info2 = ListBuffer[String]()
    info2.append("hadoop spark")
    info2.append("hadoop flink")
    info2.append("flink spark")
    info2.append("storm flink")
    val t1: DataSet[String] = env.fromCollection(info)
    val t2: DataSet[String] = env.fromCollection(info2)

    t1.join(t2).where(0).equalTo(0).apply((first,second)=>{


    }).print()



  }

  def distinctFunction(env:ExecutionEnvironment):Unit = {
    import org.apache.flink.api.scala._
    val info = ListBuffer[String]()
    info.append("hadoop spark")
    info.append("hadoop flink")
    info.append("flink spark")
    info.append("storm flink")
    val infoDataSet: DataSet[String] = env.fromCollection(info)
    infoDataSet.flatMap(_.split(" ")).distinct().print()

  }



  def flatMapFunction(env:ExecutionEnvironment):Unit = {
    import org.apache.flink.api.scala._
    val info = ListBuffer[String]()
    info.append("hadoop spark")
    info.append("hadoop flink")
    info.append("flink spark")
    info.append("storm flink")
    val infoDataSet: DataSet[String] = env.fromCollection(info)
    //infoDataSet.flatMap(_.split(" ")).print()

    // word count
    infoDataSet
      .flatMap(_.split(" "))
      .map((_,1))
      .groupBy(0).sum(1).print()

  }


  def firstFunction(env:ExecutionEnvironment): Unit ={
    val students = ListBuffer[(Int,String)]()
    for(i <- 1 to 100){
      students.append((1,"hadoop"))
      students.append((3,"spark"))
      students.append((2,"flink"))
      students.append((2,"flink1"))
      students.append((3,"spark1"))

      students.append((2,"flink3"))
      students.append((2,"flink2"))
      students.append((1,"hadoop1"))

    }
    import org.apache.flink.api.scala._

    val data = env.fromCollection(students)


    data.groupBy(0).sortGroup(1,Order.ASCENDING).first(2).print()

  }


  def mapPartitionFunction(env:ExecutionEnvironment): Unit ={
    val students = new ListBuffer[String]
    for(i <- 1 to 100){
      students.append("student: "+ i)
    }
    import org.apache.flink.api.scala._
    val data = env.fromCollection(students).setParallelism(4)

    data.mapPartition(x => {
      print("=============== \n")
      x
    }).print()


  }

  def mapFunction(env:ExecutionEnvironment): Unit ={
    import org.apache.flink.api.scala._

    val data = env.fromCollection(List(1,2,3,4,5,6,7,8,9,10))
    //data.map((x:Int) => x+1).print()
    //data.map(x=>x+1).print()
    data
      .map(_ + 1)
      .filter(_ % 2 ==0)
      .print()

  }

}
