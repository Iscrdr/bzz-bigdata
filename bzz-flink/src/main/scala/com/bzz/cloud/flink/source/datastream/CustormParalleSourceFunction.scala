package com.bzz.cloud.flink.source.datastream
import org.apache.flink.streaming.api.functions.source.{ParallelSourceFunction, SourceFunction}
/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-09 18:28
  *
  */
class CustormParalleSourceFunction extends ParallelSourceFunction[Long]{

  var counter = 1L
  var isRunning = true

  override def run(sourceContext: SourceFunction.SourceContext[Long]): Unit = {
    while (isRunning){
      sourceContext.collect(counter)
      counter += 1
      Thread.sleep(1000)
    }
  }

  override def cancel(): Unit = {
    isRunning = false
  }
}
