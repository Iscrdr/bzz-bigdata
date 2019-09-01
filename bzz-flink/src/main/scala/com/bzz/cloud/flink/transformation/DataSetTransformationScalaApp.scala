package com.bzz.cloud.flink.transformation

import org.apache.flink.api.scala.ExecutionEnvironment


/**
 * @program: bzz-bigdata
 * @description: dataset transformation api
 * @author: 624003618@qq.com
 * @create: 2019-09-01 15:58
 */
object DataSetTransformationScalaApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    mapFunction(env)
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
