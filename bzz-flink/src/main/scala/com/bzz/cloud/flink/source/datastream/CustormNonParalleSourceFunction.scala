package com.bzz.cloud.flink.source.datastream

import org.apache.flink.streaming.api.functions.source.SourceFunction

/**
  * @program: bzz-bigdata
  * @description: ${description}
  * @author: 624003618@qq.com
  * @create: 2019-09-09 18:20
  *
  */
class CustormNonParalleSourceFunction extends SourceFunction[Long]{
  var count = 1L
  var isRuning = true

  override def run(sourceContext: SourceFunction.SourceContext[Long]): Unit = {
    while (isRuning){
      sourceContext.collect(count)
      count += 1
      Thread.sleep(1000)
    }
  }

  override def cancel(): Unit = {
    isRuning = false
  }
}
