package com.bzz.cloud.flink.sql

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.types.Row
/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-11 07:51
  *
  */
object TableSQLApi {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val tableEnv = TableEnvironment.getTableEnvironment(env)

    val filePath = "D:\\testdata\\SalesLog.csv"
    val csvSource = env.readCsvFile[SalesLog](filePath,ignoreFirstLine = true)

    val table = tableEnv.fromDataSet(csvSource)
    tableEnv.registerTable("sales",table)
    val tableResult = tableEnv.sqlQuery("select custormerId,sum(amountPaid)  from sales group by custormerId")
    tableEnv.toDataSet[Row](tableResult).print()

    env.execute("TableSQLApi")
  }

  case class SalesLog(transactionId:String,custormerId:String,itemId:String,amountPaid:Double)
}
