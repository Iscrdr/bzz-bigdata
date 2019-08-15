package com.bzz.cloud.flink

import org.apache.flink.api.scala.{AggregateDataSet, DataSet, ExecutionEnvironment}
import org.apache.flink.core.fs.FileSystem._
import org.apache.flink.streaming.api.scala._

object BatchWordCountScala {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val inpath = "D:\\test\\in.txt"
    val outpath = "D:\\test\\out.csv"
    val text = env.readTextFile(inpath)
    val wordCounts: DataSet[(String, Int)] = text.flatMap(line => line.split(" ")).map((_,1)).groupBy(0).sum(1)
    wordCounts.writeAsText(outpath,WriteMode.OVERWRITE).setParallelism(1)
    env.execute("BatchWordCountScala")
  }

}
